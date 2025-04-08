package com.example.demo.repository;

import com.example.demo.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.exceptions.PlayerNotFoundException;
import com.example.demo.service.dto.FilterPlayerDto;
import com.example.demo.service.dto.GetPlayersCountDto;
import com.example.demo.service.dto.UpdatePlayerDto;
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
        playersList.add(new Player(idSequence++, "hjerjlfk", "hverjk", Race.ELF, Profession.CLERIC, 0, 0, 100, 10000L, false));
        playersList.add(new Player(idSequence++, "lknjlk", "kjnkl", Race.HOBBIT, Profession.CLERIC, 0, 0, 100, 30000L, false));
        playersList.add(new Player(idSequence++, "hjerjlfk", "hverjk", Race.ELF, Profession.DRUID, 0, 0, 100, 20000L, false));
        playersList.add(new Player(idSequence++, "bhjkhk", "dece", Race.DWARF, Profession.WARLOCK, 0, 0, 100, 15000L, false));
    }

    public Player update(Player player, UpdatePlayerDto updatePlayerDto) {
        if (updatePlayerDto.getName() != null) {
            player.setName(updatePlayerDto.getName());
        }

        if (updatePlayerDto.getTitle() != null) {
            player.setTitle(updatePlayerDto.getTitle());
        }

        if (updatePlayerDto.getRace() != null) {
            player.setRace(updatePlayerDto.getRace());
        }

        if (updatePlayerDto.getProfession() != null) {
            player.setProfession(updatePlayerDto.getProfession());
        }

        if (updatePlayerDto.getBirthday() != null) {
            player.setBirthday(updatePlayerDto.getBirthday());
        }

        if (updatePlayerDto.getBanned() != null) {
            player.setBanned(updatePlayerDto.getBanned());
        }
        if (updatePlayerDto.getExperience() != null) {
            player.setExperience(updatePlayerDto.getExperience());
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

    public List<Player> getListByFilter(FilterPlayerDto filterPlayerDto) {
        int pageSize = Optional.ofNullable(filterPlayerDto.getPageSize()) //
                .orElse(3); //количество элементов на текущей странице
        int pageNumber = Optional.ofNullable(filterPlayerDto.getPageNumber())
                .orElse(0); //номер текущей страницы
        return playersList.stream()
                .filter(new PlayerFilterPredicate(filterPlayerDto))
                .sorted(new PlayerComparator(filterPlayerDto.getOrder()))
                .skip((long) pageSize * pageNumber) //пропустит первые 3 если null //используется для пропуска элементов предыдущих страниц
                .limit(pageSize) //выдаст следующие 3 //используется для определения количества элементов на странице
                .toList();
    }

    public int getPlayersCount(GetPlayersCountDto getPlayersCountDto) {
        return (int) playersList.stream()
                .filter(new PlayerFilterPredicate(getPlayersCountDto))
                .count();
    }
}

