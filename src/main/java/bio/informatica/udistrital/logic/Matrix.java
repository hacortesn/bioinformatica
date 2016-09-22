/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.informatica.udistrital.logic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author UDistrital
 */
public class Matrix {

    private int score = 0;

    public static int[] simularity = {
            // A    G   C   T
            10, -1, -3, -4, //A
            -1, 7, -5, -3, //G
            -3, -5, 9, 0, //C
            -4, -3, 0, 8};//T

    private static int D = -5;

    private final int rows;
    private final int columns;
    private List<List<Integer>> matrix = new LinkedList<>();

    private List<NitrogenousBase> rowNitrogenousBases = new LinkedList<>();
    private List<NitrogenousBase> columnNitrogenousBases = new LinkedList<>();
    private List<Position> positions = new LinkedList<>();

    public Matrix(List<NitrogenousBase> rowNitrogenousBases, List<NitrogenousBase> columnNitrogenousBases) {
        this.rowNitrogenousBases = rowNitrogenousBases;
        this.columnNitrogenousBases = columnNitrogenousBases;
        rows = this.rowNitrogenousBases.size() + 1;
        columns = this.columnNitrogenousBases.size() + 1;
        processMatrix();
        toAlignment();
    }

    private void processMatrix() {

        for (int i = 0; i < rows; i++) {
            List<Integer> values = new LinkedList<>();
            for (int j = 0; j < columns; j++) {
                values.add(j, 0);
            }
            matrix.add(i, values);
        }

        for (int i = 0; i < columns; i++) {
            matrix.get(0).set(i, i * D);
        }
        for (int j = 0; j < rows; j++) {
            matrix.get(j).set(0, j * D);
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                int valueCellDiag = getValueIn(i - 1, j - 1) + simular(rowNitrogenousBases.get(i - 1).getPosition(), columnNitrogenousBases.get(j - 1).getPosition());
                int valueCellUp = getValueIn(i - 1, j) + D;
                int valueCellLeft = getValueIn(i, j - 1) + D;
                valueCellDiag = Math.max(valueCellDiag, valueCellUp);
                setValueIn(i, j, Math.max(valueCellDiag, valueCellLeft));
            }
        }
        score = getValueIn(rows - 1, columns - 1);
    }

    private void toAlignment() {
        int i = rows - 1, j = columns - 1;
        String alA = "";
        String alB = "";

        Position position = null;//= new Position(i, j, rowNitrogenousBases.get(i - 1), columnNitrogenousBases.get(j - 1), getValueIn(i, j));
        //positions.add(position);

        while (i > 0 && j > 0) {
            int score = getValueIn(i, j);
            int scoreDiagonal = getValueIn(i - 1, j - 1);
            int scoreUp = getValueIn(i, j - 1);
            int scoreLeft = getValueIn(i - 1, j);
            System.out.println("initial row->" + i + " column->" + j + " score->" + score);
            if (score == scoreDiagonal + simular(rowNitrogenousBases.get(i - 1).getPosition(), columnNitrogenousBases.get(j - 1).getPosition())) {
                System.out.println("diagonal row->" + i + " column->" + j);
                alA = rowNitrogenousBases.get(i - 1).getLetter() + alA;
                alB = columnNitrogenousBases.get(j - 1).getLetter() + alB;
                i--;
                j--;
                position = new Position(i, j, score);
                position.setRowNitrogenousBase(rowNitrogenousBases.get(i));
                position.setColumnNitrogenousBase(columnNitrogenousBases.get(j));
            } else if (score == scoreLeft + D) {
                System.out.println("left row->" + i + " column->" + j);
                alA = rowNitrogenousBases.get(i - 1).getLetter() + alA;
                alB = "-" + alB;
                i--;

                position = new Position(i, j, score);
                position.setRowNitrogenousBase(rowNitrogenousBases.get(i));

            } else if (score == scoreUp + D) {
                System.out.println("up row->" + i + " column->" + j);
                alA = "-" + alA;
                alB = columnNitrogenousBases.get(j - 1) + alB;
                j--;

                position = new Position(i, j, score);
                position.setColumnNitrogenousBase(columnNitrogenousBases.get(j));
            }
            positions.add(position);

        }
        while (i > 0) {
            alA = rowNitrogenousBases.get(i - 1) + alA;
            alB = "-" + alB;
            i--;
            position = new Position(-1, -1, 0);
            position.setRowNitrogenousBase(rowNitrogenousBases.get(i));
            positions.add(position);
        }
        while (j > 0) {
            alA = "-" + alA;
            alB = columnNitrogenousBases.get(j - 1) + alB;
            j--;
            position = new Position(-1, -1, 0);
            position.setColumnNitrogenousBase(columnNitrogenousBases.get(j));
            positions.add(position);
        }

        Collections.reverse(positions);
        System.out.println(alB);
        System.out.println(alA);
        System.out.println(positions);


    }

    public void setValueIn(int row, int column, int value) {
        matrix.get(row).set(column, value);
    }

    public int getValueIn(int row, int column) {
        return matrix.get(row).get(column);
    }

    private static int simular(int first, int second) {
        return simularity[first * 4 + second];
    }

    public int getColumns() {
        return columns;
    }

    public List<List<Integer>> getMatrix() {
        return matrix;
    }

    public int getRows() {
        return rows;
    }

    public int getScore() {
        return score;
    }

    public List<Position> getPathWay() {
        return positions;
    }
}
