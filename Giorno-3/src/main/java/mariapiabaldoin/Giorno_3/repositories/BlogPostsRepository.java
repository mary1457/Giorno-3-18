package mariapiabaldoin.Giorno_3.repositories;


import mariapiabaldoin.Giorno_3.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogPostsRepository extends JpaRepository<BlogPost, Long> {

}