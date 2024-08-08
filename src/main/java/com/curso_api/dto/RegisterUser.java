package com.curso_api.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser implements Serializable {

    private Long id;
    private String username;
    private String name;
    private String role;
    private String jwt;

}
