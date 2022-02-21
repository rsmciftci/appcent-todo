package com.appcent.todo.service;

import com.appcent.todo.converter.TaskMapper;
import com.appcent.todo.dao.TaskDao;
import com.appcent.todo.dataprovider.TaskDataProvider;
import com.appcent.todo.dto.TaskDto;
import com.appcent.todo.dto.TaskSavingDto;
import com.appcent.todo.dto.TaskUpdatingDto;
import com.appcent.todo.entity.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskDao taskDao;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldSave() {

        TaskSavingDto taskSavingDto = TaskDataProvider.getTaskSavingDto();
        Task task = TaskMapper.INSTANCE.convertTaskSavingDtoToTask(taskSavingDto);
        task.setCreationDateTime(LocalDateTime.now());
        task.setDone(false);
        when(taskDao.save(ArgumentMatchers.any(Task.class))).thenReturn(task);
        TaskSavingDto resultOfSaving = taskService.save(taskSavingDto);
        assertEquals(taskSavingDto,resultOfSaving);
    }

    @Test
    void shouldDelete() {
        Task task = TaskDataProvider.getTask();
        when(taskDao.findById(1L)).thenReturn(Optional.of(task));
        assertFalse(Optional.of(task).isEmpty());
        taskService.delete(1L);
        verify(taskDao).deleteById(1L);
    }


    @Test
    void shouldUpdate() {
        TaskUpdatingDto taskUpdatingDto = TaskDataProvider.getTaskUpdatingDto();
        Task task = TaskMapper.INSTANCE.convertTaskUpdatingDtoToTask(taskUpdatingDto);
        task.setUpdatedDateTime(LocalDateTime.now());
        when(taskDao.save(ArgumentMatchers.any(Task.class))).thenReturn(task);

        TaskUpdatingDto result = taskService.update(taskUpdatingDto);
        assertEquals(taskUpdatingDto,result);
    }

    @Test
    void findAllByUserId() {

        List<Task> taskList = new ArrayList<>();
        when(taskDao.findAllByUserId(1L)).thenReturn(taskList);
        List<TaskDto> taskDtoList = TaskMapper.INSTANCE.convertTaskListToTaskDtoList(taskList);

        List<TaskDto> result = taskService.findAllByUserId(1L);
        assertEquals(taskDtoList,result);

    }

    @Test
    void findById() {
        Task task = TaskDataProvider.getTask();
        when(taskDao.findById(1L)).thenReturn(Optional.of(task));
        assertFalse(Optional.of(task).isEmpty());
        TaskDto taskDto = TaskMapper.INSTANCE.convertTaskToTaskDto(task);

        TaskDto result = taskService.findById(1L);
        assertEquals(taskDto,result);

    }

}