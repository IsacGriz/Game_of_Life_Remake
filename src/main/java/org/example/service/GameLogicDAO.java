package org.example.service;

import java.util.Random;

public interface GameLogicDAO {

    void generateRandomStartPopulation();

    void generatePopulation();

    void calculateNextGeneration();
}
