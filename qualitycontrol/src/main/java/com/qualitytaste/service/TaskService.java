package com.qualitytaste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualitytaste.model.Task;
import com.qualitytaste.repository.TaskRepository;

@Service
public class TaskService {
    

    @Autowired
    private TaskRepository taskRepository;

     public List<Task> getAllTasks() {  //return all tasks
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {  //return specific task
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {      //add task
        System.out.println("saddddddddddddddddddddddddddddddddddddddddddddddd@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+task.getType());
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {  //update task
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setCompleted(updatedTask.isCompleted());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }
    public Task completeTask(Long id) {  //update task
        return taskRepository.findById(id)
                .map(task -> {
                    task.setCompleted(!task.isCompleted());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    public void deleteTask(Long id) {  //delete task
        taskRepository.deleteById(id);
    }

    
}