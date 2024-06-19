package org.example.DAO;

import org.example.model.Parameter;
import org.example.model.ReceivedParameterModel;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public class ParameterDAOImpl implements ParameterDAO {
    List<String> parameters;

    public ParameterDAOImpl(String[] parameters) {
        this.parameters = Arrays.asList(parameters);
        Arrays.sort(parameters);
    }


    @Override
    public void handleArgs(ReceivedParameterModel<Integer> parameterModel) {
        if (parameterModel.getPrefix() == 'g') {
            Parameter.setGenerations(parameterModel.getValue());
        } else if (parameterModel.getPrefix() == 'h') {
            Parameter.setHeight(parameterModel.getValue());
        } else if (parameterModel.getPrefix() == 's') {
            Parameter.setSpeed(parameterModel.getValue());
        } else if (parameterModel.getPrefix() == 'w') {
            Parameter.setWidth(parameterModel.getValue());
        }
    }

    @Override
    public void verifyIfAllMandatoryArgumentsWerePassed() {
        parameters.forEach(parameter -> {
            String[] parameterSplit = parameter.split("=");

            if (parameterSplit.length != 2 || parameterSplit[0].length() != 1) {
                throw new InvalidParameterException(parameter);
            }

            if (parameterSplit[0].charAt(0) != 'p') {
                ReceivedParameterModel<Integer> parameterModel = new ReceivedParameterModel<>(parameterSplit[0].charAt(0), Integer.parseInt(parameterSplit[1]));
                handleArgs(parameterModel);
            } else {
                ReceivedParameterModel<String> parameterModel = new ReceivedParameterModel<>(parameterSplit[0].charAt(0), parameterSplit[1]);
                Parameter.setPopulation(parameterModel.getValue());
            }
        });
    }
}
