package player.ai;

import game.BoardHelper;

public class StaticEvaluator implements Evaluator {

    public int eval(int[][] board , int player){
        int mob = evalMobility(board,player);
        int sc = evalDiscDiff(board,player);
        return 2*mob + sc + 1000*evalCorner(board,player);
    }

    public static int evalDiscDiff(int[][] board , int player){
        int oplayer = (player==1) ? 2 : 1;

        int mySC = BoardHelper.getPlayerStoneCount(board,player);
        int opSC = BoardHelper.getPlayerStoneCount(board,oplayer);

        return 100 * (mySC - opSC) / (mySC + opSC);
    }

    public static int evalMobility(int[][] board , int player){
        int oplayer = (player==1) ? 2 : 1;

        int myMoveCount = BoardHelper.getAllPossibleMoves(board,player).size();
        int opMoveCount = BoardHelper.getAllPossibleMoves(board,oplayer).size();

        return 100 * (myMoveCount - opMoveCount) / (myMoveCount + opMoveCount + 1);
    }

    public static int evalCorner(int[][] board , int player){
        int oplayer = (player==1) ? 2 : 1;

        int myCorners = 0;
        int opCorners = 0;

        if(board[0][0]==player) myCorners++;
        if(board[7][0]==player) myCorners++;
        if(board[0][7]==player) myCorners++;
        if(board[7][7]==player) myCorners++;

        if(board[0][0]==oplayer) opCorners++;
        if(board[7][0]==oplayer) opCorners++;
        if(board[0][7]==oplayer) opCorners++;
        if(board[7][7]==oplayer) opCorners++;

        return 100 * (myCorners - opCorners) / (myCorners + opCorners + 1);
    }

    public static int evalParity(int[][] board){
        int remDiscs = 64 - BoardHelper.getTotalStoneCount(board);
        return remDiscs % 2 == 0 ? -1 : 1;
    }


}
