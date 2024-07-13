package com.elleined.image_server_api.model.image;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cacheable
@org.hibernate.annotations.Cache(region = "deletedImageCache", usage = CacheConcurrencyStrategy.READ_WRITE)

@Entity
@Table(
        name = "tbl_deleted_image",
        indexes = {
                @Index(name = "created_at_idx", columnList = "created_at"),
                @Index(name = "file_name_idx", columnList = "file_name")
        }
)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class DeletedImage extends Image {
}
