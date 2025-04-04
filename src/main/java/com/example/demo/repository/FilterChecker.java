package com.example.demo.repository;

import com.example.demo.Player;
import com.example.demo.service.dto.GetPlayersCountService;

import java.util.Optional;
import java.util.function.Predicate;

public class FilterChecker implements Predicate <Player> {

    private final GetPlayersCountService getPlayersCountService;

    public FilterChecker(GetPlayersCountService getPlayersCountService) {
        this.getPlayersCountService = getPlayersCountService;
    }

    public boolean test(Player player) {
        return Optional.ofNullable(getPlayersCountService.getName())
                .map(name -> player.getName().toLowerCase().contains(getPlayersCountService.getName()))
                .orElse(true) &&

                Optional.ofNullable(getPlayersCountService.getTitle())
                        .map(title -> player.getTitle().toLowerCase().contains(getPlayersCountService.getTitle()))
                        .orElse(true) &&

                Optional.ofNullable(getPlayersCountService.getRace())
                        .map(race -> player.getRace().equals(getPlayersCountService.getRace()))
                        .orElse(true) &&

                Optional.ofNullable(getPlayersCountService.getProfession())
                        .map(profession -> player.getProfession().equals(getPlayersCountService.getProfession()))
                        .orElse(true) &&

                Optional.ofNullable(getPlayersCountService.getAfter())
                        .map(after -> player.getBirthday() >= getPlayersCountService.getAfter())
                        .orElse(true) &&

                Optional.ofNullable(getPlayersCountService.getBefore())
                        .map(before -> player.getBirthday() <= getPlayersCountService.getBefore())
                        .orElse(true) &&

                Optional.ofNullable(getPlayersCountService.getBanned())
                        .map(banned -> player.isBanned() == getPlayersCountService.getBanned())
                        .orElse(true) &&

                Optional.ofNullable(getPlayersCountService.getMinExperience())
                        .map(minExperience -> player.getExperience() >= getPlayersCountService.getMinExperience())
                        .orElse(true) &&

                Optional.ofNullable(getPlayersCountService.getMaxExperience())
                        .map(maxExperience -> player.getExperience() <= getPlayersCountService.getMaxExperience())
                        .orElse(true) &&

                Optional.ofNullable(getPlayersCountService.getMinLevel())
                        .map(minLevel -> player.getLevel() >= getPlayersCountService.getMinLevel())
                        .orElse(true) &&

                Optional.ofNullable(getPlayersCountService.getMaxLevel())
                        .map(maxLevel -> player.getLevel() <= getPlayersCountService.getMaxLevel())
                        .orElse(true);
    }
}
