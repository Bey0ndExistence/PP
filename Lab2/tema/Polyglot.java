//import libraria principala polyglot din graalvm
import org.graalvm.polyglot.*;
import java.util.Arrays;
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

    //functia MAIN
    public static void main(String[] args) {
       /* //construim un context pentru evaluare elemente JS
        Context polyglot = Context.create();
        //construim un array de string-uri, folosind cuvinte din pagina web:  https://chrisseaton.com/truffleruby/tenthings/
        Value array = polyglot.eval("js", "[\"If\",\"we\",\"run\",\"the\",\"java\"]");
        //pentru fiecare cuvant, convertim la upcase folosind R si calculam suma de control folosind PYTHON
        for (int i = 0; i < array.getArraySize();i++){
            String element = array.getArrayElement(i).asString();
            String upper = RToUpper(element);
            int crc = SumCRC(upper);
            System.out.println(upper + " -> " + crc);
        }
        */
        int[] List= PythonRandList();
        for(int i=0;i<20;i++) {
            System.out.printf("%d ",List[i]);
        }
        System.out.println("");
        JSprint(List);

        sortR(List);
        // inchidem contextul Polyglot
        //polyglot.close();
    }
}

