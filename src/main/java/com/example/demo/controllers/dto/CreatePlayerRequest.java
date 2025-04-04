package com.example.demo.controllers.dto;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CreatePlayerRequest {
    @NotBlank //аннотация для string
    @Size(max = 12, message = "Максимальная длина имени - 12 символов")
    private String name;
    @NotBlank
    @Size(max = 30, message = "Максимальная длина титула - 30 символов")
    private String title;
    @NotNull(message = "Данное поле не может быть пустым") //любой тип
    private Race race;
    @NotNull(message = "Данное поле не может быть пустым")
    private Profession profession;
    @NotNull(message = "Данное поле не может быть пустым")
    @Min(value = 946684800000L, message = "Укажите дату не ранее 2000-го года")
    @Max(value = 32503680000000L, message = "Укажите дату не позднее 3000-го года")
    private Long birthday;
}
