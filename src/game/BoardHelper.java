package game;

import java.awt.*;
import java.util.ArrayList;

public class BoardHelper {

    public static boolean isGameFinished(int[][] board){
       return !(hasAnyMoves(board,1) || hasAnyMoves(board,2));
    }


    public static int[][] getStartBoard(){
        int[][] b = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                b[i][j] = 0;
            }
        }
        b[3][3] = 2;
        b[3][4] = 1;
        b[4][3] = 1;
        b[4][4] = 2;
        return b;
    }

    public static Point getMove(int[][] before , int[][] after){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(before[i][j]==0 && after[i][j]!=0){
                    return new Point(i,j);
                }
            }
        }
        return null;
    }

    public static int getWinner(int[][] board){
        if(!isGameFinished(board))
            //game not finished
            return -1;
        else{
            //count stones
            int p1s = getPlayerStoneCount(board,1);
            int p2s = getPlayerStoneCount(board,2);

            if(p1s == p2s){
                //tie
                return 0;
            }else if(p1s > p2s){
                //p1 wins
                return 1;
            }else{
                //p2 wins
                return 2;
            }
        }
    }

    public static int getTotalStoneCount(int[][] board){
        int c = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] != 0) c++;
            }
        }
        return c;
    }

    public static int getPlayerStoneCount(int[][] board, int player){
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] == player) score++;
            }
        }
        return score;
    }


    public static boolean hasAnyMoves(int[][] board, int player){
        return getAllPossibleMoves(board,player).size() > 0;
    }

    public static ArrayList<Point> getAllPossibleMoves(int[][] board, int player){
        ArrayList<Point> result = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(canPlay(board,player,i,j)){
                    result.add(new Point(i,j));
                }
            }
        }
        return result;
    }

    public static void moveUp(int[][] board,int player, int oplayer ,int i,int j, ArrayList<Point> allReversePoints) {
        int mi , mj;
        ArrayList<Point> mupts = new ArrayList<>();
        mi = i - 1;
        mj = j;
        while(mi>0 && board[mi][mj] == oplayer){
            mupts.add(new Point(mi,mj));
            mi--;
        }
        if(mi>=0 && board[mi][mj] == player && mupts.size()>0){
            allReversePoints.addAll(mupts);
        }
    }

    public static void moveDown(int[][] board,int player, int oplayer ,int i,int j, ArrayList<Point> allReversePoints) {
        int mi , mj;
        ArrayList<Point> mdpts = new ArrayList<>();
        mi = i + 1;
        mj = j;
        while(mi<7 && board[mi][mj] == oplayer){
            mdpts.add(new Point(mi,mj));
            mi++;
        }
        if(mi<=7 && board[mi][mj] == player && mdpts.size()>0){
            allReversePoints.addAll(mdpts);
        }
    }

    public static void moveLeft(int[][] board,int player, int oplayer ,int i,int j, ArrayList<Point> allReversePoints) {
        int mi , mj;
        ArrayList<Point> mlpts = new ArrayList<>();
        mi = i;
        mj = j - 1;
        while(mj>0 && board[mi][mj] == oplayer){
            mlpts.add(new Point(mi,mj));
            mj--;
        }
        if(mj>=0 && board[mi][mj] == player && mlpts.size()>0){
            allReversePoints.addAll(mlpts);
        }
    }

    public static void moveRight(int[][] board,int player, int oplayer ,int i,int j, ArrayList<Point> allReversePoints) {
        int mi , mj;
        ArrayList<Point> mrpts = new ArrayList<>();
        mi = i;
        mj = j + 1;
        while(mj<7 && board[mi][mj] == oplayer){
            mrpts.add(new Point(mi,mj));
            mj++;
        }
        if(mj<=7 && board[mi][mj] == player && mrpts.size()>0){
            allReversePoints.addAll(mrpts);
        }
    }

    public static void moveUpLeft(int[][] board,int player, int oplayer ,int i,int j, ArrayList<Point> allReversePoints) {
        int mi , mj;
        ArrayList<Point> mulpts = new ArrayList<>();
        mi = i - 1;
        mj = j - 1;
        while(mi>0 && mj>0 && board[mi][mj] == oplayer){
            mulpts.add(new Point(mi,mj));
            mi--;
            mj--;
        }
        if(mi>=0 && mj>=0 && board[mi][mj] == player && mulpts.size()>0){
            allReversePoints.addAll(mulpts);
        }
    }

    public static void moveUpRight(int[][] board,int player, int oplayer ,int i,int j, ArrayList<Point> allReversePoints) {
        int mi , mj;
        ArrayList<Point> murpts = new ArrayList<>();
        mi = i - 1;
        mj = j + 1;
        while(mi>0 && mj<7 && board[mi][mj] == oplayer){
            murpts.add(new Point(mi,mj));
            mi--;
            mj++;
        }
        if(mi>=0 && mj<=7 && board[mi][mj] == player && murpts.size()>0){
            allReversePoints.addAll(murpts);
        }
    }

    public static void moveDownLeft(int[][] board,int player, int oplayer ,int i,int j, ArrayList<Point> allReversePoints) {
        int mi , mj;
        ArrayList<Point> mdlpts = new ArrayList<>();
        mi = i + 1;
        mj = j - 1;
        while(mi<7 && mj>0 && board[mi][mj] == oplayer){
            mdlpts.add(new Point(mi,mj));
            mi++;
            mj--;
        }
        if(mi<=7 && mj>=0 && board[mi][mj] == player && mdlpts.size()>0){
            allReversePoints.addAll(mdlpts);
        }
    }

    public static void moveDownRight(int[][] board,int player, int oplayer ,int i,int j, ArrayList<Point> allReversePoints) {
        int mi , mj;
        ArrayList<Point> mdrpts = new ArrayList<>();
        mi = i + 1;
        mj = j + 1;
        while(mi<7 && mj<7 && board[mi][mj] == oplayer){
            mdrpts.add(new Point(mi,mj));
            mi++;
            mj++;
        }
        if(mi<=7 && mj<=7 && board[mi][mj] == player && mdrpts.size()>0){
            allReversePoints.addAll(mdrpts);
        }
    }

    public static ArrayList<Point> getReversePoints(int[][] board,int player,int i,int j){

        ArrayList<Point> allReversePoints = new ArrayList<>();

        int mi , mj , c;
        int oplayer = ((player == 1) ? 2 : 1);

        //move up
        moveUp(board, player, oplayer, i, j, allReversePoints);

        //move down
        moveDown(board, player, oplayer, i, j, allReversePoints);

        //move left
        moveLeft(board, player, oplayer, i, j, allReversePoints);

        //move right
        moveRight(board, player, oplayer, i, j, allReversePoints);

        //move up left
        moveUpLeft(board, player, oplayer, i, j, allReversePoints);

        //move up right
        moveUpRight(board, player, oplayer, i, j, allReversePoints);

        //move down left
        moveDownLeft(board, player, oplayer, i, j, allReversePoints);

        //move down right
        moveDownRight(board, player, oplayer, i, j, allReversePoints);

        return allReversePoints;
    }

    private static boolean moveUp(int[][] board,int player,int oplayer,int i,int j) {
        int mi , mj , c;
        mi = i - 1;
        mj = j;
        c = 0;
        while(mi>0 && board[mi][mj] == oplayer){
            mi--;
            c++;
        }
        if(mi>=0 && board[mi][mj] == player && c>0) return true;
        else return false;
    }

    private static boolean moveDown(int[][] board,int player,int oplayer,int i,int j) {
        int mi , mj , c;
        mi = i + 1;
        mj = j;
        c = 0;
        while(mi<7 && board[mi][mj] == oplayer){
            mi++;
            c++;
        }
        if(mi<=7 && board[mi][mj] == player && c>0) return true;
        else return false;
    }

    private static boolean moveLeft(int[][] board,int player,int oplayer,int i,int j) {
        int mi , mj , c;
        mi = i;
        mj = j - 1;
        c = 0;
        while(mj>0 && board[mi][mj] == oplayer){
            mj--;
            c++;
        }
        if(mj>=0 && board[mi][mj] == player && c>0) return true;
        else return false;
    }

    private static boolean moveRight(int[][] board,int player,int oplayer,int i,int j) {
        int mi , mj , c;
        mi = i;
        mj = j + 1;
        c = 0;
        while(mj<7 && board[mi][mj] == oplayer){
            mj++;
            c++;
        }
        if(mj<=7 && board[mi][mj] == player && c>0) return true;
        else return false;
    }

    private static boolean moveUpLeft(int[][] board,int player,int oplayer,int i,int j) {
        int mi , mj , c;
        mi = i - 1;
        mj = j - 1;
        c = 0;
        while(mi>0 && mj>0 && board[mi][mj] == oplayer){
            mi--;
            mj--;
            c++;
        }
        if(mi>=0 && mj>=0 && board[mi][mj] == player && c>0) return true;
        else return false;
    }

    private static boolean moveUpRight(int[][] board,int player,int oplayer,int i,int j) {
        int mi , mj , c;
        mi = i - 1;
        mj = j + 1;
        c = 0;
        while(mi>0 && mj<7 && board[mi][mj] == oplayer){
            mi--;
            mj++;
            c++;
        }
        if(mi>=0 && mj<=7 && board[mi][mj] == player && c>0) return true;
        else return false;
    }

    private static boolean moveDownLeft(int[][] board,int player,int oplayer,int i,int j) {
        int mi , mj , c;
        mi = i + 1;
        mj = j - 1;
        c = 0;
        while(mi<7 && mj>0 && board[mi][mj] == oplayer){
            mi++;
            mj--;
            c++;
        }
        if(mi<=7 && mj>=0 && board[mi][mj] == player && c>0) return true;
        else return false;
    }

    private static boolean moveDownRight(int[][] board,int player,int oplayer,int i,int j) {
        int mi , mj , c;
        mi = i + 1;
        mj = j + 1;
        c = 0;
        while(mi<7 && mj<7 && board[mi][mj] == oplayer){
            mi++;
            mj++;
            c++;
        }
        if(mi<=7 && mj<=7 && board[mi][mj] == player && c>0) return true;
        else return false;
    }

    public static boolean canPlay(int[][] board,int player,int i,int j){

        if(board[i][j] != 0) return false;

        int mi , mj , c;
        int oplayer = ((player == 1) ? 2 : 1);

        //move up
        if(moveUp(board, player, oplayer, i, j)) return true;

        //move down
        if(moveDown(board, player, oplayer, i, j)) return true;

        //move left
        if(moveLeft(board, player, oplayer, i, j)) return true;

        //move right
        if(moveRight(board, player, oplayer, i, j)) return true;

        //move up left
        if(moveUpLeft(board, player, oplayer, i, j)) return true;

        //move up right
        if(moveUpRight(board, player, oplayer, i, j)) return true;

        //move down left
        if(moveDownLeft(board, player, oplayer, i, j)) return true;

        //move down right
        if(moveDownRight(board, player, oplayer, i, j)) return true;

        //when all hopes fade away
        return false;
    }

    public static int[][] getNewBoardAfterMove(int[][] board, Point move , int player){
        //get clone of old board
        int[][] newboard = new int[8][8];
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                newboard[k][l] = board[k][l];
            }
        }

        //place piece
        newboard[move.x][move.y] = player;
        //reverse pieces
        ArrayList<Point> rev = BoardHelper.getReversePoints(newboard,player,move.x,move.y);
        for(Point pt : rev){
            newboard[pt.x][pt.y] = player;
        }

        return newboard;
    }

    private static void addStableDisc(ArrayList<Point> stableDiscs, ArrayList<Point> pts) {
        for(Point sd : pts){
            boolean redundant = false;
            for(Point stableDisc : stableDiscs){
                if(sd.equals(stableDisc)){
                    redundant = true;
                    break;
                }
            }
            if(!redundant) stableDiscs.add(sd);
        }
    }

    private static void moveUp(int[][] board,int player ,int i, int j, ArrayList<Point> stableDiscs) {
        ArrayList<Point> mupts = new ArrayList<>();
        int mi, mj;
        mi = i - 1;
        mj = j;
        while(mi>0 && board[mi][mj] == player){
            mupts.add(new Point(mi,mj));
            mi--;
        }

        addStableDisc(stableDiscs, mupts);
    }

    private static void moveDown(int[][] board,int oplayer ,int i, int j, ArrayList<Point> stableDiscs) {
        ArrayList<Point> mdpts = new ArrayList<>();
        int mi, mj;
        mi = i + 1;
        mj = j;
        while(mi<7 && board[mi][mj] == oplayer){
            mdpts.add(new Point(mi,mj));
            mi++;
        }

        addStableDisc(stableDiscs, mdpts);
    }

    private static void moveLeft(int[][] board,int oplayer ,int i, int j, ArrayList<Point> stableDiscs) {
        ArrayList<Point> mlpts = new ArrayList<>();
        int mi, mj;
        mi = i;
        mj = j - 1;
        while(mj>0 && board[mi][mj] == oplayer){
            mlpts.add(new Point(mi,mj));
            mj--;
        }

        addStableDisc(stableDiscs, mlpts);
    }

    private static void moveRight(int[][] board,int oplayer ,int i, int j, ArrayList<Point> stableDiscs) {
        ArrayList<Point> mrpts = new ArrayList<>();
        int mi, mj;
        mi = i;
        mj = j + 1;
        while(mj<7 && board[mi][mj] == oplayer){
            mrpts.add(new Point(mi,mj));
            mj++;
        }

        addStableDisc(stableDiscs, mrpts);
    }

    private static void moveUpLeft(int[][] board,int oplayer ,int i, int j, ArrayList<Point> stableDiscs) {
        ArrayList<Point> mulpts = new ArrayList<>();
        int mi, mj;
        mi = i - 1;
        mj = j - 1;
        while(mi>0 && mj>0 && board[mi][mj] == oplayer){
            mulpts.add(new Point(mi,mj));
            mi--;
            mj--;
        }

        addStableDisc(stableDiscs, mulpts);
    }

    private static void moveUpRight(int[][] board,int oplayer ,int i, int j, ArrayList<Point> stableDiscs) {
        ArrayList<Point> murpts = new ArrayList<>();
        int mi, mj;
        mi = i - 1;
        mj = j + 1;
        while(mi>0 && mj<7 && board[mi][mj] == oplayer){
            murpts.add(new Point(mi,mj));
            mi--;
            mj++;
        }

        addStableDisc(stableDiscs, murpts);
    }

    private static void moveDownLeft(int[][] board,int oplayer ,int i, int j, ArrayList<Point> stableDiscs) {
        ArrayList<Point> mdlpts = new ArrayList<>();
        int mi, mj;
        mi = i + 1;
        mj = j - 1;
        while(mi<7 && mj>0 && board[mi][mj] == oplayer){
            mdlpts.add(new Point(mi,mj));
            mi++;
            mj--;
        }

        addStableDisc(stableDiscs, mdlpts);
    }

    private static void moveDownRight(int[][] board,int oplayer ,int i, int j, ArrayList<Point> stableDiscs) {
        ArrayList<Point> mdrpts = new ArrayList<>();
        int mi, mj;
        mi = i + 1;
        mj = j + 1;
        while(mi<7 && mj<7 && board[mi][mj] == oplayer){
            mdrpts.add(new Point(mi,mj));
            mi++;
            mj++;
        }

        addStableDisc(stableDiscs, mdrpts);
    }

    public static ArrayList<Point> getStableDisks(int[][] board,int player,int i,int j){

        ArrayList<Point> stableDiscs = new ArrayList<>();

        int mi , mj;
        int oplayer = ((player == 1) ? 2 : 1);

        //move up
        moveUp(board, player, i, j, stableDiscs);

        //move down
        moveDown(board, oplayer, i, j, stableDiscs);

        //move left
        moveLeft(board, oplayer, i, j, stableDiscs);

        //move right
        moveRight(board, oplayer, i, j, stableDiscs);

        //move up left
        moveUpLeft(board, oplayer, i, j, stableDiscs);

        //move up right
        moveUpRight(board, oplayer, i, j, stableDiscs);

        //move down left
        moveDownLeft(board, oplayer, i, j, stableDiscs);

        //move down right
        moveDownRight(board, oplayer, i, j, stableDiscs);

        return stableDiscs;
    }


    private static void upPossibleFrontier(int[][] board, int i, int j, ArrayList<Point> possiblefrontiers) {
        //up
        if (i > 0 && board[i - 1][j] == 0) possiblefrontiers.add(new Point(i - 1, j));
    }
    private static void downPossibleFrontier(int[][] board, int i, int j, ArrayList<Point> possiblefrontiers) {
        //down
        if (i < 7 && board[i + 1][j] == 0) possiblefrontiers.add(new Point(i + 1, j));
    }
    private static void rightPossibleFrontier(int[][] board, int i, int j, ArrayList<Point> possiblefrontiers) {
        //right
        if (j < 7 && board[i][j + 1] == 0) possiblefrontiers.add(new Point(i, j + 1));
    }
    private static void leftPossibleFrontier(int[][] board, int i, int j, ArrayList<Point> possiblefrontiers) {
        //left
        if (j > 0 && board[i][j - 1] == 0) possiblefrontiers.add(new Point(i, j - 1));
    }
    private static void upLeftPossibleFrontier(int[][] board, int i, int j, ArrayList<Point> possiblefrontiers) {
        //up-left
        if (i > 0 && j > 0 && board[i - 1][j - 1] == 0) possiblefrontiers.add(new Point(i - 1, j - 1));
    }
    private static void upRightPossibleFrontier(int[][] board, int i, int j, ArrayList<Point> possiblefrontiers) {
        //up-right
        if (i > 0 && j < 7 && board[i - 1][j + 1] == 0) possiblefrontiers.add(new Point(i - 1, j + 1));
    }
    private static void downLeftPossibleFrontier(int[][] board, int i, int j, ArrayList<Point> possiblefrontiers) {
        //down-left
        if (i < 7 && j > 0 && board[i + 1][j - 1] == 0) possiblefrontiers.add(new Point(i + 1, j - 1));
    }
    private static void downRightPossibleFrontier(int[][] board, int i, int j, ArrayList<Point> possiblefrontiers) {
        //down-right
        if(i<7 && j<7 && board[i+1][j+1]==0) possiblefrontiers.add(new Point(i+1,j+1));
    }

    public static ArrayList<Point> getFrontierSquares(int[][] board,int player){

        ArrayList<Point> frontiers = new ArrayList<>();

        int oplayer = ((player == 1) ? 2 : 1);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j]==oplayer){

                    ArrayList<Point> possiblefrontiers = new ArrayList<>();

                    //check 8 directions
                    //up
                    upPossibleFrontier(board, i, j, possiblefrontiers);
                    //down
                    downPossibleFrontier(board, i, j, possiblefrontiers);
                    //left
                    leftPossibleFrontier(board, i, j, possiblefrontiers);
                    //right
                    rightPossibleFrontier(board, i, j, possiblefrontiers);
                    //up-left
                    upLeftPossibleFrontier(board, i, j, possiblefrontiers);
                    //up-right
                    upRightPossibleFrontier(board, i, j, possiblefrontiers);
                    //down-left
                    downLeftPossibleFrontier(board, i, j, possiblefrontiers);
                    //down-right
                    downRightPossibleFrontier(board, i, j, possiblefrontiers);
                    addStableDisc(frontiers, possiblefrontiers);
                }
            }
        }

        return frontiers;
    }

}
