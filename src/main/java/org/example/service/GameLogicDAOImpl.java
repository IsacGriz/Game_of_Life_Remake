package org.example.service;

import org.example.model.Board;
import org.example.model.Parameter;

import java.util.Random;

public class GameLogicDAOImpl implements GameLogicDAO {

    Random rand = new Random();
    Board board;

    public GameLogicDAOImpl() {
        if (Parameter.getPopulation().equals("RND")) generateRandomStartPopulation();
        else generatePopulation();
    }

    @Override
    public void generateRandomStartPopulation() {
        char[][] boardModel = new char[Parameter.getHeight()][Parameter.getWidth()];
        for (int i = 0; i < Parameter.getHeight(); i++) {
            for (int j = 0; j < Parameter.getWidth(); j++) {
                boardModel[i][j] = rand.nextBoolean() ? 'x' : '.';
            }
        }

        board = new Board(boardModel);
    }

    @Override
    public void generatePopulation() {
        String[] populationSplit = Parameter.getPopulation().split("#");
        char[][] boardModel = new char[Parameter.getHeight()][Parameter.getWidth()];

        for (int i = 0; i < populationSplit.length; i++) {
            for (int j = 0; j < populationSplit[i].length(); j++) {
                boardModel[i][j] += 'a';
                boardModel[i][j] = populationSplit[i].charAt(j) == 1 ? 'x' : '.';
            }
        }

        board = new Board(boardModel);
    }

    @Override
    public void calculateNextGeneration() {
        char[][] newBoard = new char[Parameter.getHeight()][Parameter.getWidth()];

        for (int currentColumn = 0; currentColumn < Parameter.getHeight(); currentColumn++) {
            for (int currentRow = 0; currentRow < Parameter.getWidth(); currentRow++) {
                int neighborCount = getNeighborCount(currentColumn, currentRow);

                if (neighborCount < 2 || neighborCount > 3) newBoard[currentColumn][currentRow] = 0;
                else if (neighborCount == 3) newBoard[currentColumn][currentRow] = 1;
                else newBoard[currentColumn][currentRow] = board.board()[currentColumn][currentRow];
            }
        }

        for (int i = 0; i < newBoard.length; i++) {
            System.arraycopy(newBoard[i], 0, board.board()[i], 0, newBoard[i].length);
        }
    }

    private int getNeighborCount(int currentColumn, int currentRow) {
        int neighborCount = 0;

        for (int i = currentColumn - 1; i <= currentColumn + 1; i++) {
            for (int j = currentRow - 1; j <= currentRow + 1; j++) {
                int col = i;
                int row = j;

                if (i < 0) {
                    col = board.board().length - 1;
                } else if (i >= board.board().length) {
                    col = 0;
                }

                if (j < 0) {
                    row = board.board()[col].length - 1;
                } else if (j >= board.board()[col].length) {
                    row = 0;
                }

                if (col != currentRow && row != currentColumn && board.board()[col][row] == 1) neighborCount++;
            }
        }
        return neighborCount;
    }
}
