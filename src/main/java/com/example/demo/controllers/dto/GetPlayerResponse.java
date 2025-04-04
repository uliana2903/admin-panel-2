package com.example.demo.controllers.dto;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;
@Data
public class GetPlayerResponse {
    private Long id;
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Long birthday;
    private Boolean banned;
    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;

    public GetPlayerResponse(Long id, String title, String name, Race race, Profession profession,
                                Long birthday, Boolean banned, Integer experience,
                                Integer level, Integer untilNextLevel) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.race = race;
        this.profession = profession;
        this.birthday = birthday;
        this.banned = banned;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
    }
}