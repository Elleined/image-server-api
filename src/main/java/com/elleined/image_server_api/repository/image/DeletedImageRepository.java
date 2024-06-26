package com.elleined.image_server_api.repository.image;

import com.elleined.image_server_api.model.image.DeletedImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeletedImageRepository extends JpaRepository<DeletedImage, UUID> {
}