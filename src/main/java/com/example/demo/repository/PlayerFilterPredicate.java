package com.example.demo.repository;

import com.example.demo.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.service.dto.FilterPlayerDto;
import com.example.demo.service.dto.GetPlayersCountDto;

import java.util.Optional;
import java.util.function.Predicate;

public class PlayerFilterPredicate implements Predicate<Player> {

    private final String name;
    private final String title;
    private final Race race;
    private final Profession profession;
    private final Long after;
    private final Long before;
    private final Boolean banned;
    private final Integer minExperience;
    private final Integer maxExperience;
    private final Integer minLevel;
    private final Integer maxLevel;

    public PlayerFilterPredicate(GetPlayersCountDto dto) {
        this.name = dto.getName();
        this.title = dto.getTitle();
        this.race = dto.getRace();
        this.profession = dto.getProfession();
        this.after = dto.getAfter();
        this.before = dto.getBefore();
        this.banned = dto.getBanned();
        this.minExperience = dto.getMinExperience();
        this.maxExperience = dto.getMaxExperience();
        this.minLevel = dto.getMinLevel();
        this.maxLevel = dto.getMaxLevel();
    }

    public PlayerFilterPredicate(FilterPlayerDto dto) {
        this.name = dto.getName();
        this.title = dto.getTitle();
        this.race = dto.getRace();
        this.profession = dto.getProfession();
        this.after = dto.getAfter();
        this.before = dto.getBefore();
        this.banned = dto.getBanned();
        this.minExperience = dto.getMinExperience();
        this.maxExperience = dto.getMaxExperience();
        this.minLevel = dto.getMinLevel();
        this.maxLevel = dto.getMaxLevel();
        }

        @Override
        public boolean test(Player player) {
            return Optional.ofNullable(name)
                    .map(name -> player.getName().toLowerCase().contains(name.toLowerCase()))
                    .orElse(true) &&

                    Optional.ofNullable(title)
                            .map(title -> player.getTitle().toLowerCase().contains(title.toLowerCase()))
                            .orElse(true) &&

                    Optional.ofNullable(race)
                            .map(race -> player.getRace().equals(race))
                            .orElse(true) &&

                    Optional.ofNullable(profession)
                            .map(profession -> player.getProfession().equals(profession))
                            .orElse(true) &&

                    Optional.ofNullable(after)
                            .map(after -> player.getBirthday() >= after)
                            .orElse(true) &&

                    Optional.ofNullable(before)
                            .map(before -> player.getBirthday() <= before)
                            .orElse(true) &&

                    Optional.ofNullable(banned)
                            .map(banned -> player.isBanned() == banned)
                            .orElse(true) &&

                    Optional.ofNullable(minExperience)
                            .map(minExperience -> player.getExperience() >= minExperience)
                            .orElse(true) &&

                    Optional.ofNullable(maxExperience)
                            .map(maxExperience -> player.getExperience() <= maxExperience)
                            .orElse(true) &&

                    Optional.ofNullable(minLevel)
                            .map(minLevel -> player.getLevel() >= minLevel)
                            .orElse(true) &&

                    Optional.ofNullable(maxLevel)
                            .map(maxLevel -> player.getLevel() <= maxLevel)
                            .orElse(true);
        }
}
