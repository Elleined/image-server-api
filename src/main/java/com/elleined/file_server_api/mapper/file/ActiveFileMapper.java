package com.elleined.file_server_api.mapper.file;

import com.elleined.file_server_api.dto.file.ActiveFileDTO;
import com.elleined.file_server_api.mapper.folder.FolderMapper;
import com.elleined.file_server_api.model.file.ActiveFile;
import com.elleined.file_server_api.model.file.DeletedFile;
import com.elleined.file_server_api.model.folder.Folder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {
                FolderMapper.class
        }
)
public interface ActiveFileMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),
            @Mapping(target = "lastAccessedAt", source = "lastAccessedAt"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "additionalInformation", source = "additionalInformation"),
            @Mapping(target = "fileName", source = "fileName"),
            @Mapping(target = "folderDTO", source = "folder"),
            @Mapping(target = "fileSizeInMB", source = "fileSizeInMB"),
            @Mapping(target = "bytes", expression = "java(bytes)")
    })
    ActiveFileDTO toDTO(ActiveFile activeImage,
                        @Context byte[] bytes);

    @Mappings({
            @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())"),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "lastAccessedAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "additionalInformation", source = "additionalInformation"),
            @Mapping(target = "fileName", source = "fileName"),
            @Mapping(target = "fileSizeInMB", source = "fileSizeInMB"),
            @Mapping(target = "folder", source = "folder")
    })
    ActiveFile toEntity(String description,
                        String additionalInformation,
                        String fileName,
                        Folder folder,
                        double fileSizeInMB);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),
            @Mapping(target = "lastAccessedAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "additionalInformation", source = "additionalInformation"),
            @Mapping(target = "fileName", source = "fileName"),
            @Mapping(target = "fileSizeInMB", source = "fileSizeInMB"),
            @Mapping(target = "folder", source = "folder")
    })
    ActiveFile toEntity(DeletedFile deletedImage); // For restoring deleted images;
}
