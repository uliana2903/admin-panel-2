package com.example.demo;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private Long id;
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private int experience;
    private int level;
    private int untilNextLevel;
    private Long birthday;
    private boolean banned;
}
