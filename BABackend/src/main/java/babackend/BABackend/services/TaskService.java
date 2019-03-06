package babackend.BABackend.services;

import babackend.BABackend.domain.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasks();

    void deleteTask(int taskId);

    void updateTask(Task task);

    Task getTask(int taskId);

    void createTask(Task task);

    void resetUserId(int userId);

}


