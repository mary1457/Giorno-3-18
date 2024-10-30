package mariapiabaldoin.Giorno_3.controllers;


import mariapiabaldoin.Giorno_3.entities.BlogPost;
import mariapiabaldoin.Giorno_3.payloads.NewBlogPostPayload;
import mariapiabaldoin.Giorno_3.services.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogposts")
public class BlogPostsController {
    @Autowired
    private BlogPostsService blogPostsService;


    @GetMapping
    public Page<BlogPost> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sortBy) {

        return this.blogPostsService.findAll(page, size, sortBy);
    }


    @GetMapping("/{blogPostId}")
    public BlogPost findBlogPostById(@PathVariable long blogPostId) {
        return this.blogPostsService.findById(blogPostId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost createBlogPost(@RequestBody NewBlogPostPayload body) {
        return this.blogPostsService.saveBlogPost(body);
    }


    @PutMapping("/{blogPostId}")
    public BlogPost findBlogPostByIdAndUpdate(@PathVariable long blogPostId, @RequestBody NewBlogPostPayload body) {
        return this.blogPostsService.findByIdAndUpdate(blogPostId, body);
    }


    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findBlogPostByIdAndDelete(@PathVariable int blogPostId) {
        this.blogPostsService.findByIdAndDelete(blogPostId);
    }


}
