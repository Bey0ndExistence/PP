class InMemoryTicketStorage: TicketStorage {
    private val tickets = mutableListOf<Ticket>()

    override fun save(ticket: Ticket) {
        tickets.add(ticket)
    }

    override fun remove(ticket: Ticket) {
        tickets.remove(ticket)
    }

    override fun getAll(): List<Ticket> {
        return tickets.toList()
    }
}