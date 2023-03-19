import java.io.File
import java.time.LocalDateTime

class NoteApp {
    private val notite = mutableListOf<Note>()

    fun afișareListăNotițe() {
        if (notite.isEmpty()) {
            println("Nu exista notițe încă")
        } else {
            println("Listă notițe:")
            notite.forEachIndexed { nrNotiță, note ->
                println("Nr.${nrNotiță + 1} : Conținut: \"${note.getContent()}\" de marele autor: \"${note.getAuthor()?.getName()}\" pe data de ${note.getDateTime()}")
            }
        }
    }

    fun încărcare_anumită_notiță(author: String) {
        val note = notite.find { it.getContent() == author }
        if (note != null) {
            println("${note.getContent()} by ${note.getAuthor()?.getName()} on ${note.getDateTime()}: ${note.getContent()}")
        } else {
            println("Note not found.")
        }
    }

    fun creareNotiță() {
        print("Introduceti continut: ")

        val content = readLine() ?: ""

        print("Introduceti nume autor: ")

        val authorName = readLine() ?: ""
        val author = User(authorName)
        val note = Note()

        note.setContent(content)
        note.setAuthor(author)
        note.setDateTime(LocalDateTime.now())

        notite.add(note)
        println("S-a creat notita.")
        salvează_în_fișier()
    }


    private fun salvează_în_fișier() {
        val file = File("output.txt")
        file.printWriter().use { out ->
            notite.forEach {
                out.println(it.getAuthor()?.getName() ?: "")
                out.println(it.getContent())
                out.println(it.getDateTime())

            }
        }
    }

    private fun șterge_din_fișier() {
        val file = File("output.txt")
        if (file.exists()) {
            file.delete()
        }
    }

    fun deleteNoteByAuthor(title: String) {
        val note = notite.find { it.getContent() == title }
        if (note != null) {
            notite.remove(note)
            șterge_din_fișier()
            salvează_în_fișier()

            println("Notita stearsa =)")
        } else {
            println("Nu am gasit notita")
        }
    }
}