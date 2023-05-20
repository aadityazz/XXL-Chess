import processing.core.*;

import java.util.ArrayList;
import java.util.Timer;

import Pieces.*;
import java.util.Timer;
import java.util.TimerTask;


public class ChessBoard extends PApplet {

    private static final int BOARD_SIZE = 14;
    private static final int TILE_SIZE = 48;
    private static final int WINDOW_WIDTH = 792;
    private static final int WINDOW_HEIGHT = 672;
    private static final int INFO_PANEL_WIDTH = 120;

    private static int selectedRow = -1;
    private static int selectedCol = -1;
    private static int offsetX = 0;
    private static int offsetY = 0;

    private static int pieceX = -1;
    private static int pieceY = -1;

    private static ArrayList<int[]> paths = new ArrayList<>();
    private static ArrayList<int[]> lists = new ArrayList<>();

    boolean isGameOver = false;

    private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    public void settings() {
        size(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    PImage whiteBishopImg, whiteKnightImg, whiteRookImg, whitePawnImg, whiteKingImg, whiteCamelImg, whiteGuardImg, whiteAmazonImg, whiteArchbishopImg,
           blackKnightImg, blackBishopImg, blackRookImg, blackPawnImg, blackKingImg, blackCamelImg, blackGuardImg, blackAmazonImg, blackArchbishopImg;

    Timer timer;
    int timeRemaining;
    int playerChance;
    public void setup() {

        // Initialize the board with starting positions of chess pieces
        // For example, set the value of board[0][0] to -2 for a black rook
        // and board[7][0] to -3 for a black queen
        board = new int[14][14];

        /*
        * rook 1
        * knight 2
        * Bishop 3
        * Archbishop 4
        * Camel  5
        * Guard 6
        * Amazon 7
        * King 8
        * Pawn 9
        * */


        board = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 6, 5, 4, 3, 2, 1},
                {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 0},
                {-9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9},
                {-1, -2, -3, -4, -5, -6, -7, -8, -6, -5, -4, -3, -2, -1}
        };

        //Load piece Image
        blackBishopImg = loadImage("images/bishop_black.png");
        blackPawnImg = loadImage("images/pawn_black.png");
        blackKingImg = loadImage("images/king_black.png");
        blackKnightImg = loadImage("images/knight_black.png");
        blackRookImg = loadImage("images/rook_black.png");
        blackCamelImg = loadImage("images/camel_black.png");
        blackAmazonImg = loadImage("images/amazon_black.png");
        blackGuardImg = loadImage("images/guard_black.png");
        blackArchbishopImg = loadImage("images/archbishop_black.png");

        whiteBishopImg = loadImage("images/bishop_white.png");
        whiteKingImg = loadImage("images/king_white.png");
        whiteRookImg = loadImage("images/rook_white.png");
        whitePawnImg = loadImage("images/pawn_white.png");
        whiteKnightImg = loadImage("images/knight_white.png");
        whiteCamelImg = loadImage("images/camel_white.png");
        whiteAmazonImg = loadImage("images/amazon_white.png");
        whiteGuardImg = loadImage("images/guard_white.png");
        whiteArchbishopImg = loadImage("images/archbishop_white.png");

        timer = new Timer();
        timeRemaining = 180; // Initial time in seconds
        playerChance = 1; // Starting player chance
        startTimer();
    }


    public void draw() {

        // Draw the chess board
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {

                if ((i + j) % 2 == 0) {
                    fill(250);
                } else {
                    fill(100);
                }

                if (selectedCol == i && selectedRow == j){
                    fill(0,200,0, 100);
                }


                rect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                int x = i * TILE_SIZE;
                int y = j * TILE_SIZE ;
                int piece = board[i][j];
                if(!isGameOver){
                    if (piece != 0) {
                        // Draw the chess piece image based on the piece value
                        // ...
                        if(piece == 1){
                            image(whiteRookImg, x, y, 48, 48);
                        } else if (piece == 2) {
                            image(whiteKnightImg, x, y, 48, 48);
                        } else if (piece == 3) {
                            image(whiteBishopImg, x, y, 48, 48);
                        } else if (piece == 4) {
                            image(whiteArchbishopImg, x, y, 48, 48);
                        } else if (piece == 5) {
                            image(whiteCamelImg, x, y, 48, 48);
                        } else if (piece == 6) {
                            image(whiteGuardImg, x, y, 48, 48);
                        } else if (piece == 7) {
                            image(whiteAmazonImg, x, y, 48, 48);
                        } else if (piece == 8) {
                            image(whiteKingImg, x, y, 48, 48);
                        } else if (piece == 9) {
                            image(whitePawnImg, x, y, 48, 48);
                        }

                        else if (piece == -1) {
                            image(blackRookImg, x, y,48, 48);
                        } else if (piece == -2) {
                            image(blackKnightImg, x, y,48, 48);
                        } else if (piece == -3) {
                            image(blackBishopImg, x, y,48, 48);
                        } else if (piece == -4) {
                            image(blackArchbishopImg, x, y, 48, 48);
                        } else if (piece == -5) {
                            image(blackCamelImg, x, y, 48, 48);
                        } else if (piece == -6) {
                            image(blackGuardImg, x, y, 48, 48);
                        } else if (piece == -7) {
                            image(blackAmazonImg, x, y, 48, 48);
                        } else if (piece == -8) {
                            image(blackKingImg, x, y,48, 48);
                        } else if (piece == -9) {
                            image(blackPawnImg, x, y,48, 48);
                        }
                    }
                }else{
                    ;
                }
            }
        }


        // Draw the information panel on the right
        fill(200);

        displayClock();
        displayPlayerChance();
        if(!OpponentLogics.isWhiteKingAlive(board)){
            isGameOver = true;
            text("Player " + playerChance + " wins by checkmate", width/2, height-20);
        } else if (!OpponentLogics.isWhiteKingAlive(board)) {
            isGameOver = true;
            text("Player " + playerChance + " wins by checkmate", width/2, height-20);
        }
        checkWinner();

        rect(WINDOW_WIDTH - INFO_PANEL_WIDTH, 0, INFO_PANEL_WIDTH, WINDOW_HEIGHT);

        // Draw the chess pieces on the board
        // For example, use an image or a specific color for each type of chess piece
        // and draw it at the corresponding position on the board
        drawValidMoves();
    }

    private void displayClock() {
        textSize(32);
        textAlign(RIGHT, RIGHT);
        text("Time: " + timeRemaining, width/2, height/2);
    }

    private void displayPlayerChance() {
        textSize(16);
        textAlign(CENTER, TOP);
        text("Player " + playerChance + "'s chance", width/2, 20);
    }

    private void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                timeRemaining--;
                if (timeRemaining <= 0) {
                    stopTimer();
                    playerChance = 3 - playerChance; // Switch player chance
                }
            }
        }, 1000, 1000); // Schedule timer to decrement every second
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private void checkWinner() {
        if (timeRemaining <= 0) {
            textSize(24);
            textAlign(CENTER, BOTTOM);
            text("Player " + playerChance + " wins by time over", width/2, height-20);
        }
    }

    private int selectedRowDum = -1;
    private int selectedColDum = -1;
    public void mousePressed() {
        int row = (mouseY)/48 ;
        int col = (mouseX)/48;

        pieceX = row;
        pieceY = col;
        // Check if the click is within the chessboard bounds
        if (row >= 0 && row < 14 && col >= 0 && col < 14) {
            selectedRow = row;
            selectedCol = col;
            offsetX = mouseX - selectedCol * TILE_SIZE;
            offsetY = mouseY - selectedRow * TILE_SIZE;

            selectedRowDum = selectedRow;
            selectedColDum = selectedCol;
        }

        int piece = board[selectedCol][selectedRow];

        if(piece == 1) paths = Pieces.Rook.whereToMoveRook(selectedRow, selectedCol, 1, board);
        else if(piece == 2) paths = Pieces.Knight.whereToMoveKnight(selectedRow, selectedCol, 1, board);
        else if(piece == 3) paths = Pieces.Bishop.whereToMoveBishop(selectedRow, selectedCol, 1, board);
        else if(piece == 4) paths = Pieces.Archbishop.whereToMoveArchbishop(selectedRow, selectedCol, 1, board);
        else if(piece == 5) paths = Pieces.Camel.whereToMoveCamel(selectedRow, selectedCol, 1, board);
        else if(piece == 6) paths = Pieces.Guard.whereToMoveGuard(selectedRow, selectedCol, 1, board);
        else if(piece == 7) paths = Pieces.Amazon.whereToMoveAmazon(selectedRow, selectedCol, 1, board);
        else if(piece == 8) paths = Pieces.King.whereToMoveKing(selectedRow, selectedCol, 1, board);
        else if(piece == 9) paths = Pieces.Pawn.whereToMovePawn(selectedRow, selectedCol, 1, board);

        else if(piece == -1) paths = Pieces.Rook.whereToMoveRook(selectedRow, selectedCol, 1, board);
        else if(piece == -2) paths = Pieces.Knight.whereToMoveKnight(selectedRow, selectedCol, -1, board);
        else if(piece == -3) paths = Pieces.Bishop.whereToMoveBishop(selectedRow, selectedCol, -1, board);
        else if(piece == -4) paths = Pieces.Archbishop.whereToMoveArchbishop(selectedRow, selectedCol, -1, board);
        else if(piece == -5) paths = Pieces.Camel.whereToMoveCamel(selectedRow, selectedCol, -1, board);
        else if(piece == -6) paths = Pieces.Guard.whereToMoveGuard(selectedRow, selectedCol, -1, board);
        else if(piece == -7) paths = Pieces.Amazon.whereToMoveAmazon(selectedRow, selectedCol, -1, board);
        else if(piece == -8) paths = Pieces.King.whereToMoveKing(selectedRow, selectedCol, -1, board);
        else if(piece == -9) paths = Pieces.Pawn.whereToMovePawn(selectedRow, selectedCol, -1, board);


        lists = OpponentLogics.redMarker(paths, board, piece);

    }

    public void mouseDragged() {
        // Check if a piece is selected
        if (selectedRow != -1 && selectedCol != -1) {
            // Update the position of the selected piece to follow the mouse
            selectedCol = (mouseX - offsetX) / TILE_SIZE;
            selectedRow = (mouseY - offsetY) / TILE_SIZE;
        }

    }

    public void mouseReleased() {
        // Check if a piece is selected
        if (selectedRow != -1 && selectedCol != -1) {
            // Move the selected piece to the final position on the chessboard

            if(isInPath(selectedRow, selectedCol)){
                board[selectedCol][selectedRow] = board[selectedColDum][selectedRowDum];
                if(selectedRowDum != selectedRow || selectedColDum !=  selectedCol) board[selectedColDum][selectedRowDum] = 0;
            }else{
                board[selectedCol][selectedRow] = board[selectedCol][selectedRow];
            }

            // Deselect the piece
            selectedRow = -1;
            selectedCol = -1;

            selectedColDum = -1;
            selectedRowDum = -1;

            pieceX = -1;
            pieceY = -1;

            paths.clear();
            lists.clear();

            if(playerChance == 1) playerChance =2;
            else playerChance = 1;

            draw();
        }
    }


    boolean isInPath(int x, int y){
        for (int[] path : paths) {
            if (path[0] == x && path[1] == y) return true;
        }
        return false;
    }

    void drawValidMoves() {

        if (pieceX != -1 && pieceY != -1) {

            fill(0, 100, 255, 150); // Semi-transparent blue for valid move indicators

            for (int[] move : paths) {
                PVector vector = converter(move);
                rect(vector.y * TILE_SIZE, vector.x * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }

            fill(200, 0, 0, 150);

            for(int[] move: lists){
                PVector vector = converter(move);
                rect(vector.y* TILE_SIZE, vector.x * TILE_SIZE, TILE_SIZE, TILE_SIZE );
            }

            if(OpponentLogics.whiteKingRed){
                fill(250, 0,0,150);
                rect(OpponentLogics.whiteKingPosition[0]*TILE_SIZE, OpponentLogics.whiteKingPosition[1]*TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
            else if(OpponentLogics.blackKingRed){
                fill(250, 0,0,150);
                rect(OpponentLogics.blackKingPosition[0]*TILE_SIZE, OpponentLogics.blackKingPosition[1]*TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

    }

    public static PVector converter(int[] array){
        PVector vector;

        if (array.length >= 2) {
            int x = array[0];
            int y = array[1];
            vector = new PVector(x, y);
        } else {
            // Handle the case when the array does not have enough elements
            vector = null; // Or you can set a default value, or throw an exception, depending on your needs
        }

        return vector;
    }

    public static void main(String[] args) {
        PApplet.main("ChessBoard");
    }
}