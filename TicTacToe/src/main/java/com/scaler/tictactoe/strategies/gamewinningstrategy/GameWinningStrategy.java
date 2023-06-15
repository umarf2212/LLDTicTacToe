package strategies.gamewinningstrategy;

import models.Board;
import models.Player;
import models.Cell;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Player lastMovePlayer, Cell moveCell);
}
