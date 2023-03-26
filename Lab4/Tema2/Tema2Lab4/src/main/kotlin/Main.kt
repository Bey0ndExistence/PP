import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun main() {
    val ticketStorage = InMemoryTicketStorage()

    print("1-Cash sau 2-Card ? : ")
    val cashMethod= CashPayment(200.00)
    val cardMethod=CardPayment(BankAccount(122.00,"cardNumber", Date(1234567890000L),322,"marian"))
    val payment = readLine()?.trim() ?:"".toInt()
    var ticketManager: CinemaTicketManager? =null
    if(payment==1){
         ticketManager=CinemaTicketManager(ticketStorage,cashMethod)
    }
    else{
         ticketManager=CinemaTicketManager(ticketStorage,cardMethod)
    }


    while (true) {
        println("Introduceti actiunea:")
        println("1. Afisare bilete cumparate")
        println("2. Incarcare bilet specific")
        println("3. Cumparare bilet")
        println("4. Stergere bilet")
        println("5. Iesire din program")

        when (readLine()?.toInt()) {
            1 -> ticketManager.showAllTickets()
            2 -> {
                print("Introduceti titlul filmului: ")
                val movieTitle = readLine()?.trim() ?: ""

                print("Introduceti numarul salii: ")
                val hallName = readLine()?.trim() ?: ""
                print("Introduceti numarul locului: ")
                val seatNumber = readLine()?.toInt() ?: 0
                print("Introduceti data si ora in format dd.MM.yyyy HH:mm: ")
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                val showtime = LocalDateTime.parse(readLine()?.trim() ?: "", formatter)
                ticketManager.loadTicket(movieTitle, hallName, seatNumber, showtime)
            }

            3 -> {
                print("Introduceti titlul filmului: ")
                val movieTitle = readLine()?.trim() ?: ""
                print("Introduceti numarul salii: ")
                val hallName = readLine()?.trim() ?: ""
                print("Introduceti numarul locului: ")
                val seatNumber = readLine()?.toInt() ?: 0
                print("Introduceti data si ora in format dd.MM.yyyy HH:mm: ")
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                val showtime = LocalDateTime.parse(readLine()?.trim() ?: "", formatter)
                ticketManager.bookTicket(movieTitle, hallName, seatNumber, showtime)
            }

            4 -> {
                print("Introduceti titlul filmului: ")
                val movieTitle = readLine()?.trim() ?: ""
                print("Introduceti numarul salii: ")
                val hallName = readLine()?.trim() ?: ""
                print("Introduceti numarul locului: ")
                val seatNumber = readLine()?.toInt() ?: 0
                print("Introduceti data si ora in format dd.MM.yyyy HH:mm: ")
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                val showtime = LocalDateTime.parse(readLine()?.trim() ?: "", formatter)
                ticketManager.deleteTicketReservation(movieTitle, hallName, seatNumber, showtime)
            }

            5 -> return
            else -> println("Nu-i buna comanda asta")
        }
    }
}