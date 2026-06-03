package josh.todo.Controller;

import josh.todo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private TaskService service;

    @GetMapping
    public String showMain(Model model) {
        model.addAttribute("tasks", service.findAll());

        return "main";
    }

    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable Integer id, Model model) {
        model.addAttribute("tasks", service.deleteById(id));

        return "redirect:/main";
    }

    @GetMapping("/completeTask/{id}")
    public String completeTask(@PathVariable Integer id, Model model) {
        service.completeById(id);
        model.addAttribute("tasks", service.findAll());

        return "redirect:/main";
    }
}