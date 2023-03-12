//utilizam File din Java.io pentru a deschide fisierul text
import java.io.File
import kotlin.collections.MutableList
fun GetUniqueWordCount(all_words : List<String>) : MutableMap<String, Int> {
    //functia pentru calculul cuvintelor unice
    val result = mutableMapOf<String, Int>()
    return result
}

fun GetUniqueCharCount(all_chars : List<String>) : MutableMap<Char, Int> {
    //functia pentru calculul caracterelor unice
    val result = mutableMapOf<Char, Int>()
    return result
}

fun SortByHitCount(items : MutableMap<Char, Int>, how: Boolean) : MutableMap<Int, Char> {
    //functia de sortare a caracterelor, dupa valoare (frecventa), atat crescator cat si descrescator, in functie de how
    var result = mutableMapOf<Int, Char>()
    if (how == true) {
         result = items.entries.sortedBy { it.value }.associate { it.value to it.key }.toMutableMap()
    } else {
         result = items.entries.sortedByDescending { it.value }.associate { it.value to it.key }.toMutableMap()
    }
    return result
}

fun Frecv_Trim_Words(trim_words :MutableList<String>): HashMap<String,Int>{
    val search = HashMap<String,Int>()
    trim_words.forEach {
        if(!search.containsKey(it)){
            search.put(it,1)
        }
        else{
            search[it] = search[it]!! + 1
        }
    }
    return search
}

fun Frecv_Chars(trim_words :MutableList<String>): HashMap<Char,Int>{
    val search = HashMap<Char,Int>()
    trim_words.forEach {
        for (ch in it) {
            if (!search.containsKey(ch)) {
                search.put(ch, 1)
            } else {
                search[ch] = search[ch]!! + 1
            }
        }
    }
    return search
}

//functia main()
fun main(args : Array<String>){
    //citim liniile din fisier
    val lines = File("Fisier.txt").reader().readText()
    //construim un array de cuvinte, seprand prin spatiu
    val words = lines.split(" ")

    //eliminam semnele de punctuatie de pe marginile cuvintelor
    val trim_words = mutableListOf<String>()
    words.forEach {
        val filter = it.trim(',','.','"','?', '!')
        trim_words += filter.toLowerCase()
        print(filter + " ")
    }
    println("\n")

    //construim o lista cu toate caracterele folosite 'A..Z'
    val chars = mutableListOf<String>()
    trim_words.forEach {
        for (c in it){
            if (c in 'a'..'z' || c in 'A'..'Z') {
                chars += c.toUpperCase().toString()
                print(c.toUpperCase())
            }
        }
    }
    println("\n")


  /* val search= Frecv_Trim_Words(trim_words)
    for(i in search){
        if(i.value==1)
          println(i.key+"-"+ i.value+" \n")
    }
*/

    val search2= Frecv_Chars(chars)
    for(i in search2){
        if(i.value==1)
            println(i.key+"-"+ i.value+" \n")
    }

    val sorted= SortByHitCount(search2,true)
    for(i in sorted){
        println(" ${i.key} - ${i.value} ")
    }
}
    //Pentru constructia histogramelor, R foloseste un mecanism prin care asociaza caracterelor unice, numarul total de aparitii (frecventa)
    // 1. Construiti in Kotlin acelasi mecanism de masurare a frecventei elementelor unice si afisati cuvintele unice din trim_words
    // 2. Construiti in Kotlin acelasi mecanism de masurare a frecventei elementelor unice si afisati caracterele unice din chars
    // 3. Pentru frecventele caracterelor unice caclulate anterior si
    //      A. Afisati perechile (frecventa -> Caracter) sortate crescator si descrescator
    //      B. afisati graficele variatiei de frecventa sortate anterior crescator si descrescator si concatenati-le intr-un grafic de puncte

    //construim histograma pentru cuvinte
    //RHistogram.BuildHistogram(trim_words.toTypedArray(), "Words", true)
    //construim histograma pentru caractere
    //RHistogram.BuildHistogram(chars.toTypedArray(), "Chars", true)

