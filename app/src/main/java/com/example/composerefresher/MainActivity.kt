package com.example.composerefresher

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composerefresher.ui.theme.ComposeRefresherTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val TAG = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is what will actually show on the emulator!
        setContent {
            ComposeRefresherTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {


    // we need an internal state variable!
    // When state changes, UI changes
    // We need to remember the state
//    var moneyCounter by remember {
//        mutableStateOf(0)
//    }

    // Another way to rememebr:
    // This state is hoisted now! more global
    val moneyCounter = remember {
        mutableStateOf(0)
    }

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = Color(0xFF546E7A)
    ) {
        // Create a circle in middle
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${moneyCounter.value}", style = TextStyle(
                    color = Color.White,
                    fontSize = 39.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
            CreateCircle(moneyCounter = moneyCounter.value) { updatedValue ->
                moneyCounter.value = updatedValue
            }
        }
    }
}

@Composable
// We need to create a callback to use our moneyCounter
fun CreateCircle(moneyCounter: Int = 0, updateMoneyCounter: (Int) -> Unit) {

    Card(
        Modifier
            .padding(3.dp)
            .size(105.dp)
            .clickable {

                // Now we wanna increase the value using the callback!
                updateMoneyCounter(moneyCounter + 1)
            },
        CircleShape,
        elevation = 5.dp
    ) {
        // To center the text, put in a box
        Box(contentAlignment = Alignment.Center) {
            // We dont see the moneycounter change
            // in order for us to see changes in Composable,
            // We have to redraw the text.
            // Since this is declarative, we are passing data through
            // And the composable function reacts to that data
            // we need an internal state variable!
            Text(text = "Tap!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeRefresherTheme() {
        // If we just put the showage
        // below greeting, they will overlap!
        // We must use a "Column"
        MyApp()
    }
}