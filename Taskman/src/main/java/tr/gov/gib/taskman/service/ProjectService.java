package tr.gov.gib.taskman.service;

import tr.gov.gib.taskman.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto create(ProjectDto projectDto);
    ProjectDto getByProjectId(Long id);

    List<ProjectDto> getProjects();

    void delete(Long id);
    ProjectDto update(ProjectDto projectDto);

}
