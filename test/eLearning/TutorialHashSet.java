/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLearning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author vr
 */
public class TutorialHashSet {
    public static void main(String[] args) {
        HashSet<String> testHashSet = new HashSet<>();
        List<String> testList = new ArrayList<>();
        testHashSet.add("a");
        testHashSet.add("a");
        testHashSet.add("a");
        testHashSet.add("a");
        testHashSet.add("b");
        testHashSet.add("b");
        testHashSet.add("b");
        testHashSet.add("b");
        System.out.println("I'm value from a Hashset : " + testHashSet);
        
        testList.add("a");
        testList.add("a");
        testList.add("a");
        testList.add("a");
        testList.add("b");
        testList.add("b");
        testList.add("b");
        testList.add("b");
        System.out.println("I'm value from a list : " + testList);
        
        testList = new ArrayList<>(testHashSet); // ohh pake ini de
        System.out.println(testList);
    }
}
