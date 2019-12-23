package com.user.management.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {

    private Integer id;

    @NotBlank(message = "Name must be specified")
    private String name;

}
