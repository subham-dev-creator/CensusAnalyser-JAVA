package com.training.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCode {
    @CsvBindByName(column = "State Name")
    public String stateName;

    @CsvBindByName(column = "State Code")
    public String stateCode;

    public CSVStateCode(String stateName, String stateCode) {
        this.stateName = stateName;
        this.stateCode = stateCode;
    }

    @Override
    public String toString() {
        return "CSVStateCode{" +
                ", stateName='" + stateName + '\'' +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
