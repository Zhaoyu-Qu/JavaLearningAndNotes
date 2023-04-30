package chapter12enumsautoboxingandannotations.enumerations;

/**
 * An enum class can't extend another class or be extended
 * However, all enum classes automatically inherit one class, java.lang.Enum
 * Some of the methods inherited from Java.lang.Enum are discussed here
 */
public class Section3EnumInheritance {
    public static void main(String[] args) {
        Dice a = Dice.Five;
        // Outputs 4, because Dice.One is the 0th constants defined in the enum type,
        // while Dice.Five is the 4th
        System.out.println(a.ordinal());

        //The compareTo() method compares the ordinal values
        //Both objects must be enum constants of the same enum types
        Dice b = Dice.Two;
        if (a.compareTo(b) > 0) {
            System.out.println("a > b"); //true
        } else {
            System.out.println("a !> b");
        }

        //equals() will only return true if both objects refer to the same enum constant
        Integer c = 4;
        if (a.equals(c)) {
            System.out.println("a == c");
        } else {
            System.out.println("a != c"); // true
        }

        Dice d = Dice.Five;
        if (a.equals(d)) {
            System.out.println("a == d"); // true
        } else {
            System.out.println("a != d");
        }

    }
}