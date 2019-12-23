package com.user.management.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Integer id;

    @NotBlank(message = "Title must be specified")
    private String title;

    @NotBlank(message = "Content must be specified")
    private String content;

    private List<Integer> tagIds;
}
