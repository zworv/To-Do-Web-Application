package josh.todo.Controller;

import jakarta.validation.Valid;
import josh.todo.Entity.Task;
import josh.todo.Entity.User;
import josh.todo.Service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/addTask")
public class AddTaskController {

    @Autowired
    private TaskService service;

    @ModelAttribute(name="task")
    public Task task() {
        return new Task();
    }

    @GetMapping
    public String showAddTaskForm() {
        return "addTask";
    }

    @PostMapping
    public String addTask(@Valid Task task, Errors errors, @AuthenticationPrincipal User user) {
        if(errors.hasErrors()) {
            return "addTask";
        }

        Integer userId = user.getId();

        task.setUserId(userId);

        service.create(task);

        log.info("Add user {} task: {}", user, task);

        return "redirect:/main";
    }
}
