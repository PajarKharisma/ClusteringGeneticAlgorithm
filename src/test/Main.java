package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
       /* ArrayList<Address> biodata = new ArrayList<Address>();
        biodata.add(new Address(0));
        biodata.add(new Address(1));
        biodata.add(new Address(2));
        biodata.add(new Address(3));

        for (Address address:biodata) {
            System.out.println(address.getValue());
        }
        System.out.println("===============================================");
        Collections.shuffle(biodata);
        for (Address address:biodata) {
            System.out.println(address.getValue());
        }*/
        Random random = new Random();
        int cluster = random.nextInt(5) + 2;
        System.out.println(cluster);
    }
}

class Address{
    private int value;

    public Address(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
