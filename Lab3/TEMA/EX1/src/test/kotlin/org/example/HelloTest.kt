import org.jsoup.Jsoup
data class Item(val title: String, val link: String, val description: String, val pubDate: String)

class RSSProcessor {
    fun process(url: String): List<Item> {
        val items = mutableListOf<Item>()
        val doc = Jsoup.connect(url).get()
        val elements = doc.select("item")

        for (element in elements) {
            val item = Item(
                element.select("title").text(),
                element.select("link").text(),
                element.select("description").text(),
                element.select("pubDate").text()
            )
            items.add(item)
        }

        return items
    }
}

fun main() {
    val processor = RSSProcessor()
    val url = "http://rss.cnn.com/rss/edition.rss"


        val items = processor.process(url)

        for (item in items) {
            println("Title: ${item.title}")
            println("Link: ${item.link}")
            println("PubDate: ${item.pubDate}")
            println()
        }
}