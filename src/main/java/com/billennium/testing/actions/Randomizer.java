package com.billennium.testing.actions;

import com.billennium.testing.logger.Log;
import org.apache.commons.lang3.RandomStringUtils;

import static com.billennium.testing.actions.Randomizer.Type.*;

public class Randomizer {

    public synchronized String generate(int length) {
        return generate(length, ALPHANUMERIC);
    }

    public static synchronized String generate(int length, Type typeOfString) {

        switch (typeOfString) {
            case NUMBERS:
                String numeric = RandomStringUtils.randomNumeric(length);
                Log.debug("Random numeric string was generated: " + numeric + ".");
                return numeric;
            case LETTERS:
                String alphabetic = RandomStringUtils.randomAlphabetic(length);
                Log.debug("Random numeric string was generated: " + alphabetic + ".");
                return alphabetic;
            case ALPHANUMERIC:
                String alphanumeric = RandomStringUtils.randomAlphanumeric(length);
                Log.debug("Random numeric string was generated: " + alphanumeric + ".");
                return alphanumeric;
            default:
                return "";
        }
    }

    public enum Type {
        NUMBERS,
        LETTERS,
        ALPHANUMERIC
    }
}
