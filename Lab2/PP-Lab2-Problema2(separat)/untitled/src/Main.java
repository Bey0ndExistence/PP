import org.graalvm.polyglot.*;
import java.util.Scanner;
import java.io.*;

class Polyglot {

    private static void linear_regression(String numeFisier,String path,String color) throws IOException
    {
        File file = new File("/home/student/Desktop/PP/Lab2/PP-Lab2-Problema2(separat)/dataset.txt");
        Scanner citireFisier = new Scanner(file);
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value data = polyglot.eval("R",
                "library(lattice)\n" +
                "x <-  c(" + citireFisier.nextLine() + ")\n" +
                "y <-  c(" + citireFisier.nextLine() + ")\n" +
                "model <- lm(y~x)\n" +
                "png(file = '" + path + numeFisier + ".png')\n" +
                "plot(x,y,col = '" + color + "')\n" +
                "abline(model)\n" +
                "dev.off()");
        polyglot.close();
        citireFisier.close();
    }

    public static void main(String[] args) throws IOException
    {
        Scanner r = new Scanner(System.in);
        FileWriter file = new FileWriter("/home/student/Desktop/PP/Lab2/PP-Lab2-Problema2(separat)/dataset.txt");
        String s;

        System.out.print("Inputuri pentru X cu virgula intre ele: ");
        s = r.nextLine();
        file.write(s);
        file.write('\n');

        System.out.print("Inputuri pentru Y cu virgula intre ele: ");
        s = r.nextLine();
        file.write(s);
        file.close();

        System.out.print("Numele desenului/fisierului: ");
        String numeFisier = r.nextLine();
        String path = "/home/student/Desktop/PP/Lab2/PP-Lab2-Problema2(separat)/";

        System.out.print("Introduceti o culoare pentru puncte in engleza: ");
        String color = r.nextLine();

        linear_regression( numeFisier, path, color);
    }
}