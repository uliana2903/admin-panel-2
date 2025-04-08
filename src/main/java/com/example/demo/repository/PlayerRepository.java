package com.example.demo.repository;


import com.example.demo.Player;
import com.example.demo.service.dto.FilterPlayerDto;
import com.example.demo.service.dto.GetPlayersCountDto;
import com.example.demo.service.dto.UpdatePlayerDto;

import java.util.List;

public interface PlayerRepository {
    Player update(Player player, UpdatePlayerDto updatePlayerDto);

    void save(Player player);

    Player findById(long id);

    void delete(long id);

    List<Player> getListByFilter(FilterPlayerDto filterPlayerDto);

    int getPlayersCount(GetPlayersCountDto getPlayersCountDto);
}

