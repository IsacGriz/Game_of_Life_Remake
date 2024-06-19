package org.example.model;

import java.util.Set;

public class ReceivedParameterModel<T> {
    private static final Set<Character> POSSIBLE_PREFIXES = Set.of('g', 'h', 'p', 's', 'w');
    private static final Set<Character> POSSIBLE_VALUES_FOR_P = Set.of('0', '1', '#');
    private final char PREFIX;
    private final T VALUE;

    public ReceivedParameterModel(char prefix, T value) {
        verifyValue(prefix, value);

        this.PREFIX = prefix;
        this.VALUE = value;
    }

    private void verifyValue(char prefix, T value){

        if (!POSSIBLE_PREFIXES.contains(prefix)) {
            throw new IllegalArgumentException("Not valid PREFIX: " + prefix);
        }

        if (value == null) {
            throw new IllegalArgumentException("Not valid value");
        }

        if ((prefix == 'g' || prefix == 'h' || prefix == 's' || prefix == 'w') && !(value instanceof Integer)) {
            throw new IllegalArgumentException("Not valid value: " + value + " it must be an integer");
        }

        if (prefix == 'g' && (Integer) value < 0) {
            throw new IllegalArgumentException("Not valid value: " + value);
        }

        if (prefix == 'h' && (Integer) value != 10 && (Integer) value != 20 && (Integer) value != 40 && (Integer) value != 80) {
            throw new IllegalArgumentException("Not valid value: " + value);
        }

        if (prefix == 'w' && (Integer) value != 10 && (Integer) value != 20 && (Integer) value != 40) {
            throw new IllegalArgumentException("Not valid value: " + value);
        }

        if (prefix == 's' && ((Integer) value < 250 || (Integer) value > 1000)) {
            throw new IllegalArgumentException("Not valid value: " + value);
        }

        if (prefix == 'p') {
            for (int i = 0; i < value.toString().length(); i++) {
                if (!POSSIBLE_VALUES_FOR_P.contains(value.toString().charAt(i))) {
                    throw new IllegalArgumentException("Not valid value: " + value);
                }
            }

            String[] characters = value.toString().split("#");

            if (characters.length > Parameter.getHeight() || characters[0].length() < Parameter.getWidth()) {
                throw new IllegalArgumentException("Not valid value: " + value);
            }
        }
    }

    public char getPrefix() {
        return PREFIX;
    }

    public T getValue() {
        return VALUE;
    }
}
