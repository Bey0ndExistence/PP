open class LibraryPrinter
{
    fun printBooksRaw(carti: Set<Book>)
    {
        carti.forEach()
        {
            println("Autor: " + it.getAuthor())
            println("Text: " + it.getText())
            println("Name: " + it.getName())
            println("Publisher: " + it.getPublisher())
            println("Price: " + it.getPrice())
            println()
        }
    }
    fun printHTML(books: Set<Book>) {
        println("<html>")
        println("\t<head><title>Books</title></head>")
        println("\t<body>")
        println("\t\t<h1>Books</h1>")
        println("\t\t<ul>")
        books.forEach {
            println("\t\t\t<li>${it.getName()} by ${it.getAuthor()}, published by ${it.getPublisher()} worth ${it.getPrice()}</li>")
        }
        println("\t\t</ul>")
        println("\t</body>")
        println("</html>")
    }

    fun printJSON(books: Set<Book>) {
        val jsonBooks = mutableListOf<String>()
        books.forEach {
            jsonBooks.add(
                """
                {
                    "name": "${it.getName()}",
                    "author": "${it.getAuthor()}",
                    "publisher": "${it.getPublisher()}",
                    "price": ${it.getPrice()}
                }
                """.trimIndent()
            )
        }
        println("[${jsonBooks.joinToString(",")}]")
    }
}