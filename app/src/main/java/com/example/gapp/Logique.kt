package com.example.gapp

data class Virement(
    val montant : Int,
    val auteur: String,
    val libelle: String
)

fun calculerPart(total: Int, nbPersonnes: Int): Int{
    return total / nbPersonnes
}

fun afficherPart(prenom: String, part: Int){
    println("$prenom doit verser $part € pour le loyer")
}


fun messageVersement(prenom: String, somme: Int, attendu: Int): String{
    return if (somme == attendu){
        "$prenom a payé pile sa part"
    }
    else if (somme > attendu){
        "$prenom a versé ${ somme - attendu } € en trop"
    }
    else {
        "Il manque ${ attendu - somme } € à $prenom"
    }
}

fun totalPour(personne: String, virements: List<Virement>): Int{
    return virements.filter { it.auteur == personne }.sumOf { it.montant }
}

fun main() {

    val prenom = "Thomas"
    val montant = 1050

    val v1 = Virement(200, prenom, "loyer")
    val v2 = Virement(500, "Aliya", "loyer octobre")
    val v3 = Virement(150, prenom, "loyer part 2")
    val v4 = Virement(100, prenom, "loyer fin")
    val virements = listOf(v1, v2, v3, v4)

    val partLoyer = calculerPart(montant, 2)
    afficherPart(prenom, partLoyer)


    val virementsAliya = virements.filter { it.auteur == "Aliya" }
    println(virementsAliya.size)
    val grosVirement = virements.filter { it.montant > 120 }
    for (v in grosVirement){
        println(v)
    }
    val totalThomas = totalPour(prenom, virements)
    val totalAliya = totalPour("Aliya", virements)
    println(messageVersement(prenom, totalThomas, partLoyer))
    println(messageVersement("Aliya", totalAliya, partLoyer))
}