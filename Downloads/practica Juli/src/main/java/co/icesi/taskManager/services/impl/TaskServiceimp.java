package co.icesi.taskManager.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.icesi.taskManager.model.Task;
import co.icesi.taskManager.repositories.TaskRepository;
import co.icesi.taskManager.services.interfaces.TaskService;

import java.util.List;

@Service
public class TaskServiceimp implements TaskService{


    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        Long id = task.getId();

        Task task2 = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task2.setName(task.getName());
        task2.setNotes(task.getNotes());
        task2.setDescription(task.getDescription());
        task2.setPriority(task.getPriority());

        return task2;

    }

    @Override
    public void deleteTask(long taskId) {
        Task taskToDelete = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        taskRepository.delete(taskToDelete);
    }

    @Override
    public Task getTaskById(long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }





}