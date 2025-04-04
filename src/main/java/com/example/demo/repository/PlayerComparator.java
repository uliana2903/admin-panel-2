package com.example.demo.repository;

import com.example.demo.Player;
import com.example.demo.filter.PlayerOrder;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
    private PlayerOrder playerOrder;

    public PlayerComparator(PlayerOrder playerOrder) {
        this.playerOrder = playerOrder;
    }

    @Override
    public int compare(Player player1, Player player2) {
        if (playerOrder == null) {
            playerOrder = PlayerOrder.ID;
        }
        return switch (playerOrder) {
            case ID -> Long.compare(player1.getId(), player2.getId());
            case NAME -> player1.getName().compareTo(player2.getName());
            case BIRTHDAY -> Long.compare(player1.getBirthday(), player2.getBirthday());
            case LEVEL -> Integer.compare(player1.getLevel(), player2.getLevel());
            case EXPERIENCE -> Integer.compare(player1.getExperience(), player2.getExperience());
        };
    }
}


