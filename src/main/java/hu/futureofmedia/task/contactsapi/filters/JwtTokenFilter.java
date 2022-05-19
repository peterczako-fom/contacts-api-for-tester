package hu.futureofmedia.task.contactsapi.filters;

import hu.futureofmedia.task.contactsapi.auth.services.JwtTokenUtil;
import hu.futureofmedia.task.contactsapi.users.AppUserDetails;
import hu.futureofmedia.task.contactsapi.users.repositories.AppUserRepository;
import hu.futureofmedia.task.contactsapi.utility.LoggerHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final AppUserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        log.info("Class: {}, method: doFilterInternal", this.getClass().getSimpleName());

        final String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
        LoggerHelper.log(log, headerAuth);
        if(headerAuth == null || !headerAuth.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String token = headerAuth.split(" ")[1];
        LoggerHelper.log(log, token);
        if(!jwtTokenUtil.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        AppUserDetails userDetails = userRepository
                .findByEmail(jwtTokenUtil.getEmail(token))
                .map(AppUserDetails::new)
                .orElse(null);

        LoggerHelper.log(log, userDetails);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        Optional.ofNullable(userDetails).map(AppUserDetails::getAuthorities).orElse(null)
                );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);

    }


}
