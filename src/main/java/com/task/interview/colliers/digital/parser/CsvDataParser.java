package com.task.interview.colliers.digital.parser;

import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.util.Collections;
import java.util.List;

@Slf4j
public class CsvDataParser {

    public static List<String[]> readAllDataAtOnce(String file) {
        try {
            log.info("Reading data from file: " + file);
            return new CSVReaderBuilder(new FileReader(file))
                    .withSkipLines(1)
                    .build()
                    .readAll();
        } catch (Exception e) {
            log.error("Data reading from file: " + file + " failed.");
            return Collections.emptyList();
        }
    }

}
