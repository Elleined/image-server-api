package com.elleined.image_server_api.dto.image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ActiveImageDTO extends ImageDTO {
    private int projectId;
}
