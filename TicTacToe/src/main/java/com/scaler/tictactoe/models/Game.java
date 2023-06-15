package models;

import exceptions.InvalidGameConstructorException;
import strategies.gamewinningstrategy.GameWinningStrategy;
import strategies.gamewinningstrategy.OrderOneWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private GameWinningStrategy gameWinningStrategy;
    private Player winner;

    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }
    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    private Game() {}

    public static Builder getBuilder() {
        return new Builder();
    }

    public void undo() {}

    public void makeNextMove() {}

    public void displayBoard(){
        this.board.display();
    }

    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }
    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public void executeNextMove() {
        Player toMovePlayer = players.get(nextPlayerIndex);
        System.out.println("Player "+toMovePlayer.getName()+" to move");

        Move move = toMovePlayer.makeMove(this.board);

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(players.get(nextPlayerIndex));

        this.moves.add(move);

        if (gameWinningStrategy.checkWinner(board, toMovePlayer, move.getCell())) {
            this.gameStatus = GameStatus.ENDED;
            this.winner = toMovePlayer;
        }

        nextPlayerIndex = (nextPlayerIndex + 1) % players.size();
    }

    public static class Builder {
        private int boardSize;
        private List<Player> players;

        public Builder setBoard(int boardSize) {
            this.boardSize = boardSize;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public boolean valid() throws Exception {
            if (this.boardSize < 3) {
                throw new InvalidGameConstructorException("Board size should be at least 3x3");
            }
            else if (this.players.size() < 2) {
                throw new InvalidGameConstructorException("Players should be atleast 2");
            }
            return true;
        }

        public Game build() throws Exception {
            try {
                valid();
            }
            catch (Exception e) {
                throw new InvalidGameConstructorException(e.getMessage());
            }

            Game game = new Game();
            game.setBoard(new Board(this.boardSize));
            game.setPlayers(this.players);
            game.setMoves(new ArrayList<>());
            game.setNextPlayerIndex(0);
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setGameWinningStrategy(new OrderOneWinningStrategy(boardSize));
            return game;

        }
    }

}
