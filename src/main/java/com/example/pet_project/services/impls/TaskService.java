package com.example.pet_project.services.impls;
import com.example.pet_project.dtos.requests.TaskRequest;
import com.example.pet_project.entities.Category;
import com.example.pet_project.entities.Task;
import com.example.pet_project.repositories.TaskRepository;
import com.example.pet_project.services.ITaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Task> getTask(String sortBy, String direction){
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortBy);
        return taskRepository.findAll(sort);
    }

    @Override
    public boolean addTask(TaskRequest request){
        Task task = modelMapper.map(request, Task.class);
        task.setCategory(Category.fromValue(request.getCategoryName()));
        if (task.getCategory() == null) task.setCategory(Category.OTHER);
        taskRepository.save(task);
        return true;
    }

    @Override
    public boolean toggleTask(Long id){
        if (!taskRepository.existsById(id)){
            throw new RuntimeException();
        }
        Task task = taskRepository.findTaskById(id);
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
        return true;
    }

    @Override
    public boolean deleteTask(Long id){
        if (!taskRepository.existsById(id)){
            throw new RuntimeException();
        }
        taskRepository.deleteById(id);
        return true;
    }

    @Override
    public int getNumberTask(){
        return taskRepository.findAll().size();
    }

    @Override
    public int getNumberTaskCompleted(){
        return taskRepository.countTaskByCompletedTrue();
    }

    @Override
    public int getNumberTaskNotCompleted(){
        return taskRepository.countTaskByCompletedFalse();
    }
}
