package josh.todo.Service;

import josh.todo.Entity.Task;
import josh.todo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    public List<Task> findAllByOrderById() {
        return taskRepo.findAll(Sort.by("id"));
    }

    public List<Task> findAllByOrderByDeadline() {
        return taskRepo.findAll(Sort.by("deadline"));
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
