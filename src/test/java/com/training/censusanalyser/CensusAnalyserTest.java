package com.training.censusanalyser;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class CensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:\\Users\\I524735\\IdeaProjects\\CensusAnalyser-JAVA\\src\\main\\resources\\IndiaStateCensusData.csv";

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() throws StateCensusAnalyzerException {
        Path pathlink = Paths.get(INDIA_CENSUS_CSV_FILE_PATH);
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(pathlink);
        int numOfRecords = censusAnalyser.readStateCensusCSVData();
        assertEquals(29, numOfRecords);
    }


}
