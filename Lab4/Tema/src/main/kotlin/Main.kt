fun main() {
    val notițe = NoteApp()
    while (true) {
        println("Introduceti actiunea :")
        println("1. Afișare notițe curente ")
        println("2. Încarcă notiță specifică")
        println("3. Creare notiță")
        println("4. Șterge notița")
        println("5. Ieșire din program")
        when (readLine()?.toInt()) {
            1 -> notițe.afișareListăNotițe()
            2 -> {
                print("Introduceți titlul: ")
                notițe.încărcare_anumită_notiță(readLine()?.trim() ?: "")
            }
            3 -> notițe.creareNotiță()
            4 -> {
                print("Introduceți titlul: ")
                notițe.deleteNoteByAuthor(readLine()?.trim() ?: "")
            }
            5 -> return
            else -> println("Nu-i bună comanda asta")
        }
    }
}