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


public class StateCensusAnalyzer {
    public Path csvFilePath;

    // Constructor
    public StateCensusAnalyzer(Path csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    // Returns The number of record in CSV file
    public int readStateCensusCSVData() throws StateCensusAnalyzerException {
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
        } catch (IOException e1) {
            throw new StateCensusAnalyzerException("Invalid path entered",StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
        } catch (IllegalStateException e2) {
            throw new StateCensusAnalyzerException("Invalid state present", StateCensusAnalyzerException.ExceptionType.INCORRECT_STATE);
        }
    }

    public int readStateCodeCSVData() throws StateCensusAnalyzerException{
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(csvFilePath);
            CsvToBeanBuilder<CSVStateCode> builder = new CsvToBeanBuilder<CSVStateCode>(reader);
            builder.withType(CSVStateCode.class);
            builder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<CSVStateCode> csvToBean = builder.build();

            int noOfRecords = 0;
            Iterator<CSVStateCode> StateCensusCSVIterator = csvToBean.iterator();
            while (StateCensusCSVIterator.hasNext()) {
                noOfRecords++;
                CSVStateCode censusData = StateCensusCSVIterator.next();
                System.out.println(censusData.toString());
            }
            return noOfRecords;
        } catch (IOException e1) {
            throw new StateCensusAnalyzerException("Invalid path entered",StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
        } catch (IllegalStateException e2) {
            throw new StateCensusAnalyzerException("Invalid state present", StateCensusAnalyzerException.ExceptionType.INCORRECT_STATE);
        }
    }
}
