import java.time.LocalDateTime

class CinemaTicketManager(private val ticketStorage: TicketStorage, private val paymentMethod: PaymentMethod) {
    fun bookTicket(movieTitle: String, cinemaHallName: String, seatNumber: Int, showtime: LocalDateTime): Ticket {
        val ticket = Ticket(movieTitle, cinemaHallName,12.99, seatNumber, showtime)
        paymentMethod.pay(ticket.pret)
        ticketStorage.save(ticket)
        return ticket

    }

    fun showAllTickets() {
        val tickets = ticketStorage.getAll()
        if (tickets.isEmpty()) {
            println("Nu exista bilete cumparate.")
        } else {
            println("Bilete cumparate:")
            for (ticket in tickets) {
                println(ticket.toString())
            }
        }
    }

    fun loadTicket(movieTitle: String, cinemaHallName: String, seatNumber: Int, showtime: LocalDateTime): Ticket? {
        val tickets = ticketStorage.getAll()
        return tickets.find { it.movieTitle == movieTitle && it.cinemaHallName == cinemaHallName && it.seatNumber == seatNumber && it.showtime == showtime }
    }

    fun deleteTicketReservation(movieTitle: String, cinemaHallName: String, seatNumber: Int, showtime: LocalDateTime): Boolean {
        val ticket = loadTicket(movieTitle, cinemaHallName, seatNumber, showtime)
        return if (ticket != null) {
            ticketStorage.remove(ticket)
            true
        } else {
            false
        }
    }
}