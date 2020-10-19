package com.training.censusanalyser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;


public class StateCensusAnalyser {
    public Path csvFilePath;

    // Constructor
    public StateCensusAnalyser(Path csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    // Returns The number of record in CSV file
    public int readStateCensusCSVData() {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(csvFilePath);
            CsvToBeanBuilder<CSVStateCensus> builder = new CsvToBeanBuilder<CSVStateCensus>(reader);
            builder.withType(CSVStateCensus.class);
            builder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<CSVStateCensus> csvToBean = builder.build();

            int noOfRecords = 0;
            Iterator<CSVStateCensus> StateCensusCSVIterator = csvToBean.iterator();
            while (StateCensusCSVIterator.hasNext()) {
                noOfRecords++;
                CSVStateCensus censusData = StateCensusCSVIterator.next();
                System.out.println(censusData.toString());
            }
            return noOfRecords;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
