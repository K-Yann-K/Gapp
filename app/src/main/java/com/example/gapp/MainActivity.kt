package com.example.gapp

class MainActivity {

    data class Person(val name: String, val age: Int)
    data class Enveloppe(val name: String, val montant: Long)
    data class  Transaction(val montant: Long, val libelle: String, val date: String, val auteur: Person )

    fun CalculByMonth(charges : Enveloppe, numberOfParticipant : Int, listCharge : MutableList<Enveloppe>): Long {
        for toPaid in listCharge :
        return charges.montant / numberOfParticipant

    }

     fun main() {
        val thomas = Person("Thomas", 29)
        val alice = Person("Alice", 29)

        val loyer = Enveloppe("budhet appart", 105000)
        val nourriture = Enveloppe("budget course", 55000)

        val virement1 = Transaction(30000, "food", "02/10/2026", "Alice")
        val virement2 = Transaction(25000, "course du mois d'octobre", "02/10/2026", "Thomas")
        val virement3 = Transaction(80000, "loyer", "01/10/2026", "Alice")
        val virement4 = Transaction(25000, "loyer 10/2026", "05/10/2026", "Thomas")
    }
}