package com.example.gapp

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class BudgetViewModel : ViewModel() {

    val loyer = 1050
    val participants = listOf("Thomas", "Aliya")

    private val _virements = virementsDeDepart.toMutableStateList()
    val virements: List<Virement> get() = _virements

    val part: Int get() = calculerPart(loyer, participants.size)

    val messages: List<String> get() = participants.map { prenom -> messageVersement(prenom, totalPour(prenom, virements), part) } // TODO : participants.map { ... }

    fun ajouter(virement: Virement) { _virements.add(virement) }
    fun supprimer(id: String) { _virements.removeAll { it.id == id } }
}