package josh.todo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean isCompleted = false;
    @NotNull
    private LocalDate deadline;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private Integer userId;

}
