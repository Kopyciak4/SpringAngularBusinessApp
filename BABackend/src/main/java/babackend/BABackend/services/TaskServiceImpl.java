package babackend.BABackend.services;

import babackend.BABackend.DAO.TasksRepository;
import babackend.BABackend.domain.Task;
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

        return taskRepository.findAll();


    }

    public void deleteTask(int taskID) {
        taskRepository.deleteById(taskID);
    }



}
