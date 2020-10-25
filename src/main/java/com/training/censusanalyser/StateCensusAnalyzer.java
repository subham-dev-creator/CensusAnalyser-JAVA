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
            ICSVBuilder<CSVStateCensus> builder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCensus> StateCensusCSVIterator = builder.getCSVFileIterator(reader,CSVStateCensus.class);
            int noOfRecords = getCount(StateCensusCSVIterator);
            System.out.println("Num Of Records : " + noOfRecords);
            return noOfRecords;
        } catch (IOException | CSVException e) {
            throw new StateCensusAnalyzerException("Invalid path entered", StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
        }
    }

    public int readStateCodeCSVData() throws StateCensusAnalyzerException{
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(csvFilePath);
            ICSVBuilder<CSVStateCode> builder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCode> StateCensusCSVIterator = builder.getCSVFileIterator(reader,CSVStateCode.class);
            int noOfRecords = getCount(StateCensusCSVIterator);
            System.out.println("Num Of Records : " + noOfRecords);
            return noOfRecords;
        } catch (IOException | CSVException e) {
            throw new StateCensusAnalyzerException("Invalid path entered", StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
        }
    }

    private <E> int getCount(Iterator<E> csvIterator) {
        Iterable<E> csvIterable = () -> csvIterator;
        int noOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return noOfEnteries;
    }
}
