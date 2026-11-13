package com.example.gapp

import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import java.lang.reflect.Modifier
import java.nio.file.WatchEvent

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
                innerPadding -> EcranBudget(virements, WatchEvent.Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun EcranBudget(virements: List<Virement>, modifier: Modifier = Modifier) {

}

@Composable
fun LigneVirement(virement: Virement) {
    Row{
        
    }
}

@Preview(showBackground = true)
@Composable
fun EcranBudgetPreview() {
    // TODO : appelle EcranBudget avec une petite liste de test
}