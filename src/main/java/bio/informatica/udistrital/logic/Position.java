/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.informatica.udistrital.logic;

import java.util.HashMap;
import java.util.Map;

/**
 * @author UDistrital
 */
public class Position {

    private int row;
    private int column;
    private NitrogenousBase rowNitrogenousBase = NitrogenousBase.NA;
    private NitrogenousBase columnNitrogenousBase = NitrogenousBase.NA;
    private int value;

    public Position(int row, int column, NitrogenousBase rowNitrogenousBase, NitrogenousBase columnNitrogenousBase, int value) {
        this.row = row;
        this.column = column;
        this.rowNitrogenousBase = rowNitrogenousBase;
        this.columnNitrogenousBase = columnNitrogenousBase;
        this.value = value;
    }

    public Position(int row, int column, int value) {
        this.row = row;
        this.column = column;
        rowNitrogenousBase = NitrogenousBase.NA;
        columnNitrogenousBase = NitrogenousBase.NA;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public NitrogenousBase getRowNitrogenousBase() {
        return rowNitrogenousBase;
    }

    public void setRowNitrogenousBase(NitrogenousBase rowNitrogenousBase) {
        this.rowNitrogenousBase = rowNitrogenousBase;
    }


    public NitrogenousBase getColumnNitrogenousBase() {
        return columnNitrogenousBase;
    }

    public void setColumnNitrogenousBase(NitrogenousBase columnNitrogenousBase) {
        this.columnNitrogenousBase = columnNitrogenousBase;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        Map<String, Object> map = new HashMap<>();
        map.put("row", row);
        map.put("column", column);
        map.put("nitroRow", rowNitrogenousBase);
        map.put("nitroColumns", columnNitrogenousBase);
        map.put("value", value);
        return map.toString();
    }
}
