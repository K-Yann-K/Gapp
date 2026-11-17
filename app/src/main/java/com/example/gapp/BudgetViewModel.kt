package com.example.gapp

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class BudgetViewModel : ViewModel() {

    private val _virements = virementsDeDepart.toMutableStateList()
    val virements: List<Virement> get() = _virements

    fun ajouter(virement: Virement) {
        _virements.add(virement)
    }
}