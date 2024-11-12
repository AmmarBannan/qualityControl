package com.qualitytaste.model;

import jakarta.persistence.*;



@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskType type; // Checkbox or Metric

    private Boolean completed;

    private Integer minPoint;     // Minimum value for Metric
    private Integer maxPoint;     // Maximum value for Metric
    private Integer currentPoint; // Ideal value for Metric


    // Constructors

    public Task() {}

    public Task(String title, String description, TaskType type, Integer minPoint, Integer maxPoint
    ) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.minPoint = minPoint;
        this.maxPoint = maxPoint;
    }


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public TaskType getType() { return type; } 
    
    public void setType(String type) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        this.type = TaskType.valueOf(type.toUpperCase()); // Convert input to uppercase
    }

    public void setType(TaskType type) {
        this.type = type; // Directly set if it's already an enum
    }

    public Integer getMinPoint() { return minPoint; }
    public void setMinPoint(Integer minPoint) { this.minPoint = minPoint; } 

    public Integer getMaxPoint() { return maxPoint; }   
    public void setMaxPoint(Integer maxPoint) { this.maxPoint = maxPoint; }

    public Integer getCurrentPoint() { return currentPoint; }
    public void setCurrentPoint(Integer currentPoint) { this.currentPoint = currentPoint; }

    
}

