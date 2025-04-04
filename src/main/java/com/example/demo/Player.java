package com.example.demo;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;

@Data
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

    public Player(Long id, String name,
                  String title, Race race,
                  Profession profession, int experience,
                  int level, int untilNextLevel, long birthday, boolean banned) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
        this.birthday = birthday;
        this.banned = banned;
    }
}
