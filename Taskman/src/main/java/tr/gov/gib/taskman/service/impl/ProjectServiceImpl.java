package tr.gov.gib.taskman.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.gov.gib.taskman.dao.ProjectRepository;
import tr.gov.gib.taskman.dao.model.Project;
import tr.gov.gib.taskman.dao.model.Workitem;
import tr.gov.gib.taskman.dto.ProjectDto;
import tr.gov.gib.taskman.dto.WorkitemDto;
import tr.gov.gib.taskman.service.ProjectService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProjectDto create(ProjectDto projectDto) {
        Project project=mapToEntity(projectDto);
        Project newProject= projectRepository.save(project);
        ProjectDto projectResponse= mapToDTO(newProject);
        return projectResponse;
    }

    @Override
    public ProjectDto getByProjectId(Long id) {
        Project project= projectRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Project not found"+id));
        return mapToDTO(project);
    }

    @Override
    public List<ProjectDto> getProjects() {
        List<Project> projects= projectRepository.findAll();
        return  projects.stream().map((project) -> mapToDTO(project)).collect(Collectors.toList());

    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public ProjectDto update(ProjectDto projectDto) {
        Project project= projectRepository.findById(projectDto.getId()).orElseThrow(()->new EntityNotFoundException("Project not found "+projectDto.getId()));
        modelMapper.map(projectDto,project);

        Project updatedProject= projectRepository.save(project);

        return  mapToDTO(updatedProject);
    }

    private Project mapToEntity(ProjectDto projectDto)
    {

        Project project=modelMapper.map(projectDto,Project.class);
        return project;
    }

    private ProjectDto mapToDTO(Project project)
    {
        ProjectDto projectDto=modelMapper.map(project,ProjectDto.class);
        return projectDto;
    }
}
