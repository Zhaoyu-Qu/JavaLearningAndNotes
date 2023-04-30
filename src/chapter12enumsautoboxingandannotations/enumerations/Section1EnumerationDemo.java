package chapter12enumsautoboxingandannotations.enumerations;

public class Section1EnumerationDemo {
    public static void main(String[] args) {
        // declare a as a variable of enumeration type Brand
        Brand a;
        // a can only be assigned a value that is defined by the enumeration type Brand
        a = Brand.BMW;

        Brand b = Brand.Toyota;
        Country c = Country.Australia;
        // Two enumeration constants of the same type can be compared
        // if (a == c) ... won't work
        if (a == b) {
            System.out.println("A and B are the same brand.");
        }

        // enum constants can be used to control a switch statement
        // Pay attention to the syntax
        switch (a) {
            // Brand.Toyota won't work.
            // This is because the enum type has been implicitly specified
            case Toyota:
                System.out.println("A is Toyota.");
                break;
            case BMW:
                System.out.println("A is BMW.");
                break;
            default:
                System.out.println("A is neither Toyota or BMW.");
        }

        System.out.println(Brand.Honda); // "Honda" is displayed

        // two predefined methods
        // public static enum-type[] values()
        // public static enum-type valueOf(String str)

        Brand[] brands = Brand.values();
        for (Brand brand : brands) {
            System.out.println(brand);
        }

        System.out.println("\n" + Brand.valueOf("Lexus"));
    }
}

enum Brand {
    //Enumeration constants
    //Note the semicolon is not needed
    Toyota, BMW, Mercedes, Lexus, Honda, Nissan, Volkswagen
}

enum Country {
    China, Australia, Finland
}