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
            Iterator<CSVStateCensus> StateCensusCSVIterator = new OpenCSVBuilder().getCSVFileIterator(reader,CSVStateCensus.class);
            int noOfRecords = getCount(StateCensusCSVIterator);
            System.out.println("Num Of Records : " + noOfRecords);
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
            Iterator<CSVStateCode> StateCensusCSVIterator = new OpenCSVBuilder().getCSVFileIterator(reader,CSVStateCode.class);
            int noOfRecords = getCount(StateCensusCSVIterator);
            System.out.println("Num Of Records : " + noOfRecords);
            return noOfRecords;
        } catch (IOException e1) {
            throw new StateCensusAnalyzerException("Invalid PATH Entered",StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
        } catch (IllegalStateException e2) {
            throw new StateCensusAnalyzerException("Invalid STATE Present", StateCensusAnalyzerException.ExceptionType.INCORRECT_STATE);
        }
    }

    private <E> int getCount(Iterator<E> csvIterator) {
        Iterable<E> csvIterable = () -> csvIterator;
        int noOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return noOfEnteries;
    }
}
