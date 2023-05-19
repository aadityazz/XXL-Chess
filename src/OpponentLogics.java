import java.util.ArrayList;

public class OpponentLogics {

    static boolean whiteKingRed = false;

    static int[] whiteKingPosition;
    static boolean blackKingRed = false;
    static int[] blackKingPosition;
    public static ArrayList<int[]> redMarker(ArrayList<int[]> paths, int[][] board, int identity){
        ArrayList<int[]> list = new ArrayList<>();

        for(int[] path: paths){
            if((board[path[1]][path[0]] > 0 && identity < 0)){
                list.add(path);
                if(board[path[1]][path[0]] == 8){
                    whiteKingRed = true;
                    whiteKingPosition = new int[]{path[1],path[0]};
                }else{
                    whiteKingRed = false;
                }
            } else if ((board[path[1]][path[0]] < 0 && identity > 0)) {
                list.add(path);
                if(board[path[1]][path[0]] == -8){
                    blackKingRed = true;
                    blackKingPosition = new int[]{path[1],path[0]};
                }else{
                    blackKingRed = false;
                }
            }


        }
        return list;
    }

    public static boolean isBlackKingAlive(int[][] board){
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                if(board[i][j] == -8) return true;
            }
        }
        return false;
    }

    public static boolean isWhiteKingAlive(int[][] board){
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                if(board[i][j] == 8) return true;
            }
        }
        return false;
    }



}
