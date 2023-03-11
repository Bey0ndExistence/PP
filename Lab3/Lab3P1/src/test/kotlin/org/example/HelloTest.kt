class Birth(val year: Int, val Month: Int, val Day: Int){
    override fun toString() : String{
        return "($Day.$Month.$year)"
    }
}
class Contact(val Name: String, var Phone: String, val BirthDate: Birth){
    fun Print() {
        println("Name: $Name, Mobile: $Phone, Date: $BirthDate")
    }
    fun update(name: String, newPhone: String){
        if(this.Name== name){
            this.Phone= newPhone;
        }
    }
}

class Sort(val criteriu: String){
    fun search(agenda: List<Contact>, searchTerm: String): List <Contact>{
        return when(criteriu.toLowerCase()){
            "nume" -> agenda.filter{it.Name.toLowerCase().contains(searchTerm.toLowerCase())}
            "telefon" -> agenda.filter{it.Phone.contains((searchTerm))}
            else -> emptyList();
        }
    }
}




fun main(args : Array<String>){
    val agenda = mutableListOf<Contact>()
    agenda.add(Contact("Mihai", "0744321987", Birth(1900, 11, 25)))
    agenda += Contact("George", "0761332100", Birth(2002, 3, 14))
    agenda += Contact("Liviu" , "0231450211", Birth(1999, 7, 30))
    agenda += Contact("Popescu", "0211342787", Birth(1955, 5, 12))
    for (persoana in agenda){
        persoana.Print()
    }
    println("Agenda dupa eliminare contact [George]:")
    agenda.removeAt(1)
    for (persoana in agenda){
        persoana.Print()
    }
    agenda.remove(Contact("Liviu" , "0231450211", Birth(1999, 7, 30)))
    println("Agenda dupa eliminare contact [Liviu]:")
    agenda.removeAt(1)
    for (persoana in agenda){
        persoana.Print()
    }

    val sortByName = Sort("nume")
    val contactsByName = sortByName.search(agenda, "Mihai")
    println("Contacte cu numele Mihai:")
    contactsByName.forEach { it.Print() }

    val sortByPhone = Sort("telefon")
    val contactsByPhone = sortByPhone.search(agenda, "021")
    println("Contacte cu numarul de telefon incepand cu 021:")
    contactsByPhone.forEach { it.Print() }

    for (persoana in agenda)
    {
        persoana.update("Mihai","0777777777");
    }

    for (persoana in agenda)
    {
        persoana.Print()
    }

}