package service.winningStrategy;

import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy {

    private int dimension;
    private List<HashMap<Character, Integer>> rowHashMap;
    private List<HashMap<Character, Integer>> colHashMap;
    private HashMap<Character, Integer> topLeftHashMap;
    private HashMap<Character, Integer> topRightHashMap;
    private HashMap<Character, Integer> cornerHashMap;


    public OrderOneWinningStrategy(int dimension) {
        rowHashMap = new ArrayList<>();
        colHashMap = new ArrayList<>();
        topLeftHashMap = new HashMap<>();
        topRightHashMap = new HashMap<>();
        cornerHashMap = new HashMap<>();
        for (int i = 0; i < dimension; i++) {
            rowHashMap.add(new HashMap<>());
            colHashMap.add(new HashMap<>());
        }
    }

    public boolean isTopLeftDiagonalCell(int row, int col) {
        return row == col;
    }

    public boolean isTopRightDiagonalCell(int row, int col) {
        return (row + col) == dimension;
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        Player player = lastMove.getPlayer();
        char symbol = player.getSymbol();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();

        if (checkRowWin(row, symbol))
            return player;
        else if (checkColWin(row, symbol))
            return player;
        else if (isTopLeftDiagonalCell(row, col) && checkTopRightDiagonalWin(symbol)) {
            return player;
        } else if (isTopRightDiagonalCell(row, col) && checkTopLeftDiagonalWin(symbol)) {
            return player;
        } else if (checkCornerWin(symbol))
            return player;

        return null;
    }

    public boolean checkRowWin(int row,char symbol) {
        //if the symbol is not existing, insert in map
        rowHashMap.get(row).putIfAbsent(symbol,0);
        //for every insertion of symbol update the count
        rowHashMap.get(row).put(symbol, rowHashMap.get(row).get(symbol)+1);
        //if the symbol count is equal to dimension then return true else false.
        return rowHashMap.get(row).get(symbol)==dimension;
    }

    public boolean checkColWin(int col, char symbol) {
        //if the symbol is not existing, insert in map
        colHashMap.get(col).putIfAbsent(symbol,0);
        //for every insertion of symbol update the count
        colHashMap.get(col).put(symbol, colHashMap.get(col).get(symbol)+1);
        //if the symbol count is equal to dimension then return true else false.
        return colHashMap.get(col).get(symbol)==dimension;
    }

    public boolean checkTopRightDiagonalWin(char symbol) {
        //if the symbol is not existing, insert in map
        topRightHashMap.putIfAbsent(symbol,0);
        //for every insertion of symbol update the count
        topRightHashMap.put(symbol, topRightHashMap.get(symbol)+1);
        //if the symbol count is equal to dimension then return true else false.
        return topRightHashMap.get(symbol)==dimension;
    }

    public boolean checkTopLeftDiagonalWin(char symbol) {
        //if the symbol is not existing, insert in map
        topLeftHashMap.putIfAbsent(symbol,0);
        //for every insertion of symbol update the count
        topLeftHashMap.put(symbol, topLeftHashMap.get(symbol)+1);
        //if the symbol count is equal to dimension then return true else false.
        return topLeftHashMap.get(symbol)==dimension;
    }

    public boolean checkCornerWin(char symbol) {
        //if the symbol is not existing, insert in map
        cornerHashMap.putIfAbsent(symbol,0);
        //for every insertion of symbol update the count
        cornerHashMap.put(symbol, cornerHashMap.get(symbol)+1);
        //if the symbol count is equal to dimension then return true else false.
        return cornerHashMap.get(symbol)==4;
    }
}
