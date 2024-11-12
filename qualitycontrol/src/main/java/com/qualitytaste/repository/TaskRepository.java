package com.qualitytaste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.qualitytaste.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTitleContainingIgnoreCase(String title);
}