package josh.todo.Service;

import josh.todo.Entity.Task;
import josh.todo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;

    public TaskService(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task create(Task task) {
        return taskRepo.save(task);
    }

    public Optional<Task> findById(Integer id) {
        return taskRepo.findById(id);
    }

    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    public List<Task> findAllByUserId(Integer userId) {
        return taskRepo.findAllByUserId(userId);
    }

    public List<Task> findAllOrderById() {
        return taskRepo.findAll(Sort.by("id"));
    }

    public List<Task> findAllOrderByDeadline() {
        return taskRepo.findAll(Sort.by("deadline"));
    }

    public List<Task> findAllByUserIdOrderById(Integer userId) {
        return taskRepo.findAllByUserIdOrderById(userId);
    }

    public List<Task> findAllByUserIdOrderByDeadline(Integer userId) {
        return taskRepo.findAllByUserIdOrderByDeadline(userId);
    }

    public List<Task> deleteById(Integer id) {
        taskRepo.deleteById(id);
        return taskRepo.findAll();
    }

    public void completeById(Integer id) {
        var task = taskRepo.findById(id);

        if(task.isEmpty()) {
            return;
        }

        task.get().setIsCompleted(true);
        taskRepo.save(task.get());
    }
}
