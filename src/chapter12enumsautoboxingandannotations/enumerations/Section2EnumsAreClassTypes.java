package chapter12enumsautoboxingandannotations.enumerations;

import java.util.Random;

/**
 * Each enumeration constant is an object of its enumeration type. Thus, when you define a constructor for an
 * enum, the constructor is called when each enumeration constant is created. Also, each enumeration has its own
 * copy of any instance variables defined by the enumeration.
 */
public class Section2EnumsAreClassTypes {
    public static void main(String[] args) {
        Dice a, b, c;
        a = Dice.Five;
        b = Dice.Random;
        c = Dice.Random;

        //Note that the constructor will only be invoked once throughout the life cycle of the enum type
        System.out.println(a.getValue()); // outputs 5
        System.out.println(b.getValue()); // outputs a random number between 1 and 6 inclusively
        System.out.println(c.getValue()); // outputs the same random number generated before
    }
}

enum Dice {
    One(1), Two(2), Three(3), Four(4), Five(5), Six(6), Random;
    private int value;
    Dice() {
        value = new Random().nextInt(1, 7);
    }
    Dice(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}