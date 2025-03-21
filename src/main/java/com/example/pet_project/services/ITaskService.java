package com.example.pet_project.services;

import com.example.pet_project.dtos.requests.TaskRequest;
import com.example.pet_project.entities.Task;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ITaskService {
    List<Task> getTask(String sortBy, String direction);
    boolean addTask(TaskRequest request);
    boolean toggleTask(Long id);
    boolean deleteTask(Long id);
    boolean updateTask(Long id, TaskRequest request);
    int getNumberTask();
    int getNumberTaskCompleted();
    int getNumberTaskNotCompleted();
    List<Task> getTaskByCategory(String categoryName, String sortBy, String direction);
}
