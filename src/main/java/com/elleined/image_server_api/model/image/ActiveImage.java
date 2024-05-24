package com.elleined.image_server_api.model.image;

import com.elleined.image_server_api.model.project.Project;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(
        name = "tbl_image",
        indexes = {
                @Index(name = "created_at_idx", columnList = "created_at"),
                @Index(name = "uuid_idx", columnList = "uuid")
        }
)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ActiveImage extends Image {

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "project_id",
            nullable = false,
            updatable = false
    )
    private Project project;

    @OneToMany(mappedBy = "activeImage")
    private List<ImageHistory> imageHistories;
}