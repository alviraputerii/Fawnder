/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLearning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author vr
 */
public class TutorialMisahinKoma {
    public static void main(String[] args) {
        String test = "BaNdunG,    sura  baya, bEk As  i";
        String[] list = test.split(",");

        List<String> asd = new ArrayList<>();
        for (String s : list) {
            asd.add(s.replaceAll(" ", "").toLowerCase());
        }
        
        String petBreed = "bandung";
        String petBreed2 = "surabaya";
        System.out.println(asd);
        for (String s : asd) {
            System.out.println(petBreed2.contains(s));
        }
    }
}
