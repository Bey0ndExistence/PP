import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Ticket(val movieTitle: String, val cinemaHallName: String, val pret : Double , val seatNumber: Int, val showtime: LocalDateTime){
    override fun toString(): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        return "$movieTitle la $cinemaHallName, locul $seatNumber, ora ${showtime.format(formatter)} , cu pretul $pret"
    }

}