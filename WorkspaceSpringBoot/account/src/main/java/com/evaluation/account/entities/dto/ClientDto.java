package com.evaluation.account.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Integer id;
    private Integer identify;
    private String name;
    private String gender;
    private Integer age;
    private String direction;
    private Integer phone;
    private String password;
    private Boolean status;
}
