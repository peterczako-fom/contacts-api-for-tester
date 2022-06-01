package hu.futureofmedia.task.contactsapi.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.futureofmedia.task.contactsapi.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final List<String> whitePaths = List.of(
            "/login",
            "/auth/refresh-token",
            "/v1/users"
    );

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(whitePaths.contains(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }

        final String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(headerAuth == null || !headerAuth.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String token = headerAuth.split(" ")[1];

        try {
            var username = JwtUtil.decode(token).getSubject();
            var userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            new ObjectMapper().writeValue(response.getOutputStream(), e.getMessage());
        }
    }
}
