package josh.todo.Controller;

import jakarta.servlet.http.HttpServletRequest;
import josh.todo.Entity.User;
import josh.todo.Service.TaskService;
import josh.todo.Service.UserRepositoryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserRepositoryDetailsService userService;

    @GetMapping
    public String showMain(Model model) {
        model.addAttribute("tasks", taskService.findAll());

        return "main";
    }

    @PreAuthorize("hasRole{USER}")
    @GetMapping("/deleteCurrentUser")
    public String deleteCurrentUser(@AuthenticationPrincipal User user, HttpServletRequest request) {
        userService.deleteUserById(user.getId());

        SecurityContextHolder.clearContext();
        request.getSession(false).invalidate();

        return "redirect:/";
    }

    @GetMapping("/taskOrderById")
    public String taskOrderById(Model model) {
        model.addAttribute("tasks", taskService.findAllByOrderById());

        return "main";
    }

    @GetMapping("/taskOrderByDeadline")
    public String taskOrderByDeadline(Model model) {
        model.addAttribute("tasks", taskService.findAllByOrderByDeadline());

        return "main";
    }

    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable Integer id, Model model) {
        model.addAttribute("tasks", taskService.deleteById(id));

        return "redirect:/main";
    }

    @GetMapping("/completeTask/{id}")
    public String completeTask(@PathVariable Integer id, Model model) {
        taskService.completeById(id);
        model.addAttribute("tasks", taskService.findAll());

        return "redirect:/main";
    }
}