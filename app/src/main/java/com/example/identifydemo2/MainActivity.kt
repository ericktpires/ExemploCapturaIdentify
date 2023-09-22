package com.example.identifydemo2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.identifydemo2.ui.theme.IdentifyDemo2Theme
import br.com.stoneage.identify.sdk.*
import br.com.stoneage.identify.enums.*

class MainActivity : ComponentActivity() {
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        STAUserSession.initialize(
            StageEnum.HMG,
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiODBmMWM0OS1lNWQyLTRlZDUtYjYwZS0zOThjOWE1ZGM2MTYiLCJzdWIiOiIzNDYyZGU3MS1mODc0LTQzMmUtYjZkMi1lOTM0NTk0MWMyOTQiLCJpYXQiOiIyMDIzLTA5LTEyVDE1OjQwOjU4LjkwOTA3NzQrMDA6MDAiLCJleHAiOjE2OTQ2MTk2NTgsImNsaWVudF9pZCI6ImRiZGQ1NWU0LTU4MjgtNGZiNy04MzRlLTdmNzAwYmY4MDM1ZiIsImlzcyI6ImlkZW50aWZ5LWhtZy5zdG9uZWFnZS5jb20uYnIifQ.FdFrsIpw85gzkMSq5vTWayY0BVSe1cJxkwSXYAikgNY",
            STATheme(
                STAColor(255, 0, 0, 255),
                STAColor(255, 0, 255, 0),
                STAColor(255, 200, 200, 200)
            ),
            this
        )
        setContent {
            IdentifyDemo2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OpenCamera("Teste", {this.openCameraActivityForResult()})
                }
            }
        }
    }

    fun openCameraActivityForResult() {
        val intent = Intent(this, DocumentFrontActivity::class.java)
        resultLauncher.launch(intent)
    }



}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun OpenCamera(name: String, onCLick: () -> Unit, modifier: Modifier = Modifier) {
    TextButton(
        onClick = onCLick,
        modifier = modifier
    ) {
        Text(text = name)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewActivity () {
    IdentifyDemo2Theme {
        OpenCamera(name = "Teste", onCLick = { /*TODO*/ })
    }
}