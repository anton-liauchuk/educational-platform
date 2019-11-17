package com.user.management.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    // todo review id in DTO
    private Integer id;
    private String username;
    private String email;
}
