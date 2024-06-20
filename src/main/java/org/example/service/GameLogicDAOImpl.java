package org.example.service;

import org.example.model.Parameter;

import java.util.Random;

public class GameLogicDAOImpl implements GameLogicDAO {

    Random rand = new Random();
    int[][] board = new int[Parameter.getHeight()][Parameter.getWidth()];

    {
        if (Parameter.getPopulation().equals("RND")) generateRandomStartPopulation();
        else generatePopulation();
    }

    @Override
    public void generateRandomStartPopulation() {
        for (int i = 0; i < Parameter.getHeight(); i++) {
            for (int j = 0; j < Parameter.getWidth(); j++) {
                board[i][j] = rand.nextInt(0, 1);
            }
        }
    }

    @Override
    public void generatePopulation() {
        String[] populationSplit = Parameter.getPopulation().split("#");

        for (int i = 0; i < populationSplit.length; i++) {
            for (int j = 0; j < populationSplit[i].length(); j++) {
                board[i][j] = Integer.parseInt(populationSplit[i].charAt(j) + "");
            }
        }
    }

    @Override
    public void calculateNextGeneration() {
        int[][] newBoard = new int[Parameter.getHeight()][Parameter.getWidth()];

        for (int currentColumn = 0; currentColumn < board.length; currentColumn++) {
            for (int currentRow = 0; currentRow < board[currentColumn].length; currentRow++) {
                int neighborCount = getNeighborCount(currentColumn, currentRow);

                if (neighborCount < 2 || neighborCount > 3) newBoard[currentColumn][currentRow] = 0;
                else if (neighborCount == 3) newBoard[currentColumn][currentRow] = 1;
            }
        }

        for (int i = 0; i < newBoard.length; i++) {
            System.arraycopy(newBoard[i], 0, board[i], 0, newBoard[i].length);
        }
    }

    private int getNeighborCount(int currentColumn, int currentRow) {
        int neighborCount = 0;

        for (int i = currentColumn - 1; i <= currentColumn + 1; i++) {
            for (int j = currentRow - 1; j <= currentRow + 1; j++) {
                int row = i;
                int col = j;

                if (i < 0) {
                    col = board.length - 1;
                } else if (i >= board.length) {
                    row = 0;
                }

                if (j < 0) {
                    row = board[i].length - 1;
                } else if (j >= board[i].length) {
                    row = 0;
                }

                if (col != currentRow && row != currentColumn && board[col][row] == 1) neighborCount++;
            }
        }
        return neighborCount;
    }
}
