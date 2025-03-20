package com.example.pet_project.repositories;

import com.example.pet_project.entities.Category;
import com.example.pet_project.entities.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findTaskById(Long id);
    List<Task> findAll(Sort sort);
    Integer countTaskByCompletedTrue();
    Integer countTaskByCompletedFalse();
    List<Task> findAllByCategory(Category category, Sort sort);
}
