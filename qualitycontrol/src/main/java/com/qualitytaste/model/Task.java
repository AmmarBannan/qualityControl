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
    private TaskType type=TaskType.CHECKBOX; // Checkbox or Metric

    private Boolean completed=false;

    private Integer min_point=0;     // Minimum value for Metric
    private Integer max_point=10;     // Maximum value for Metric
    private Integer current_point; // Ideal value for Metric


    // Constructors

    public Task() {}

    public Task(String title, String description, TaskType type, Integer min_point, Integer max_point
    ) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.min_point = min_point;
        this.max_point = max_point;
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
        this.type = TaskType.valueOf(type.toUpperCase()); // Convert in_put to uppercase
    }

    public void setType(TaskType type) {
        this.type = type; // Directly set if it's already an enum
    }

    public Integer getMin_point() { return min_point; }
    public void setMin_point(Integer min_point) { this.min_point = min_point; } 

    public Integer getMax_point() { return max_point; }   
    public void setMax_point(Integer max_point) { this.max_point = max_point; }

    public Integer getCurrent_point() { return current_point; }
    public void setCurrent_point(Integer current_point) { this.current_point = current_point; }

    
}

