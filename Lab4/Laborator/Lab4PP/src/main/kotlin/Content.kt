open class Content (private var author: String,private var text: String,private var name: String,private var publisher: String)
{
    open fun getAuthor():String
    {
        return this.author
    }
    open fun setAuthor(autor: String)
    {
        if(autor.isNotEmpty())
            this.author=autor
        else
            println("Autor invalid!\n")
    }
    open fun getText(): String
    {
        return this.text
    }
    open fun setText(scris: String)
    {
        if(scris.isNotEmpty())
            this.text=scris
        else
            println("Text invalid!\n")
    }

    open fun getName(): String
    {
        return this.name
    }
    open fun setName(nume: String)
    {
        if(nume.isNotEmpty())
            this.name=nume
        else
            println("Nume invalid!\n")
    }

    open fun getPublisher(): String
    {
        return this.publisher
    }

    open fun setPublisher(publicatie: String)
    {
        if(publicatie.isNotEmpty())
            this.publisher=publicatie
        else
            println("Publicatie invalida!\n")
    }
}