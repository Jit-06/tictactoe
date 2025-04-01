package controllers;

import constants.GameStatus;
import constants.WinningStrategies;
import exception.GameOverException;
import models.Board;
import models.Game;
import models.Move;
import models.Player;
import service.winningStrategy.WinningStrategyFactory;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players, WinningStrategies winningStrategy){
     try {
         return Game.builder()
                 .dimensions(dimension)
                 .players(players)
                 .winningStrategy(WinningStrategyFactory.getWinningStrategy(winningStrategy, dimension))
                 .build();
     }catch(Exception e){
         System.out.println("ERROR :" +e.getMessage());
         System.out.println("Could not start the game, something went wrong");
     }
     return null;
    }

    public void displayBoard(Game game){
        game.getCurrentBoard().printBoard();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public Player getGameWinner(Game game){
        return game.getWinner();
    }

    public Move executeMove(Game game, Player player) throws GameOverException {
        Move move=player.makeMove(game.getCurrentBoard());
        game.setNumberOfSymbols(game.getNumberOfSymbols()+1);
        updateGameStatus(game);
        updateGameMoves(game, move);
        updateBoardStates(game);
        return move;
    }

    public Player checkWinner(Game game, Move lastPLayerMove){
        Player player=game.getWinningStrategy().checkWinner(game.getCurrentBoard(), lastPLayerMove);
        if(player!=null){
            game.setWinner(player);
            game.setGameStatus(GameStatus.SUCCESS);
            return player;
        }
        return null;
    }

    public Board undoMove(Game game, Move lastMove){
        //TODO
        return null;
    }

    public void replayGame(Game game){
        //TODO
    }

    private void updateGameMoves(Game game, Move move){
        game.getMoves().add(move);
    }

    private void updateBoardStates(Game game){
        game.getBoardSates().add(new Board(game.getCurrentBoard()));
    }

    private void updateGameStatus(Game game){
        int numberOfSymbols=game.getNumberOfSymbols();
        int dimensions=game.getCurrentBoard().getSize();
        if(numberOfSymbols == (dimensions*dimensions)){
            game.setGameStatus(GameStatus.DRAW);
        }
    }
}
