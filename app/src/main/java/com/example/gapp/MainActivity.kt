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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val v1 = Virement(200, "Thomas", "loyer")
        val v2 = Virement(500, "Aliya", "loyer octobre")
        val v3 = Virement(150, "Thomas", "loyer part 2")
        val v4 = Virement(100, "Thomas", "loyer fin")
        val virements = listOf(v1, v2, v3, v4)

        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) {
                innerPadding -> EcranBudget(virements, Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun EcranBudget(virements: List<Virement>, modifier: Modifier = Modifier) {

}

@Composable
fun LigneVirement(virement: Virement) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ){
            Column(modifier = Modifier.weight(1f)){
                Text(virement.auteur, fontSize = 20.sp)
                Text(virement.libelle, fontSize = 14.sp)

            }
            Text("${virement.montant} €")
        }

    }


@Preview(showBackground = true)
@Composable
fun LigneVirementPreview() {
    LigneVirement(Virement(200, "Thomas", "loyer"))
}