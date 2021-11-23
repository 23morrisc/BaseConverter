import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

/**
 * Base conversions stuff
 * @version Thursday 11/18/2021
 * @author 23morrisc
 */

public class BaseConverter {
    // Constructor for class.
    public BaseConverter() {

    }

    /**
     * Convert a String num in fromBase to base-10 int.
     * @param num
     * @param fromBase
     * @return an int
     */

    public int strToInt(String num, String fromBase) {
        int base = Integer.parseInt(fromBase);
        String alpha = "0123456789ABCDEF";
        int sum = 0, exp = 0;
        for (int i = num.length()-1; i >= 0; i--) {
            sum += alpha.indexOf("" + num.charAt(i)) * Math.pow(base, exp);
            exp++;
        }
        return sum;
    }

    /**
     * Convert a base-10 int to a String number of base toBase.
     * @param num
     * @param toBase
     * @return a string
     */

    public String intToStr(int num, int toBase) {
        String alpha = "0123456789ABCDEF";
        String toNum = "";

        while (num > 0) {
            toNum = alpha.charAt(num % toBase) + toNum;
            num /= toBase;
        }

        if (toNum.equals(" "))
            return "0";

        return toNum;
    }

    /**
     * Opens the file stream, inputs data one line at a time, converts, prints the result to the console window
     * and writes data to the output stream.
     * @return jack and squat
     */

    public void inputConvertPrintWrite() {
        //File file = new File("datafiles/values10.dat");
        Scanner sc = null;
        PrintWriter pw = null;
        try {

            sc = new Scanner(new File("datafiles/values500.dat"));
            pw = new PrintWriter(new File("datafiles/converted.dat"));

            while (sc.hasNext()) {
                String[] line = sc.nextLine().split("\t");
                // line = {"24A4B56", "13", "6"};
                //items to test: line[1], line [2];
                int fromBase = Integer.parseInt(line[1]);
                int toBase = Integer.parseInt(line[2]);



                if (fromBase < 2 || fromBase > 16) {

                    System.out.println("invalid input base " + fromBase);

                } else if (toBase < 2 || toBase > 16) {

                    System.out.println("invalid output base " + toBase);

                } else {

                        String finishedNum = intToStr(strToInt(line[0], line[1]), toBase);
                        System.out.print(line[0]+" base "+line[1]+" = "+finishedNum+" base "+toBase);
                        pw.print(line[0]+"\t"+fromBase+"\t"+finishedNum+"\t"+toBase+"\n");

                    System.out.println();
                    //pw.println();
                }


            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sc != null) {
            sc.close();
        }
        if (pw != null) {
            pw.close();
        }

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method for class BaseConverter. Yours should look just like this!

    /**
     * Main method for the class
     * @param args if needed
     */

    public static void main(String[] args) {
        BaseConverter app = new BaseConverter();
        app.inputConvertPrintWrite();

        //System.out.println(app.strToInt("21221201", "3"));
        //System.out.println(app.intToStr(5789324, 16));
    }
}

