package com.appcent.todo.converter;

import com.appcent.todo.dto.TaskDto;
import com.appcent.todo.dto.TaskUpdatingDto;
import com.appcent.todo.dto.TaskSavingDto;
import com.appcent.todo.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "user.id", target = "userId")
    TaskSavingDto convertTaskToTaskSavingDto(Task task);

    @Mapping(target = "user.id", source = "userId")
    Task convertTaskSavingDtoToTask(TaskSavingDto taskSavingDto);

    @Mapping(target = "user.id", source = "userId")
    Task convertTaskUpdatingDtoToTask(TaskUpdatingDto taskUpdatingDto);

    @Mapping(source = "user.id", target = "userId")
    TaskUpdatingDto convertTaskToTaskUpdatingDto(Task task);

    @Mapping(source = "user.id", target = "userId" )
    TaskDto convertTaskToTaskDto(Task task);

    @Mapping(source = "user.id", target = "userId" )
    List<TaskDto> convertTaskListToTaskDtoList(List<Task> taskList);
}
