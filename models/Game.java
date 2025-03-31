package models;

import constants.GameStatus;
import constants.PlayerType;
import exception.DuplicateSymbolException;
import exception.InvalidBoardSizeException;
import exception.InvalidBotCountException;
import exception.InvalidNumberOfPlayerException;
import service.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private Board currentBoard;
    private List<Player> players;
    private Player currentPlayer;
    private GameStatus gameStatus;
    private Player winner;
    private List<Move> moves;
    private List<Board> boardSates;
    private WinningStrategy winningStrategy;

    public static Builder builder(){
        return new Builder();
    }

    public Game(Board currentBoard, List<Player> players, WinningStrategy winningStrategy) {
        this.currentBoard = currentBoard;
        this.players = players;
        this.winningStrategy=winningStrategy;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moves = new ArrayList<Move>();
        this.boardSates = new ArrayList<Board>();

    }

    public static class Builder{
       // private Board currentBoard;
        private int dimensions;
        private List<Player> players;
        private WinningStrategy winningStrategy;

        public Builder currentBoard(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder winningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }

        public void validateBotCount() throws InvalidBotCountException{
            int botCount=0;
            for(Player player:players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount>1){
                throw new InvalidBotCountException("Bot count can not be more tha 1, curently "+ botCount);
            }
        }

        public void validateBoardSize() throws InvalidBoardSizeException{
            if(dimensions<3 || dimensions>10){
                throw new InvalidBoardSizeException("Board size should be >=3 and <=10, current board size "+dimensions);
            }
        }

        public void validatePlayerNumber() throws InvalidNumberOfPlayerException{
            if(players.size()!=(dimensions-1)){
                throw new InvalidNumberOfPlayerException("Number of players is invalid, current count "+players.size());
            }
        }

        public void validateDuplicateSymbols() throws DuplicateSymbolException {
            HashSet<Character> symbolSet=new HashSet<>();
            for(Player player:players){
                symbolSet.add(player.getSymbol());
            }
            if(symbolSet.size()!= players.size()){
                throw new DuplicateSymbolException("All Players should have unique symbol");
            }
        }
        /*
        Lets say the number of players are -> 1 2 3 4 5  -> total 5
        Their symbols are -->                 A B C D D  -> total 4 due to hashset
         */

        public void validate() throws InvalidBoardSizeException, InvalidBotCountException, InvalidNumberOfPlayerException, DuplicateSymbolException {
            validateBoardSize();
            validateBotCount();
            validatePlayerNumber();
            validateDuplicateSymbols();
        }

        public Game build() throws InvalidBotCountException, InvalidNumberOfPlayerException, DuplicateSymbolException, InvalidBoardSizeException {
            validate();
            return new Game(new Board(dimensions), players, winningStrategy);
        }
    }
}
