package babackend.BABackend.services;

import babackend.BABackend.DAO.TasksRepository;
import babackend.BABackend.domain.Task;
import babackend.BABackend.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private TasksRepository taskRepository;


    @Autowired
    public TaskServiceImpl(TasksRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) {
            User taskOwner = task.getTaskOwner();
            if (taskOwner != null) {
                taskOwner.setTasks(null);
            }
        }
        return tasks;

    }
    @Override
    public void deleteTask(int taskID) {
        taskRepository.deleteById(taskID);
    }

    @Override
    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Task getTask(int taskId) {
        Task task =  taskRepository.findById(taskId).orElse(null);
        task.getTaskOwner().setTasks(null);
        return task;
    }

    @Override
    public void createTask(Task task) {
         taskRepository.save(task);
    }

    @Override
    public void resetUserId(int userId){
        taskRepository.resetId(userId);
    }



}
