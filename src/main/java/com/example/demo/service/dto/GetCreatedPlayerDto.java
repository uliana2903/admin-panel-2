package com.example.demo.service.dto;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class GetCreatedPlayerDto {
    private Long id;
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;
    private Long birthday;
    private Boolean banned;
}
