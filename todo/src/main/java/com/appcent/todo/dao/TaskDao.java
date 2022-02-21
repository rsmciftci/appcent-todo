package com.appcent.todo.dao;

import com.appcent.todo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskDao extends JpaRepository<Task,Long> {

    List<Task> findAllByUserId(Long userId);

}
