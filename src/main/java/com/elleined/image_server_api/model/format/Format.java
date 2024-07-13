package com.elleined.image_server_api.model.format;


import com.elleined.image_server_api.model.PrimaryKeyIdentity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cacheable
@org.hibernate.annotations.Cache(region = "formatCache", usage = CacheConcurrencyStrategy.READ_WRITE)

@Entity
@Table(
        name = "tbl_format",
        indexes = @Index(name = "format_idx", columnList = "format")
)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Format extends PrimaryKeyIdentity {

    @Column(
            name = "format",
            nullable = false,
            unique = true
    )
    private String format;
}
