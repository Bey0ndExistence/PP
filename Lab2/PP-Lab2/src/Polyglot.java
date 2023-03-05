//import libraria principala polyglot din graalvm
import org.graalvm.polyglot.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

//clasa principala - aplicatie JAVA
class Polyglot {
    //metoda privata pentru conversie low-case -> up-case folosind functia toupper() din R
    private static String RToUpper(String token){
        //construim un context care ne permite sa folosim elemente din R
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        //folosim o variabila generica care va captura rezultatul excutiei funcitiei R, toupper(String)
        //pentru aexecuta instructiunea I din limbajul X, folosim functia graalvm polyglot.eval("X", "I");
        Value result = polyglot.eval("R", "toupper(\""+token+"\");");
        //utilizam metoda asString() din variabila incarcata cu output-ul executiei pentru a mapa valoarea generica la un String
        String resultString = result.asString();
        // inchidem contextul Polyglot
        polyglot.close();

        return resultString;
    }

    //metoda privata pentru evaluarea unei sume de control simple a literelor unui text ASCII, folosind PYTHON
    private static int SumCRC(String token){
        //construim un context care ne permite sa folosim elemente din PYTHON
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        //folosim o variabila generica care va captura rezultatul excutiei functiei PYTHON, sum()
        //avem voie sa inlocuim anumite elemente din scriptul pe care il construim spre evaluare, aici token provine din JAVA, dar va fi interpretat de PYTHON
        Value result = polyglot.eval("python", "sum(ord(ch) for ch in '" + token + "')");
        //utilizam metoda asInt() din variabila incarcata cu output-ul executiei, pentru a mapa valoarea generica la un Int
        int resultInt = result.asInt();
        // inchidem contextul Polyglot
        polyglot.close();

        return resultInt;
    }

    private static int[] PythonRandList(){
        Context polyglot = Context.create();
        Value list = polyglot.eval("python",
                "import random\n" +
                        "a=[]\n" +
                        "for j in range(20):\n" +
                        "    a.append(random.randint(0,200))\n" +
                        "a");

        int[] v = list.as(int[].class);
        return v;
    }

    private static void JSprint(int[] v){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        polyglot.eval("js", "for (let i = 0; i < 20; i++) {" +
                "console.log(" + Arrays.toString(v) + "[i]);" +
                "}");
        polyglot.close();
    }

    private static void sortR(int[] v){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        polyglot.eval("R", "sort_Cut_Mean<- function(v) {\n" +
                "  array_sortat <- sort(v)\n" +
                "  dim <- length(v)\n" +
                "  firstIndex <- dim * 0.2\n" +
                "  lastIndex <-  dim * 0.8\n" +
                "  cutArray <- array_sortat[firstIndex:lastIndex]\n" +
                "  mean <- mean(cutArray)\n" +
                "  return(mean)\n" +
                "}");
        Value sort_Cut_Mean = polyglot.getBindings("R").getMember("sort_Cut_Mean");
        Value result = sort_Cut_Mean.execute(v);
        double mean = result.asDouble();
        System.out.println("Media : " + mean);
        polyglot.close();
    }
    private static void Binomial(){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value numbers = polyglot.eval("python","\n" +
                "n, x = input('Numar de aruncari (n >= 1) si Numar de succese( 1<= x <= n): ').split()\n" +
                "n=int(n)\n" +
                "x=int(x)");
        int n = polyglot.getBindings("python").getMember("n").asInt();
        int x = polyglot.getBindings("python").getMember("x").asInt();

        System.out.println(n);
        System.out.println(x);
        Value binomial = polyglot.eval("R","\n" +
                "x <- pbinom("+ x +","+ n +",0.5)\n" +
                "x");
        double rez = binomial.asDouble();
        System.out.println(rez);
        polyglot.close();


    }
    //functia MAIN
    public static void main(String[] args) {
        //construim un context pentru evaluare elemente JS
        Context polyglot = Context.create();
        //construim un array de string-uri, folosind cuvinte din pagina web:  https://chrisseaton.com/truffleruby/tenthings/
        Value array = polyglot.eval("js", "[\"WE\",\"EW\",\"java\",\"we\",\"fi\", \"avaj\"]");
        //pentru fiecare cuvant, convertim la upcase folosind R si calculam suma de control folosind PYTHON
        Set<String> printedPairs = new HashSet<>();
        for (int i = 0; i < array.getArraySize()-1;i++){
            String element1 = array.getArrayElement(i).asString();
            String upper1 = RToUpper(element1);
            int crc1 = SumCRC(upper1);
            for(int j = i+1; j< array.getArraySize();j++) {
                String element2 = array.getArrayElement(j).asString();
                String upper2 = RToUpper(element2);
                int crc2 = SumCRC(upper2);
                if (crc1 == crc2) {
                    String pair = upper1 + " -> " + upper2 + " -> " + crc1;
                    if (!printedPairs.contains(pair)) {
                        System.out.println(pair);
                        printedPairs.add(pair);
                    }
                }
            }
        }

        Binomial();
      //Continuarea laboratorului de aici
       /* int[] List= PythonRandList();
        for(int i=0;i<20;i++) {
            System.out.printf("%d ",List[i]);
        }
        System.out.println("");
        JSprint(List);

        sortR(List);
        */
        // inchidem contextul Polyglot
        //polyglot.close();
    }
}

