interface TicketStorage {
    fun save(ticket: Ticket)
    fun remove(ticket: Ticket)
    fun getAll(): List<Ticket>
}