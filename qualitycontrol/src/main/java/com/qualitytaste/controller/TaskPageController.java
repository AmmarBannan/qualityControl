package com.qualitytaste.controller;

import com.qualitytaste.model.Task;
import com.qualitytaste.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping(value ="/tasks") // Common base path for all task-related endpoints
public class TaskPageController {

    @Autowired
    private TaskService taskService;

    // Display all tasks
    @RequestMapping(method = RequestMethod.GET)
    public String showAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);

        for (Task task : tasks) {
            boolean test=task.isCompleted()?true:false;
        }
        return "taskList.html";
    }

    // Search task by ID
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchTaskByTitle(@RequestParam("title") String title, Model model) {
        List<Task> tasks = taskService.getTasksByTitle(title);
        if (tasks.isEmpty()) {
            throw new IllegalArgumentException("No tasks found with the given name");
        }
        model.addAttribute("tasks", tasks);
        return "taskDetails.html";
    }
    // Show form to add a new task
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "addTask";
    }

    // Process the form to add a new task
    // @RequestMapping(method = RequestMethod.POST,value = "/add", consumes = "application/json", produces = "application/json")
    @PostMapping
    public String addTask(@ModelAttribute Task task) {
        
        if (task.getDeadline() == null) {
            throw new IllegalArgumentException("Deadline must be provided");
        }
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    // Show form to edit an existing task
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showEditTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        model.addAttribute("task", task);
        return "editTask.html";
    }

    // Process the form to edit a task
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editTask(@PathVariable Long id, @ModelAttribute Task task) {
        task.setId(id);
        taskService.updateTask(id, task);
        return "redirect:/tasks";
    }

    // Delete task
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
    
    @PostMapping("/update/completion/{id}")
    public ResponseEntity<Void> updateTaskCompletion(@PathVariable Long id, @RequestParam Boolean completed) {
        taskService.updateTaskCompletion(id, completed);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/update/metric/{id}")
    public ResponseEntity<Void> updateTaskMetric(@PathVariable Long id, @RequestParam Integer currentPoint) {
        taskService.updateTaskMetric(id, currentPoint);
        return ResponseEntity.ok().build();
    }
}