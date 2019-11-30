package com.user.management.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {

    // todo review id in DTO
    private Integer id;

    @NotBlank(message = "Document name must be specified")
    private String name;
}
