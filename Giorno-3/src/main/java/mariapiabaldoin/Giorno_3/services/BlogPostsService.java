package mariapiabaldoin.Giorno_3.services;

import mariapiabaldoin.Giorno_3.entities.Autore;
import mariapiabaldoin.Giorno_3.entities.BlogPost;
import mariapiabaldoin.Giorno_3.exceptions.NotFoundException;
import mariapiabaldoin.Giorno_3.payloads.NewBlogPostPayload;
import mariapiabaldoin.Giorno_3.repositories.AutoriRepository;
import mariapiabaldoin.Giorno_3.repositories.BlogPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BlogPostsService {

    @Autowired
    private BlogPostsRepository blogPostsRepository;
    @Autowired
    private AutoriRepository autoriRepository;

    public BlogPost saveBlogPost(NewBlogPostPayload body) {


        Autore autore = autoriRepository.findById(body.getAutoreId())
                .orElseThrow(() -> new NotFoundException(body.getAutoreId()));


        BlogPost newBlogPost = new BlogPost(
                body.getCategoria(),
                body.getTitolo(),
                body.getContenuto(),
                body.getTempoLettura(),
                autore
        );


        String coverUrl = "https://picsum.photos/200/300";
        newBlogPost.setCover(coverUrl);


        return this.blogPostsRepository.save(newBlogPost);
    }

    public Page<BlogPost> findAll(int page, int size, String sortBy) {
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return this.blogPostsRepository.findAll(pageable);
    }

    public BlogPost findById(long blogPostId) {
        return this.blogPostsRepository.findById(blogPostId).orElseThrow(() -> new NotFoundException(blogPostId));
    }

    public BlogPost findByIdAndUpdate(long blogPostId, NewBlogPostPayload body) {

        BlogPost found = this.findById(blogPostId);


        found.setCategoria(body.getCategoria());
        found.setTitolo(body.getTitolo());
        found.setContenuto(body.getContenuto());
        found.setTempoLettura(body.getTempoLettura());


        return this.blogPostsRepository.save(found);
    }

    public void findByIdAndDelete(long blogPostId) {
        BlogPost found = this.findById(blogPostId);
        this.blogPostsRepository.delete(found);
    }
}
