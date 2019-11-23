package com.user.management.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

// todo issue with builder https://github.com/mapstruct/mapstruct/issues/1742
@Data
public class DocumentRequest {

    @NotBlank(message = "Document name must be specified")
    private String name;

}
