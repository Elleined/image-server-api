package com.elleined.image_server_api.service.project;

import com.elleined.image_server_api.exception.resource.ResourceNotFoundException;
import com.elleined.image_server_api.mapper.project.ProjectMapper;
import com.elleined.image_server_api.model.PrimaryKeyIdentity;
import com.elleined.image_server_api.model.PrimaryKeyUUID;
import com.elleined.image_server_api.model.image.ActiveImage;
import com.elleined.image_server_api.model.image.DeletedImage;
import com.elleined.image_server_api.model.project.Project;
import com.elleined.image_server_api.repository.project.ProjectRepository;
import com.elleined.image_server_api.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public Project save(String name) throws IOException {
        Project project = projectMapper.toEntity(name);
        projectRepository.save(project);
        log.debug("Saving project with name of {} success", name);
        return project;
    }

    @Override
    public Project getById(int id) {
        return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(STR."Project with id of \{id} does not exists!"));
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll().stream()
                .sorted(Comparator.comparing(PrimaryKeyIdentity::getId))
                .toList();
    }

    @Override
    public List<ActiveImage> getAllActiveImages(Project project) {
        return project.getActiveImages().stream()
                .sorted(Comparator.comparing(PrimaryKeyUUID::getCreatedAt).reversed())
                .toList();
    }

    @Override
    public List<DeletedImage> getAllDeletedImages(Project project) {
        return project.getDeletedImages().stream()
                .sorted(Comparator.comparing(PrimaryKeyUUID::getCreatedAt).reversed())
                .toList();
    }
}
