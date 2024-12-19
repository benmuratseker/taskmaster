package com.xdlab.taskmaster.repository;

import com.xdlab.taskmaster.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<Project, Long> {

}
