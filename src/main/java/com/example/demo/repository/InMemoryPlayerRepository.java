package com.example.demo.repository;

import com.example.demo.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.exceptions.PlayerNotFoundException;
import com.example.demo.filter.PlayerOrder;
import com.example.demo.service.dto.FilterPlayerService;
import com.example.demo.service.dto.GetPlayersCountService;
import com.example.demo.service.dto.UpdatePlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@ConditionalOnProperty(name = "repository.type", havingValue = "InMemoryPlayerRepository")
public class InMemoryPlayerRepository implements PlayerRepository {
    private final List<Player> playersList;
    private static long idSequence = 0;

    public InMemoryPlayerRepository() {
        this.playersList = new ArrayList<>();
    }

    public Player update(Player player, UpdatePlayerService updatePlayerService) {
        if (updatePlayerService.getName() != null) {
            player.setName(updatePlayerService.getName());
        }

        if (updatePlayerService.getTitle() != null) {
            player.setTitle(updatePlayerService.getTitle());
        }

        if (updatePlayerService.getRace() != null) {
            player.setRace(updatePlayerService.getRace());
        }

        if (updatePlayerService.getProfession() != null) {
            player.setProfession(updatePlayerService.getProfession());
        }

        if (updatePlayerService.getBanned() != null) {
            player.setBanned(updatePlayerService.getBanned());
        }
        save(player);
        return player;
    }

    public void save(Player player) {
        ListIterator<Player> iterator = playersList.listIterator();
        while (iterator.hasNext()) {
            Player currentPlayer = iterator.next();
            if (player.getId() == currentPlayer.getId()) {
                iterator.set(player);
                return;
            }
        }
        player.setId(idSequence++);
        playersList.add(player);
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public Player findById(long id) {
        for (Player player : playersList) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    public void delete(long id) {
        boolean deleted = playersList.removeIf(player -> player.getId() == id);
        if (!deleted) {
            throw new PlayerNotFoundException("Игрок не найден");
            };
        }

    public List<Player> getListByFilter(FilterPlayerService filterPlayerService) {
        int pageSize = Optional.ofNullable(filterPlayerService.getPageSize()) //
                .orElse(3); //количество элементов на текущей странице
        int pageNumber = Optional.ofNullable(filterPlayerService.getPageNumber())
                .orElse(0); //номер текущей страницы
        return playersList.stream()
                .filter(new PlayerFilterPredicate(filterPlayerService))
                .sorted(new PlayerComparator(filterPlayerService.getPlayerOrder()))
                .skip((long) pageSize * pageNumber) //пропустит первые 3 если null //используется для пропуска элементов предыдущих страниц
                .limit(pageSize) //выдаст следующие 3 //используется для определения количества элементов на странице
                .toList();
    }

    public int getPlayersCount(GetPlayersCountService getPlayersCountService) {
        return (int) playersList.stream()
                .filter(new FilterChecker(getPlayersCountService))
                .count();
    }
}

