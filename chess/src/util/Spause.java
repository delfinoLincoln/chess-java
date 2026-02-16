package util;

public class Spause {
    public static void waitAnyKey() {
        System.out.print("Press any key to continue . . .");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}