package uz.muhammadyusuf.kurbonov.fordealer.homescreen

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AppBar() },
    ) {

    }
}

@Preview()
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}