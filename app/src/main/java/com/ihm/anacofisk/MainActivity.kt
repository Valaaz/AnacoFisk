package com.ihm.anacofisk

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ihm.anacofisk.model.Game
import com.ihm.anacofisk.ui.navigation.NavigationComponent
import com.ihm.anacofisk.ui.navigation.Screens
import com.ihm.anacofisk.ui.screens.PopUpEvent
import com.ihm.anacofisk.ui.theme.AnacoFiskTheme
import com.ihm.anacofisk.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnacoFiskTheme {
                val navController = rememberNavController()
                val game = Game(economy = 0, pleasure = 0)
                val gameVM = GameViewModel()
                NavigationComponent(navController = navController, gameVM = gameVM)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController, gameVM: GameViewModel) {
    var openPopUpEvent by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.background(Color(0xFF96BF97)).padding(end = 20.dp),
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF96BF97)),
                title = { Text("AnacoFisk") },
                actions = {
                    BadgedBox(
                        badge = {
                            Badge(Modifier.size(10.dp))
                        }
                    ) {
                        FloatingActionButton(
                            onClick = { openPopUpEvent = true }, shape = CircleShape, modifier = Modifier.size(45.dp), containerColor = Color(0xFFD9D9D9)) {
                            Icon(
                                imageVector = Icons.Default.PriorityHigh,
                                contentDescription = Icons.Default.PriorityHigh.name
                            )
                        }

                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    FloatingActionButton(onClick = {}, shape = CircleShape, containerColor = Color(0xFFD9D9D9)) { Text("Passer au mois suivant", modifier = Modifier.padding(5.dp)) }
                    Spacer(modifier = Modifier.width(16.dp))
                    FloatingActionButton(shape = CircleShape, containerColor = Color(0xFFD9D9D9), onClick = { navController.navigate(Screens.AdvisorScreen.name) }) {
                        Icon(
                            Icons.Default.SupportAgent,
                            Icons.Default.SupportAgent.name
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("05/02/2025", Modifier.padding(bottom = 10.dp))
            Text("Epargne : ${gameVM.game.economy}")
            LinearProgressIndicator(
                progress = { gameVM.game.economy / 100f },
                modifier = Modifier
                    .fillMaxWidth(0.8f).height(25.dp)
                    .padding(8.dp),
                color = Color(0xFF96BF97),
            )
            Text("Plaisir : ${gameVM.game.pleasure}", Modifier.padding(top = 10.dp))
            LinearProgressIndicator(
                progress = { gameVM.game.pleasure / 100f },
                modifier = Modifier
                    .fillMaxWidth(0.8f).height(25.dp)
                    .padding(8.dp),
                color = Color(0xFFFCEF61),
            )

            Image(
                painter = painterResource(id = R.drawable.epargne),
                contentDescription = "Epargne",
                modifier = Modifier.width(350.dp).padding(top = 50.dp)
            )
            Text("Epargne")

            Image(
                painter = painterResource(id = R.drawable.plaisir),
                contentDescription = "Plaisir",
                modifier = Modifier.width(350.dp).padding(top = 50.dp)
            )
            Text("Plaisir")

        }
    }

    if (openPopUpEvent) {
        PopUpEvent(
            onDismissRequest = { openPopUpEvent = false },
            gameVM = gameVM,
            onConfirmation = { response ->
                if (response) {
                    gameVM.increaseEconomy(10)
                    gameVM.decreasePleasure(10)
                } else {
                    gameVM.increasePleasure(10)
                    gameVM.decreaseEconomy(10)
                }

                openPopUpEvent = false
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnacoFiskTheme {
        Home(rememberNavController(), GameViewModel())
    }
}