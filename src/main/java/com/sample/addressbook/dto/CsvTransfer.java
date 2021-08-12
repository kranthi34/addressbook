package com.sample.addressbook.dto;

import java.util.ArrayList;
import java.util.List;

public class CsvTransfer<T> {

    private List<String[]> csvStringList;

    private List<T> csvList;

    public CsvTransfer() {}

    public List<String[]> getCsvStringList() {
        if (csvStringList != null) return csvStringList;
        return new ArrayList<String[]>();
    }

    public void addLine(String[] line) {
        if (this.csvList == null) this.csvStringList = new ArrayList<>();
        this.csvStringList.add(line);
    }

    public void setCsvStringList(List<String[]> csvStringList) {
        this.csvStringList = csvStringList;
    }

    public void setCsvList(List<T> list) {
        this.csvList = list;
    }

    public List<T> getCsvList() {
        if (csvList != null) return csvList;
        return new ArrayList<T>();
    }
}