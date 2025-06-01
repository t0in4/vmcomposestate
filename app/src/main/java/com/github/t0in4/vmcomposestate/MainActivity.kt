package com.github.t0in4.vmcomposestate

import android.R.attr.label
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.t0in4.vmcomposestate.ui.theme.VMComposeStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VMComposeStateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TopLevel(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun TopLevel(viewModel: MyViewModel = viewModel(), modifier: Modifier = Modifier) {
    MainScreen(
        name = viewModel.customerName,
        onValueChange = { viewModel.setName(it) }
    )
}
@Composable
fun MainScreen(modifier: Modifier = Modifier, name: String, onValueChange: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = onValueChange,
            label = { Text("Name") }
        )
        Spacer(Modifier.height(20.dp))
        DrawText()
    }
}
@Composable
fun DrawText(model: MyViewModel = viewModel()) {
    val colorList: List<Color> = listOf(Color.Black, Color.Blue, Color.Yellow, Color.Red,
        Color.Green, Color.Magenta)
    val textMeasurer = rememberTextMeasurer()
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 60.sp,
                fontWeight = FontWeight.ExtraBold,
                brush = Brush.horizontalGradient(colorList)
            )
        ) {
            append(model.customerName)
        }
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawText(textMeasurer, annotatedText)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VMComposeStateTheme {
    }
}