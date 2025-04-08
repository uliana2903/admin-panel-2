package com.example.demo.service.dto;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreatePlayerDto {
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Long birthday;
}
