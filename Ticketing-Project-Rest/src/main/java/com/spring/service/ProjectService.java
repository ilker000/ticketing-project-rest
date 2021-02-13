package com.spring.service;

import com.spring.dto.ProjectDTO;
import com.spring.entity.Project;
import com.spring.entity.User;

import java.util.List;

public interface ProjectService {

    ProjectDTO getByProjectCode(String code);

    List<ProjectDTO> listAllProjects();

    Project save(ProjectDTO dto);

    void update(ProjectDTO dto);

    void delete(String code);

    void complete(String projectCode);

    List<ProjectDTO> listAllProjectDetails();

    List<ProjectDTO> readAllByAssignedManager(User user);

    List<ProjectDTO> listAllNonCompletedProjects();
}
