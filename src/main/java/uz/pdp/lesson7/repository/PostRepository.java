package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
