package com.example.demo.service;

import com.example.demo.Player;
import com.example.demo.controllers.dto.*;
import com.example.demo.exceptions.PlayerNotFoundException;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.service.dto.CreatePlayerService;
import com.example.demo.service.dto.FilterPlayerService;
import com.example.demo.service.dto.GetPlayersCountService;
import com.example.demo.service.dto.UpdatePlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public GetPlayerResponse getPlayer(long id) {
        Player player = playerRepository.findById(id);
        if (player == null) {
            throw new PlayerNotFoundException("Игрок не найден");
        }
        return new GetPlayerResponse(player.getId(), player.getName(), player.getTitle(), player.getRace(),
                player.getProfession(), player.getBirthday(), player.isBanned(), player.getExperience(),
                player.getLevel(), player.getUntilNextLevel());
    }

    public int getPlayersCount(GetPlayersCountService getPlayersCountService) {
        return playerRepository.getPlayersCount(getPlayersCountService);
    }

    public CreatePlayerResponse createPlayer(CreatePlayerService createPlayerService) {
        int experience = 0;
        Player player = new Player (null, createPlayerService.getName(),
                createPlayerService.getTitle(),
                createPlayerService.getRace(),
                createPlayerService.getProfession(),
                experience, calculateLevel(experience), calculateExperienceUntilNextLevel(experience),
                createPlayerService.getBirthday(),
                false);

        playerRepository.save(player);

        return new CreatePlayerResponse(player.getId(),
                player.getName(), player.getTitle(), player.getRace(), player.getProfession(), player.getExperience(),
                player.getLevel(), player.getUntilNextLevel(), player.getBirthday(), player.isBanned());
    }

    public UpdatePlayerResponse updatePlayer(long id, UpdatePlayerService updatePlayerService) {
        Player player = playerRepository.findById(id);
        if (player == null) {
            throw new PlayerNotFoundException("Игрок не найден");
        }
        if (updatePlayerService.getExperience() != null){
            player.setExperience(updatePlayerService.getExperience());
            player.setLevel(calculateLevel(updatePlayerService.getExperience()));
            player.setUntilNextLevel(calculateExperienceUntilNextLevel(updatePlayerService.getExperience()));
        }
        playerRepository.update(player, updatePlayerService);
        return new UpdatePlayerResponse(player.getId(), player.getName(), player.getTitle(), player.getRace(),
                player.getProfession(), player.getBirthday(), player.isBanned(), player.getExperience(),
                player.getLevel(), player.getUntilNextLevel());
    }

    public void deletePlayer(long id) {
        playerRepository.delete(id);
    }

    public List<FilterPlayerResponse> getListByFilter(FilterPlayerService filterPlayerService){
        List<Player> players = playerRepository.getListByFilter(filterPlayerService);
        List<FilterPlayerResponse> responseList= new ArrayList<>();
        for (Player player : players) {
            FilterPlayerResponse filterPlayerResponse = new FilterPlayerResponse(player.getId(),
                    player.getName(), player.getTitle(), player.getRace(), player.getProfession(),
                    player.getBirthday(), player.isBanned(), player.getExperience(),
                    player.getLevel(), player.getUntilNextLevel()
            );
            responseList.add(filterPlayerResponse);
        }
        return responseList;
    }

    public int calculateLevel(int experience) {
        return (int)((Math.sqrt(2500 + 200 * experience) - 50) / 100);
    }

    public int calculateExperienceUntilNextLevel(int experience) {
        int level = calculateLevel(experience);
        return 50 * (level + 1) * (level + 2) - experience;
    }
}
