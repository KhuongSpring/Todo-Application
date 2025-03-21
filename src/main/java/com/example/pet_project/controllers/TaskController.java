package com.example.pet_project.controllers;

import com.example.pet_project.dtos.requests.TaskRequest;
import com.example.pet_project.dtos.responses.TaskResponse;
import com.example.pet_project.entities.Category;
import com.example.pet_project.entities.Task;
import com.example.pet_project.services.impls.TaskService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskController {

    final
    ModelAndView modelAndView;
    final TaskService taskService;
    final ModelMapper modelMapper;
    List<Task> tasks;
    String categoryName = "";

    @GetMapping("/home")
    public ModelAndView getHome(
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        if (categoryName.equals("")) tasks = taskService.getTask(sortBy, direction);
        else tasks = taskService.getTaskByCategory(categoryName, sortBy, direction);
        List<TaskResponse> taskResponses = tasks
                .stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getTaskName(),
                        task.isCompleted(),
                        task.getCreatedAt(),
                        task.getCategory() != null ? task.getCategory().getDisplayName() : Category.OTHER.getDisplayName(),
                        task.getCategory() != null ? task.getCategory().getColor() : Category.OTHER.getColor()
                ))
                .collect(Collectors.toList());

        modelAndView.setViewName("home");
        modelAndView.addObject("listTask", taskResponses);
        modelAndView.addObject("task", new TaskRequest());
        modelAndView.addObject("sortBy", sortBy);
        modelAndView.addObject("direction", direction);
        modelAndView.addObject("countTask", taskService.getNumberTask());
        modelAndView.addObject("countTaskCompleted", taskService.getNumberTaskCompleted());
        modelAndView.addObject("countTaskNotCompleted", taskService.getNumberTaskNotCompleted());

        return modelAndView;
    }

    @PostMapping("/home")
    public ModelAndView addTask(@ModelAttribute("task") TaskRequest request) {
        taskService.addTask(request);
        modelAndView.setViewName("redirect:/todo/home");
        return modelAndView;
    }

    @PutMapping("/toggle/{id}")
    public ModelAndView toggleTask(@PathVariable Long id) {
        if (taskService.toggleTask(id))
            modelAndView.setViewName("redirect:/todo/home");
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateTask(@PathVariable Long id, @ModelAttribute TaskRequest request){
        if (taskService.updateTask(id, request))
            modelAndView.setViewName("redirect:/todo/home");
        return modelAndView;
    }

    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteTask(@PathVariable Long id) {
        if (taskService.deleteTask(id))
            modelAndView.setViewName("redirect:/todo/home");
        return modelAndView;
    }

    @PostMapping("/sortByCategory")
    public ModelAndView sortByCategory(@RequestParam("category") String categoryName) {
        this.categoryName = categoryName;
        modelAndView.setViewName("redirect:/todo/home");
        return modelAndView;
    }
}
