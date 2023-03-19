fun main() {
    val content1 = Content("Marin", "bruhbruh", "Carte1", "Gazeta Sporturilor")
    val content2 = Content("Sorin", "dsad", "Carte2", "Adevărul")


    val book1 = Book(content1,69.99)
    val book2 = Book(content2, 96.99)

    val librarie = Library(mutableSetOf(book1,book2))
    librarie.addBook(book1)
    librarie.addBook(book2)

    val booksByAuthor = librarie.findAllByAuthor("Marin")
    println("Cartile lui Marin")
    booksByAuthor.forEach { println(it.getName()) }
    println()

    val booksByName = librarie.findAllByName("Carte1")
    println("Carte1 are autorul:")
    booksByName.forEach { println(it.getAuthor()) }
    println()

    val booksByPublisher = librarie.findAllByPublisher("Gazeta Sporturilor")
    println("Carți de gazeta sporturilor")
    booksByPublisher.forEach { println(it.getName()) }
    println()

    val libraryprinter = LibraryPrinter()

    println("Carti de Marin format RAW:\n")
    libraryprinter.printBooksRaw(booksByAuthor)
    println()

    println("Carti de Marin format HTML:")
    libraryprinter.printHTML(booksByAuthor)
    println()

    println("Carti de Marin format JSON:")
    libraryprinter.printJSON(booksByAuthor)
    println()
}