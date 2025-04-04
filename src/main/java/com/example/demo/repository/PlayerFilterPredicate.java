package com.example.demo.repository;

import com.example.demo.Player;
import com.example.demo.service.dto.FilterPlayerService;

import java.util.Optional;
import java.util.function.Predicate;

public class PlayerFilterPredicate implements Predicate<Player> {
    private final FilterPlayerService filterPlayerService;

    public PlayerFilterPredicate(FilterPlayerService filterPlayerService) {
        this.filterPlayerService = filterPlayerService;
    }

    @Override
    public boolean test(Player player) {
        return
                Optional.ofNullable(filterPlayerService.getName())
                        .map(name -> player.getName().toLowerCase().contains(filterPlayerService.getName()))
                        .orElse(true) &&

                Optional.ofNullable(filterPlayerService.getTitle())
                        .map(title -> player.getTitle().toLowerCase().contains(filterPlayerService.getTitle()))
                        .orElse(true) &&

                Optional.ofNullable(filterPlayerService.getRace())
                        .map(race -> player.getRace().equals(filterPlayerService.getRace()))
                        .orElse(true) &&

                Optional.ofNullable(filterPlayerService.getProfession())
                        .map(profession -> player.getProfession().equals(filterPlayerService.getProfession()))
                        .orElse(true) &&

                Optional.ofNullable(filterPlayerService.getAfter())
                        .map(after -> player.getBirthday() >= filterPlayerService.getAfter())
                        .orElse(true) &&

                Optional.ofNullable(filterPlayerService.getBefore())
                        .map(before -> player.getBirthday() <= filterPlayerService.getBefore())
                        .orElse(true) &&

                Optional.ofNullable(filterPlayerService.getBanned())
                        .map(banned -> player.isBanned() == filterPlayerService.getBanned())
                        .orElse(true) &&

                Optional.ofNullable(filterPlayerService.getMinExperience())
                        .map(minExperience -> player.getExperience() >= filterPlayerService.getMinExperience())
                        .orElse(true) &&

                Optional.ofNullable(filterPlayerService.getMaxExperience())
                        .map(maxExperience -> player.getExperience() <= filterPlayerService.getMaxExperience())
                        .orElse(true) &&

                Optional.ofNullable(filterPlayerService.getMinLevel())
                        .map(minLevel -> player.getLevel() >= filterPlayerService.getMinLevel())
                        .orElse(true) &&

                Optional.ofNullable(filterPlayerService.getMaxLevel())
                        .map(maxLevel -> player.getLevel() <= filterPlayerService.getMaxLevel())
                        .orElse(true);
    }
}
