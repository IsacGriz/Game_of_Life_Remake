package org.example;

import org.example.DAO.ParameterDAO;
import org.example.DAO.ParameterDAOImpl;
import org.example.model.Parameter;

public class Main {
    public static void main(String[] args) {
        ParameterDAO parameterDAO = new ParameterDAOImpl(args);
        parameterDAO.verifyIfAllMandatoryArgumentsWerePassed();

        Parameter parameter = new Parameter();

        System.out.println(parameter);
    }
}