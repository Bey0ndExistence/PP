open class Book(private var data: Content, private var price: Double)
{
    fun laString():String
    {
        return data.toString()
    }

    fun getAuthor(): String {
        return data.getAuthor()
    }

    fun setAuthor(author: String)
    {
        data.setAuthor(author)
    }

    fun getName(): String
    {
        return data.getName()
    }

    fun setName(name: String)
    {
        return data.setName(name)
    }

     fun getText(): String
    {
        return data.getText()
    }

    fun setText(text: String)
    {
        data.setText(text)
    }

     fun getPublisher(): String
    {
        return data.getPublisher()
    }

     fun setPublisher(publisher: String)
    {
       data.setPublisher(publisher)
    }

    fun setPrice(pret: Double)
    {
        if(pret>0)
            this.price=pret
        else
            println("Pret invalid!\n")
    }

    fun getPrice():Double
    {
        return this.price
    }
}