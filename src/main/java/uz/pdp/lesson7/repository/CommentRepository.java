package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
