package utils;

import geneticElement.ColorValues;

import java.util.ArrayList;

public class RandomProcess {

    public ArrayList<ColorValues> getColorValues(int clusterAmount){
        ArrayList<ColorValues> listColor = new ArrayList<>();
        for (int i=0; i<clusterAmount; i++){
            listColor.add(new ColorValues());
        }

        return listColor;
    }
}
