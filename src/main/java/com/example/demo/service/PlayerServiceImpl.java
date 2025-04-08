package com.example.demo.service;

import com.example.demo.Player;
import com.example.demo.controllers.dto.*;
import com.example.demo.exceptions.PlayerNotFoundException;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public GetCreatedPlayerDto getPlayer(long id) {
        Player player = playerRepository.findById(id);
        if (player == null) {
            throw new PlayerNotFoundException("Игрок не найден");
        }
        return new GetCreatedPlayerDto(player.getId(),
                player.getName(), player.getTitle(), player.getRace(), player.getProfession(), player.getExperience(),
                player.getLevel(), player.getUntilNextLevel(), player.getBirthday(), player.isBanned());
    }

    public int getPlayersCount(GetPlayersCountDto getPlayersCountDto) {
        return playerRepository.getPlayersCount(getPlayersCountDto);
    }

    public GetCreatedPlayerDto createPlayer(CreatePlayerDto createPlayerDto) {
        int experience = 0;
        Player player = new Player (null, createPlayerDto.getName(),
                createPlayerDto.getTitle(),
                createPlayerDto.getRace(),
                createPlayerDto.getProfession(),
                experience, calculateLevel(experience), calculateExperienceUntilNextLevel(experience),
                createPlayerDto.getBirthday(),
                false);

        playerRepository.save(player);

        return new GetCreatedPlayerDto(player.getId(),
                player.getName(), player.getTitle(), player.getRace(), player.getProfession(), player.getExperience(),
                player.getLevel(), player.getUntilNextLevel(), player.getBirthday(), player.isBanned());
    }

    public GetUpdatedPlayerDto updatePlayer(long id, UpdatePlayerDto updatePlayerDto) {
        Player player = playerRepository.findById(id);
        if (player == null) {
            throw new PlayerNotFoundException("Игрок не найден");
        }
        if (updatePlayerDto.getExperience() != null){
            player.setExperience(updatePlayerDto.getExperience());
            player.setLevel(calculateLevel(updatePlayerDto.getExperience()));
            player.setUntilNextLevel(calculateExperienceUntilNextLevel(updatePlayerDto.getExperience()));
        }
        playerRepository.update(player, updatePlayerDto);
        return new GetUpdatedPlayerDto(player.getId(), player.getName(), player.getTitle(), player.getRace(),
                player.getProfession(), player.getBirthday(), player.isBanned(), player.getExperience(),
                player.getLevel(), player.getUntilNextLevel());
    }

    public void deletePlayer(long id) {
        playerRepository.delete(id);
    }

    public List<GetFilteredListDto> getListByFilter(FilterPlayerDto filterPlayerDto){
        List<Player> players = playerRepository.getListByFilter(filterPlayerDto);
        List<GetFilteredListDto> filteredList = new ArrayList<>();
        for (Player player : players) {
            GetFilteredListDto getFilteredListDto = new GetFilteredListDto(player.getId(),
                    player.getName(), player.getTitle(), player.getRace(), player.getProfession(),
                    player.getBirthday(), player.isBanned(), player.getExperience(),
                    player.getLevel(), player.getUntilNextLevel());
            filteredList.add(getFilteredListDto);
        }
        return filteredList;
    }

    public int calculateLevel(int experience) {
        return (int)((Math.sqrt(2500 + 200 * experience) - 50) / 100);
    }

    public int calculateExperienceUntilNextLevel(int experience) {
        int level = calculateLevel(experience);
        return 50 * (level + 1) * (level + 2) - experience;
    }
}
