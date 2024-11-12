package com.qualitytaste.controller;

import com.qualitytaste.model.Task;
import com.qualitytaste.service.TaskService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.qualitytaste.service.StringToTaskTypeConverter;

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
        return "taskList.html";
    }

    // Search task by ID
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchTaskById(@RequestParam("id") Long id, Model model) {
        Task task = taskService.getTaskById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        model.addAttribute("task", task);
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
        System.out.println("Task created: " + task + "dsadassdsadasdasdsadsaddasd");
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
}