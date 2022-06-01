package hu.futureofmedia.task.contactsapi.graphqlqueryresolver;

import com.coxautodev.graphql.tools.GraphQLResolver;

public class PostResolver implements GraphQLResolver<Post> {
    private AuthorDao authorDao;

    public PostResolver(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public Author getAuthor(Post post) {
        return authorDao.getAuthor(post.getAuthorId()).orElseThrow(RuntimeException::new);
    }

    public Author getFirst_author(Post post) {
        return authorDao.getAuthor(post.getAuthorId()).orElseThrow(RuntimeException::new);
    }

}
