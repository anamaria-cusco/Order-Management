package utils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileWriterClass {


    public static void printBill(String orderDetails) {

        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("ClientBill.txt", false);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.println(orderDetails);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException io) {// can't do anything }
            }

        }
    }
}


