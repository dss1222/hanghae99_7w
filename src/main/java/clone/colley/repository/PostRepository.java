package clone.colley.repository;

import clone.colley.model.Posts;
import clone.colley.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Posts,Long> {
    List<Posts> findAllByUpdatedAtBetweenOrderByUpdatedAtDesc(LocalDateTime start, LocalDateTime end);
    List<Posts> findAllByUser(User user);
}
