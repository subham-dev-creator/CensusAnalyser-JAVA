package com.training.censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder<E> implements ICSVBuilder {
    @Override
    public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws StateCensusAnalyzerException {
        return this.getCSVBean(reader, csvClass).iterator();
    }

    @Override
    public List getCSVFileList(Reader reader, Class csvClass) throws StateCensusAnalyzerException {
        return this.getCSVBean(reader, csvClass).parse();
    }

    private CsvToBean<E> getCSVBean(Reader reader, Class csvClass) throws StateCensusAnalyzerException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true).withSeparator(',');
            return csvToBeanBuilder.build();
        } catch (IllegalStateException e) {
            throw new StateCensusAnalyzerException(e.getMessage(),
                    StateCensusAnalyzerException.ExceptionType.INCORRECT_STATE);
        }
    }


}
