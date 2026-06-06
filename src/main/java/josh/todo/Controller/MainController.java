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
    public String showMain(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("tasks", taskService.findAllByUserId(user.getId()));

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

    @GetMapping("/taskByUserIdOrderById")
    public String taskByUserIdOrderById(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("tasks", taskService.findAllByUserIdOrderById(user.getId()));

        return "main";
    }

    @GetMapping("/taskByUserIdOrderByDeadline")
    public String taskByUserIdOrderByDeadline(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("tasks", taskService.findAllByUserIdOrderByDeadline(user.getId()));

        return "main";
    }

    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable Integer id, @AuthenticationPrincipal User user, Model model) {
        if(taskService.findById(id).isEmpty() || !taskService.findById(id).get().getUserId().equals(user.getId())) {
            return "redirect:/";
        }

        model.addAttribute("tasks", taskService.deleteById(id));

        return "redirect:/main";
    }

    @GetMapping("/completeTask/{id}")
    public String completeTask(@PathVariable Integer id, @AuthenticationPrincipal User user, Model model) {
        if(taskService.findById(id).isEmpty() || !taskService.findById(id).get().getUserId().equals(user.getId())) {
            return "redirect:/";
        }

        taskService.completeById(id);
        model.addAttribute("tasks", taskService.findAll());

        return "redirect:/main";
    }
}