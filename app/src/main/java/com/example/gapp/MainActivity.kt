package com.example.gapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                AppBudget(Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun AppBudget(modifier: Modifier = Modifier) {
    val virements = remember { virementsDeDepart.toMutableStateList() }

    EcranBudget(
        virements = virements,
        onAjouter = { virements.add(Virement(50, "Aliya", "complément")) } ,
        modifier = modifier,
    )
}

@Composable
fun EcranBudget(virements: List<Virement>, onAjouter: () -> Unit, modifier: Modifier = Modifier) {
    val loyer = 1050
    val part = calculerPart(loyer, 2)
    val totalThomas = totalPour("Thomas", virements)
    val totalAliya = totalPour("Aliya", virements)

    LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
        item { Text("Loyer - $loyer €", fontSize = 24.sp) }
        item { Text(messageVersement("Thomas", totalThomas, part)) }
        item { Text(messageVersement("Aliya", totalAliya, part)) }
        item { HorizontalDivider(thickness = 1.dp) }
        items(virements){
            v -> LigneVirement(v)
        }
        item { Button(onClick = onAjouter) { Text("Aliya ajoute 50 €") } }
    }

}

@Composable
fun LigneVirement(virement: Virement) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(virement.auteur, fontSize = 20.sp)
            Text(virement.libelle, fontSize = 14.sp)

        }
        Text("${virement.montant} €")
    }

}


@Preview(showBackground = true)
@Composable
fun EcranBudgetPreview() {
    EcranBudget(virements = virementsDeDepart, onAjouter = {})
}