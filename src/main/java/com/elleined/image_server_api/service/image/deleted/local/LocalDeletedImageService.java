package com.elleined.image_server_api.service.image.deleted.local;

import com.elleined.image_server_api.model.folder.Folder;
import com.elleined.image_server_api.model.image.DeletedImage;
import com.elleined.image_server_api.model.project.Project;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LocalDeletedImageService {
    byte[] getImage(Project project, Folder folder, String fileName) throws IOException;
    void transfer(Project project, Folder folder, MultipartFile multipartFile) throws IOException;
    void permanentlyDeleteDeletedImages(List<DeletedImage> deletedImages) throws IOException;
}
