package com.curso_api.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveProduct implements Serializable {

    private String name;
    private BigDecimal price;
    private Long categoryId;
}
