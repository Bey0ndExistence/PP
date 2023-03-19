
import java.io.File
import java.time.LocalDateTime

class Note {
    private var author: User? = null
    private var content: String = ""
    private var time: LocalDateTime? = null

    fun setAuthor(author: User) {
        this.author = author
    }

    fun getAuthor(): User? {
        return author
    }

    fun setContent(content: String) {
        this.content = content
    }

    fun getContent(): String {
        return content
    }

    fun setDateTime(dateTime: LocalDateTime) {
        this.time = dateTime
    }

    fun getDateTime(): LocalDateTime? {
        return time
    }
}