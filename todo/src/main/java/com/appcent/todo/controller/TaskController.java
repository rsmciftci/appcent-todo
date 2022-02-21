package com.appcent.todo.controller;

import com.appcent.todo.dto.TaskDto;
import com.appcent.todo.dto.TaskUpdatingDto;
import com.appcent.todo.dto.TaskSavingDto;
import com.appcent.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/task")
@CrossOrigin
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService ;


    @PostMapping("")
    public ResponseEntity save(@RequestBody @Valid TaskSavingDto taskSavingDto) {
        TaskSavingDto savedTaskSavingDto = taskService.save(taskSavingDto);
        return ResponseEntity.ok(savedTaskSavingDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        taskService.delete(id);
    }

    @GetMapping("/{taskId}")
    public TaskDto findById(@PathVariable Long taskId){
        return taskService.findById(taskId);
    }

    @PutMapping("")
    public void update(@RequestBody @Valid TaskUpdatingDto taskUpdatingDto){
        taskService.update(taskUpdatingDto);
    }

    @GetMapping("/find-all-by-userId/{userId}")
    public List<TaskDto> findAllTasksOfUser(@PathVariable Long userId){
        List<TaskDto> taskDtoList = taskService.findAllByUserId(userId);
        return taskDtoList;
    }




}
