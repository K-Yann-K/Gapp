package com.example.gapp

import java.util.UUID
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Virement(
    val montant: Int,
    val auteur: String,
    val libelle: String,
    val id: String = UUID.randomUUID().toString(),
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

val virementsDeDepart = listOf(
    Virement(200, "Thomas", "loyer"),
    Virement(500, "Aliya", "loyer octobre"),
    Virement(150, "Thomas", "loyer part 2"),
    Virement(100, "Thomas", "loyer fin"),
)
fun main() {
    val vJSON = Json.encodeToString(virementsDeDepart)
    val vListe : List<Virement> = Json.decodeFromString(vJSON)
    println(vListe)

}