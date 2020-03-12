package test;

import java.util.ArrayList;
import java.util.Collections;

public class MainCheckList {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(3);
        list1.add(2);
        list1.add(1);
        Collections.sort(list1);
        System.out.println(list1);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);

        System.out.println(list1.equals(list2));
    }
}
