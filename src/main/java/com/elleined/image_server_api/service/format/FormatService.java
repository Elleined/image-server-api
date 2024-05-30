package com.elleined.image_server_api.service.format;

import com.elleined.image_server_api.model.format.Format;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface FormatService {
    Format save(String format);
    Format getById(int id);
    List<Format> getAll(Pageable pageable);

    Optional<Format> getByMultipart(MultipartFile image);

    boolean isAlreadyExists(String format);
    boolean isFileExtensionValid(MultipartFile image);

    default List<Format> saveAll(List<String> formats) {
        return formats.stream()
                .map(this::save)
                .toList();
    }
}
