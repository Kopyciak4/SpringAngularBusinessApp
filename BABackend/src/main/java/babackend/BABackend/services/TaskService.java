package babackend.BABackend.services;

import babackend.BABackend.domain.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasks();

    void deleteTask(int taskId);

}
