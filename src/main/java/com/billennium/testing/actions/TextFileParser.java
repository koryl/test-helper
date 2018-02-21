package com.billennium.testing.actions;

import com.billennium.testing.logger.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.billennium.testing.Constants.TEST_DATA_DIRECTORY;

public class TextFileParser {

    public static synchronized List<String> readParametersFromFile(String fileName) {
        List<String> resultList = null;
        try {
            resultList = Files.readAllLines(Paths.get(TEST_DATA_DIRECTORY + fileName));
            Log.debug("File with expected values was loaded: " + fileName);
        } catch (IOException e) {
            Log.error("Cannot read the parameters text file.");
        }
        return resultList;
    }
}
