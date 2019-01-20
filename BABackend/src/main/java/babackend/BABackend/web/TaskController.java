package babackend.BABackend.web;

import babackend.BABackend.domain.Task;
import babackend.BABackend.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping()
    public void deleteTask(@RequestParam("taskId") int taskId) {
        taskService.deleteTask(taskId);
    }

}
