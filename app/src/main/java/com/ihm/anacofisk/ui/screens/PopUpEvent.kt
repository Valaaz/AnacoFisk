package com.ihm.anacofisk.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ihm.anacofisk.R
import com.ihm.anacofisk.model.Game
import com.ihm.anacofisk.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopUpEvent(
    onDismissRequest: () -> Unit,
    onConfirmation: (Boolean) -> Unit,
    gameVM: GameViewModel
) {

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            shape = RoundedCornerShape(15.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Evenement 1/10",
                        )
                    },
                    actions = {
                        IconButton(onClick = onDismissRequest) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = Icons.Default.Close.name
                            )
                        }
                    }
                )
                Text("Le lave linge vient de casser !")

                Image(
                    painter = painterResource(id = R.drawable.lave_linge),
                    contentDescription = "Lave linge",
                    modifier = Modifier.size(100.dp)
                )
                Button(onClick = { onConfirmation(true) }) {
                    Text(
                        "Le remplacer \n" +
                                "-1000 €", textAlign = TextAlign.Center
                    )
                }
                Button(onClick = { onConfirmation(false) }) {
                    Text(
                        "Vivre sans lave linge \n" +
                                "-- Plaisir", textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PopUpEventPreview() {
    PopUpEvent(onDismissRequest = {}, onConfirmation = {}, gameVM = GameViewModel())
}