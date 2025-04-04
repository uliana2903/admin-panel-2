package com.example.demo.controllers.dto;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class UpdatePlayerRequest {
    @Size(max = 12, message = "Максимальная длина имени - 12 символов")
    private String name;
    @Size(max = 30, message = "Максимальная длина титула - 30 символов")
    private String title;
    private Race race;
    private Profession profession;
    @Min(value = 946684800000L, message = "Укажите дату не ранее 2000-го года")
    @Max(value = 32503680000000L, message = "Укажите дату не позднее 3000-го года")
    private Long birthday;
    private Boolean banned;
    private Integer experience;
}