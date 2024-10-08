package com.elleined.file_server_api.mapper.file;

import com.elleined.file_server_api.dto.file.DeletedFileDTO;
import com.elleined.file_server_api.mapper.CustomMapper;
import com.elleined.file_server_api.mapper.folder.FolderMapper;
import com.elleined.file_server_api.model.file.ActiveFile;
import com.elleined.file_server_api.model.file.DeletedFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {
                FolderMapper.class,
        }
)
public interface DeletedFileMapper extends CustomMapper<DeletedFile, DeletedFileDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),
            @Mapping(target = "lastAccessedAt", source = "lastAccessedAt"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "additionalInformation", source = "additionalInformation"),
            @Mapping(target = "fileName", source = "fileName"),
            @Mapping(target = "fileSizeInMB", source = "fileSizeInMB"),
            @Mapping(target = "folderDTO", source = "folder")
    })
    DeletedFileDTO toDTO(DeletedFile deletedImage);

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
    DeletedFile toEntity(ActiveFile activeImage);
}
