package co.icesi.taskManager.services.interfaces;

import java.util.List;

import co.icesi.taskManager.model.Task;

public interface ITaskService {
    Task createTask(Task task);
    Task updateTask(Task task);
    void deleteTask(long taskId);
    Task getTaskById(long taskId);
    List<Task> getAllTask();
}
