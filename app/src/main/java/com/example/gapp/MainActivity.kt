package com.example.gapp

import android.annotation.SuppressLint
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material3.TextButton

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
fun AppBudget(modifier: Modifier = Modifier, vm: BudgetViewModel = viewModel()) {

    EcranBudget(
        loyer = vm.loyer,
        messages = vm.messages,
        virements = vm.virements,
        onAjouter = vm::ajouter,
        onSupprimer = vm::supprimer,
        modifier = modifier,
    )
}

@Composable
fun EcranBudget(
    loyer: Int,
    messages: List<String>,
    virements: List<Virement>,
    onAjouter: (Virement) -> Unit,
    onSupprimer: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
        item { Text("Loyer - $loyer €", fontSize = 24.sp) }
        items(messages) {
            mes -> Text(mes)
        }

        item { HorizontalDivider(thickness = 1.dp) }
        items(virements, key = { it.id }) { v ->
            LigneVirement(v, onSupprimer = { onSupprimer(v.id) })
        }
        item { FormulaireVirement(onValider = onAjouter) }
    }
}

@Composable
fun LigneVirement(virement: Virement, onSupprimer: () -> Unit) {
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
        TextButton(onClick = onSupprimer) {
            Text("✕")
        }
    }

}



@Composable
fun FormulaireVirement(onValider: (Virement) -> Unit, modifier: Modifier = Modifier) {
    var auteur by rememberSaveable { mutableStateOf("") }
    var montantTexte by rememberSaveable { mutableStateOf("") }
    var libelle by rememberSaveable { mutableStateOf("") }

    val montant = montantTexte.toIntOrNull()
    val formulaireValide = auteur.isNotBlank() && montant != null &&  ( montant > 0)

        Column(modifier = modifier) {
            OutlinedTextField(
                value = auteur,
                onValueChange = { auteur = it },
                label = { Text("Auteur") },
            )

            OutlinedTextField(
                value = montantTexte,
                onValueChange = { montantTexte = it },
                label = { Text("Montant") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )

            OutlinedTextField(
                value = libelle,
                onValueChange = { libelle= it },
                label = { Text("Libellé") },
            )

            Button(
                onClick = { onValider(Virement(montant ?: 0, auteur, libelle))
                    auteur = ""
                    montantTexte = ""
                    libelle = ""
                },
                enabled = formulaireValide,
            ) {
                Text("Ajouter")
            }
        }
}


@Preview(showBackground = true)
@Composable
fun EcranBudgetPreview() {
    EcranBudget(
        loyer = 1050,
        messages = listOf("Il manque 75 € à Thomas", "Il manque 25 € à Aliya"),
        virements = virementsDeDepart,
        onAjouter = {},
        onSupprimer = {},
    )
}