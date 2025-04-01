import constants.BotDifficultyLevel;
import constants.GameStatus;
import constants.PlayerType;
import constants.WinningStrategies;
import controllers.GameController;
import exception.GameOverException;
import models.Bot;
import models.Game;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws GameOverException {
        Scanner sc=new Scanner(System.in);
        GameController gameController=new GameController();

        System.out.println("Enter the dimensions of the game");
        int dimensions=sc.nextInt();

        System.out.println("Will there be any bot in the game ? Y/N");
        String isBotPresent=sc.next();

        List<Player> players=new ArrayList<>();
        int iteratorNumber=dimensions-1;

        if(isBotPresent.equalsIgnoreCase("Y")){
            iteratorNumber=iteratorNumber-1;
        }

        for(int i=0;i<iteratorNumber;i++){
            System.out.println("What is the name of the player, number : " + i);
            String playName=sc.next();

            System.out.println("What is the symbol of the player, number : " + i);
            String symbol=sc.next();

            players.add(new Player(i, playName, symbol.charAt(0), PlayerType.HUMAN));
        }

        if(isBotPresent.equalsIgnoreCase("Y")){
            System.out.println("What is the name of the BOT");
            String botName=sc.next();

            System.out.println("What is the symbol of the BOT");
            String botSymbol=sc.next();

            BotDifficultyLevel botDifficultyLevel=BotDifficultyLevel.EASY;
            Bot bot=new Bot(
                    dimensions,
                    botName,
                    botSymbol.charAt(0),
                    PlayerType.BOT,
                    botDifficultyLevel
            );
            players.add(bot);
        }

        //randomised the list of players, so order of playing is completely random
        Collections.shuffle(players);

        Game game= gameController.createGame(dimensions, players, WinningStrategies.ORDERONE_WINNINGSTRATEGY);
        int playerIndex=-1;
        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            System.out.println("Current Game Status");
            gameController.displayBoard(game);
            playerIndex++;
            playerIndex=playerIndex%players.size();
            Move movePlayed=gameController.executeMove(game, players.get(playerIndex));
//            System.out.println("Do you want to undo your last move ? Y?N");
//            String isUndoRequired=sc.next();
//            if(isUndoRequired.equalsIgnoreCase("Y")){
//                gameController.undoMove(game, movePlayed);
//            }
            Player winner=gameController.checkWinner(game, movePlayed);
            if(winner!=null){
                System.out.println("WINNER IS : " + winner.getName());
                break;
            }
        }
        System.out.println("Final board status");
        gameController.displayBoard(game);
    }
}
