package com.user.management.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {

    // todo review id in DTO
    private Integer id;

    @Size(min = 4, max = 255, message = "Minimum name length: 4 characters")
    private String name;
}
