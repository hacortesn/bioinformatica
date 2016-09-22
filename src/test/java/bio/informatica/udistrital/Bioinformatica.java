/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.informatica.udistrital;


import bio.informatica.udistrital.logic.Matrix;
import bio.informatica.udistrital.logic.NitrogenousBase;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author UDistrital
 */
public class Bioinformatica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Matrix m;
        List<NitrogenousBase> sequence1 = Arrays.asList(
                NitrogenousBase.A,
                NitrogenousBase.C,
                NitrogenousBase.C,
                NitrogenousBase.G,
                NitrogenousBase.T,
                NitrogenousBase.C,
                NitrogenousBase.T,
                NitrogenousBase.T
        );

        List<NitrogenousBase> sequence2 = Arrays.asList(
                NitrogenousBase.C,
                NitrogenousBase.G,
                NitrogenousBase.T,
                NitrogenousBase.C,
                NitrogenousBase.T,
                NitrogenousBase.T
        );

        m = new Matrix(sequence2, sequence1);

        int r = m.getRows();
        int c = m.getColumns();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(m.getValueIn(i, j) + "\t");
            }
            System.out.println("");
        }

        System.out.println("final score" + m.getScore());

    }

}
