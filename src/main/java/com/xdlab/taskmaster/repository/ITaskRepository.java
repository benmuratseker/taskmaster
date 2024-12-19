package com.xdlab.taskmaster.repository;

import com.xdlab.taskmaster.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<Task, Long> {

}
