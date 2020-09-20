package com.task.interview.colliers.digital.parser

import com.opencsv.CSVReaderBuilder
import java.io.FileReader

object CsvDataParser {

    @JvmStatic
    fun readDataFromFile(file: String): List<Array<String>> {
        return try {
            CSVReaderBuilder(FileReader(file))
                    .withSkipLines(1)
                    .build()
                    .readAll()
        } catch (e: Exception) {
            emptyList()
        }
    }

}