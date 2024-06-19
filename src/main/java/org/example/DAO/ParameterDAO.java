package org.example.DAO;


import org.example.model.ReceivedParameterModel;

public interface ParameterDAO {

    void handleArgs(ReceivedParameterModel<Integer> parameterModel);

    void verifyIfAllMandatoryArgumentsWerePassed();
}
