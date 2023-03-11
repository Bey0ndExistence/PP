import java.io.File
import java.io.FileWriter
import java.io.IOException

fun addToDictionary(key: String, value: String, dictionary: HashMap<String, String>) {
    dictionary[key] = value
}
fun main(args : Array<String>){
    val file = File("input.txt")

    val dictionarFisier = hashMapOf<String, String>()
    file.forEachLine {
        val (key, value) = it.split("=")
        dictionarFisier [key] = value
    }


    var Dictionar=dictionarFisier
    /*var Dictionar = hashMapOf<String, String>(
        "Once" to "Odata",
        "upon" to "ca",
        "a" to "",
        "time" to "niciodata",
        "there" to "acolo",
        "was" to "a fost",
        "an" to "o",
        "old" to "batrana",
        "woman" to "femeie",
        "who" to "care",
        "loved" to "iubea",
        "baking" to "sa gateasca",
        "gingerbread" to "turta dulce",
        "She" to "Ea",
        "would" to "ar fi",
        "bake" to "gatit",
        "gingerbread" to "turta dulce",
        "cookies" to "biscuiti",
        "cakes" to "prajituri",
        "houses" to "case",
        "and" to "si",
        "people" to "oameni",
        "all" to "toti",
        "decorated" to "decorati",
        "with" to "cu",
        "chocolate" to "ciocolata",
        "peppermint" to "menta",
        "caramel" to "caramel",
        "candies" to "bomboane",
        "colored" to "colorate",
        "ingredients" to "ingrediente"
    )

     */
    val Poveste = "Once upon a lemon time there was an old woman who loved baking gingerbread. She would bake gingerbread cookies, cakes, houses and gingerbread people, all decorated with chocolate and peppermint, caramel candies and colored ingredients. "
    val words1 = Poveste.split(" ")
    println("Cuvintele din poveste [${words1.count()}]:")
    for (word in words1)
        print(word + " ")
    val words2 = mutableListOf<String>()
    for (word in words1){
        words2.add(word.trim(',','.'))
    }
    addToDictionary("lemon", "lamaie", Dictionar)

    println("\n")
    println("Povestea tradusa ar suna cam asa:")
    for (item in words2){
        if (Dictionar.contains(item))
            print(Dictionar[item])
        else
            print("[$item]")
        print(" ")
    }
    val filename = "poveste-tradusa.txt"
    try {
        val writer = FileWriter(filename)
        writer.write(Poveste)
        writer.close()
        println("\nPovestea tradusa a fost salvata in fisierul $filename")
    } catch (ex: IOException) {
        println("A aparut o eroare la scrierea fisierului $filename")
        ex.printStackTrace()
    }
}