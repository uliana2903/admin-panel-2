package com.example.demo.repository;


import com.example.demo.Player;
import com.example.demo.service.dto.FilterPlayerService;
import com.example.demo.service.dto.GetPlayersCountService;
import com.example.demo.service.dto.UpdatePlayerService;

import java.util.List;

public interface PlayerRepository {
    Player update(Player player, UpdatePlayerService updatePlayerService);

    void save(Player player);

    Player findById(long id);

    void delete(long id);

    List<Player> getListByFilter(FilterPlayerService filterPlayerService);

    int getPlayersCount(GetPlayersCountService getPlayersCountService);
}

