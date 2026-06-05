package josh.todo.Repository;

import josh.todo.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    public List<Task> findAllByUserId(Integer userId);
    public List<Task> findAllByUserIdOrderById(Integer userId);
    public List<Task> findAllByUserIdOrderByDeadline(Integer userId);

}
