package com.training.censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:\\Users\\I524735\\IdeaProjects\\CensusAnalyser-JAVA\\src\\main\\resources\\IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "C:\\Users\\I524735\\IdeaProjects\\CensusAnalyser-JAVA\\src\\test\\resources\\IndiaStateCensusData.csv";
    private static final String WRONG_FILE_TYPE = "C:\\Users\\I524735\\IdeaProjects\\CensusAnalyser-JAVA\\src\\main\\resources\\IndiaStateCensusData.csv";
    private static final String WRONG_DELIMITER_CSV_FILE_PATH = "C:\\Users\\I524735\\IdeaProjects\\CensusAnalyser-JAVA\\src\\main\\resources\\WrongDelimiterIndiaStateCensusData.csv";
    private static final String WRONG_HEADER_STATE_CSV_FILE_PATH = "C:\\Users\\I524735\\IdeaProjects\\CensusAnalyser-JAVA\\src\\main\\resources\\WrongHeaderIndiaStateCensusData.csv";

    // Correct/Happy Case From the Records in the INDIAN Census CSV file
    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() throws StateCensusAnalyzerException {
        Path pathlink = Paths.get(INDIA_CENSUS_CSV_FILE_PATH);
        StateCensusAnalyzer censusAnalyser = new StateCensusAnalyzer(pathlink);
        int numOfRecords = censusAnalyser.readStateCensusCSVData();
        assertEquals(29, numOfRecords);
    }

    // TC 1.1
    // Wrong/Sad Case From the Records in the INDIAN Census CSV file
    @Test
    public void givenIndianCensusCSVFile_ReturnsWrongRecords() throws StateCensusAnalyzerException {
        Path pathlink = Paths.get(INDIA_CENSUS_CSV_FILE_PATH);
        StateCensusAnalyzer censusAnalyser = new StateCensusAnalyzer(pathlink);
        int numOfRecords = censusAnalyser.readStateCensusCSVData();
        assertTrue(2!=numOfRecords);
    }

    // TC 1.2
    @Test
    public void givenIndiaCensusData_WithPath_ShouldThrowException() {
        try{
            Path pathlink = Paths.get(WRONG_CSV_FILE_PATH);
            ExpectedException exceptionRule=ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyzerException.class);
            StateCensusAnalyzer censusAnalyzer = new StateCensusAnalyzer(pathlink);
            censusAnalyzer.readStateCensusCSVData();
        }catch(StateCensusAnalyzerException e) {
            Assert.assertEquals(StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH, e.type);
        }

    }

    // TC 1.3
    @Test
    public void givenStateCensusCSVFile_WhenIncorrectState_ShouldThrowException() {
        try{
            ExpectedException exceptionRule=ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyzerException.class);
            StateCensusAnalyzer censusAnalyzer = new StateCensusAnalyzer(Paths.get(WRONG_FILE_TYPE));
            censusAnalyzer.readStateCensusCSVData();
        }catch(StateCensusAnalyzerException e) {
            Assert.assertEquals(StateCensusAnalyzerException.ExceptionType.INCORRECT_STATE, e.type);
        }
    }

    // TC 1.4
    @Test
    public void givenStateCensusCSVFile_WhenIncorrectDelimeter_ShouldThrowException() {
        try{
            ExpectedException exceptionRule=ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyzerException.class);
            StateCensusAnalyzer censusAnalyzer = new StateCensusAnalyzer(Paths.get(WRONG_DELIMITER_CSV_FILE_PATH));
            censusAnalyzer.readStateCensusCSVData();
        }catch(StateCensusAnalyzerException e) {
            Assert.assertEquals(StateCensusAnalyzerException.ExceptionType.INCORRECT_DELIMITER, e.type);
        }
    }

    // TC 1.5
    @Test
    public void givenStateCensusCSVFile_WhenIncorrectCSVHeader_ShouldThrowException() {
        try{
            ExpectedException exceptionRule=ExpectedException.none();
            exceptionRule.expect(StateCensusAnalyzerException.class);
            StateCensusAnalyzer censusAnalyzer = new StateCensusAnalyzer(Paths.get(WRONG_HEADER_STATE_CSV_FILE_PATH));
            censusAnalyzer.readStateCensusCSVData();
        }catch(StateCensusAnalyzerException e) {
            Assert.assertEquals(StateCensusAnalyzerException.ExceptionType.INCORRECT_CSV_HEADER, e.type);
        }
    }

}
