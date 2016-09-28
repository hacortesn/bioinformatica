/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.informatica.udistrital.logic;

import java.util.LinkedList;
import java.util.List;

/**
 * @author UDistrital
 */
public enum NitrogenousBase {
    A(0, "A"),
    G(1, "G"),
    C(2, "C"),
    T(3, "T"),
    NA(-1, "-");
    private int position;
    private String letter;

    NitrogenousBase(int position, String letter) {
        this.position = position;
        this.letter = letter;
    }

    public int getPosition() {
        return position;
    }

    public String getLetter() {
        return letter;
    }

    public static List<NitrogenousBase> getBases(List<String> values) {

        List<NitrogenousBase> nitrogenousBases = new LinkedList<>();
        values.forEach(letter -> nitrogenousBases.add(NitrogenousBase.valueOf(letter)));
        return nitrogenousBases;


    }


}
