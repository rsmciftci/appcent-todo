package com.appcent.todo.service;

import com.appcent.todo.converter.TaskMapper;
import com.appcent.todo.dao.TaskDao;
import com.appcent.todo.dto.TaskDto;
import com.appcent.todo.dto.TaskUpdatingDto;
import com.appcent.todo.dto.TaskSavingDto;
import com.appcent.todo.entity.Task;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskDao taskDao;



    public TaskSavingDto save(TaskSavingDto taskSavingDto){

        Task task = TaskMapper.INSTANCE.convertTaskSavingDtoToTask(taskSavingDto);
        task.setCreationDateTime(LocalDateTime.now());
        task.setDone(false);
        task = taskDao.save(task);

        TaskSavingDto savedTaskSavingDto = TaskMapper.INSTANCE.convertTaskToTaskSavingDto(task);

        return savedTaskSavingDto;
    }

    public void delete(Long id) {
        Optional<Task> optionalTask = taskDao.findById(id);
        if (optionalTask.isPresent()){
            taskDao.deleteById(id);
        }
    }


    public TaskUpdatingDto update(TaskUpdatingDto taskUpdatingDto) {


        Task task = TaskMapper.INSTANCE.convertTaskUpdatingDtoToTask(taskUpdatingDto);
        task.setUpdatedDateTime(LocalDateTime.now());

        task = taskDao.save(task);

        TaskUpdatingDto updatedTask = TaskMapper.INSTANCE.convertTaskToTaskUpdatingDto(task);
        return  updatedTask;

    }

    public List<TaskDto> findAllByUserId(Long userId){
        List<Task> taskList = taskDao.findAllByUserId(userId);
        List<TaskDto> taskDtoList = TaskMapper.INSTANCE.convertTaskListToTaskDtoList(taskList);
        return  taskDtoList;
    }


    public TaskDto findById(Long taskId) {
        Optional<Task> optionalTask = taskDao.findById(taskId);
        Task task = null;
        if(optionalTask.isPresent()){
            task = optionalTask.get();
        }
        TaskDto taskDto = TaskMapper.INSTANCE.convertTaskToTaskDto(task);
        return taskDto;
    }


}
