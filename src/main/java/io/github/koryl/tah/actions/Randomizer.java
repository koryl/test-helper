package io.github.koryl.tah.actions;

import io.github.koryl.tah.logger.Log;
import org.apache.commons.lang3.RandomStringUtils;

import static io.github.koryl.tah.actions.Randomizer.Type.*;

/**
 * It is used for populating fields or text boxes with random String values.
 * It logs actions and displaying what String was generated (on Debug level).
 */
public class Randomizer {

    /**
     * Generates random alphanumeric String.
     *
     * @param length length of String to generate
     *
     * @return random alphanumeric String
     */
    public static synchronized String generate(int length) {
        return generate(length, ALPHANUMERIC);
    }

    /**
     * Generates random String. It must be provided what the type of String should be generated:
     * NUMBERS, LETTERS or ALPHANUMERIC.
     *
     * @param length length of String to generate
     * @param typeOfString indicates what the type of String to generate (NUMBERS, LETTERS, ALPHANUMERIC)
     * @return random String
     */
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
