package com.spring.repository;

import com.spring.entity.Project;
import com.spring.entity.User;
import com.spring.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByProjectCode(String code);

    List<Project> findAllByAssignedManager(User manager);

    List<Project> findAllByProjectStatusIsNot(Status status);
}
