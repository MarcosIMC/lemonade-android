package com.example.lemonade

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lemonade(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
                }
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var lemonadeRemaining = -1
    var taps by remember { mutableStateOf(1) }
    val imageResource = when(taps) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> {R.drawable.lemon_tree}
    }

    val descriptionResource = when(taps) {
        1 -> R.string.lemon_tree_description
        2 -> R.string.lemon_description
        3 -> R.string.glass_description
        4 -> R.string.empty_glass_description
        else -> {R.string.lemon_tree_description}
    }

    val textResource = when(taps) {
        1 -> R.string.lemon_tree_action
        2 -> R.string.lemon_action
        3 -> R.string.lemonade_action
        4 -> R.string.glass_action
        else -> {R.string.lemon_tree_action}
    }

    Column(modifier = Modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            if (lemonadeRemaining > 0) {
                lemonadeRemaining--
            } else if (lemonadeRemaining == 0) {
                taps++
            } else if (taps == 2) {
                lemonadeRemaining = (2..4).random()
                lemonadeRemaining--
            } else if (taps == 4) {
                taps = 1
            } else {
                taps++
            }
        }) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(descriptionResource)
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(textResource),
            fontSize = 18.sp,

        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        Lemonade()
    }
}