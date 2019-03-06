package babackend.BABackend.web;

import babackend.BABackend.domain.Task;
import babackend.BABackend.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/tasks")
@RestController
public class TaskController {

    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping()
    public void deleteTask(@RequestParam("taskId") int taskId) {
        taskService.deleteTask(taskId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public void updateTask(@Valid @RequestBody Task task) { taskService.updateTask(task);}

    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable int taskId){
        return taskService.getTask(taskId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public void createTask(@Valid @RequestBody Task task) {
        taskService.createTask(task);
    }









}
