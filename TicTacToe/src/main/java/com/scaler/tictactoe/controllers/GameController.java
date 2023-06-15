package com.scaler.tictactoe.controllers;

import java.util.List;

import com.scaler.tictactoe.models.Game;
import com.scaler.tictactoe.models.GameStatus;
import com.scaler.tictactoe.models.Player;

public class GameController {
    
    public Game createGame(int BoardSize, List<Player> players) {
        return null;
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public void printBoard(Game game) {
        game.displayBoard();
    }

    public void undo(Game game) {}

    public void executeNextMove(Game game) {
        game.executeNextMove();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

}
