package org.example.DAO;


import org.example.model.ParameterModel;

public interface ParameterDAO {

    void handleArgs(ParameterModel<Integer> parameterModel);

    void verifyIfAllMandatoryArgumentsWerePassed();
}
