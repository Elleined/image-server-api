package com.elleined.file_server_api.service.file.deleted.db;

import com.elleined.file_server_api.exception.resource.ResourceNotFoundException;
import com.elleined.file_server_api.exception.resource.ResourceNotOwnedException;
import com.elleined.file_server_api.model.file.DeletedFile;
import com.elleined.file_server_api.model.folder.Folder;
import com.elleined.file_server_api.model.project.Project;
import com.elleined.file_server_api.repository.file.DeletedFileRepository;
import com.elleined.file_server_api.service.file.deleted.local.LocalDeletedFileService;
import com.elleined.file_server_api.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DBDeletedFileServiceImpl implements DBDeletedFileService {
    private final DeletedFileRepository deletedFileRepository;

    private final ProjectService projectService;

    private final LocalDeletedFileService localDeletedFileService;

    @Override
    public DeletedFile getByUUID(Project project, Folder folder, UUID uuid) {
        if (!projectService.has(project, folder))
            throw new ResourceNotOwnedException("Cannot get image by uuid! because this project doesn't have the specified upload folder");

        DeletedFile deletedImage = deletedFileRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Deleted image with uuid of " + uuid + " does not exists!"));

        deletedImage.setLastAccessedAt(LocalDateTime.now());
        deletedFileRepository.save(deletedImage);

        return deletedImage;
    }

    @Override
    public Page<DeletedFile> getAll(Project project, Folder folder, Pageable pageable) {
        if (!projectService.has(project, folder))
            throw new ResourceNotOwnedException("Cannot get all deleted images! because this project doesn't have this folder!");

        return deletedFileRepository.findAll(folder, pageable);
    }

    @Override
    public void permanentlyDeleteDeletedImages() throws IOException {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);

        List<DeletedFile> deletedImages = deletedFileRepository.findAll().stream()
                .filter(deletedImage -> deletedImage.getLastAccessedAt().isBefore(oneMonthAgo) ||
                        deletedImage.getLastAccessedAt().equals(oneMonthAgo))
                .toList();

        localDeletedFileService.permanentlyDeleteDeletedImages(deletedImages);
        deletedFileRepository.deleteAll(deletedImages);
    }
}
