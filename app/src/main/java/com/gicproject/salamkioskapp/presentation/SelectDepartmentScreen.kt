package com.gicproject.salamkioskapp.presentation

import android.util.Log
import android.view.LayoutInflater
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.gicproject.salamkioskapp.R
import com.gicproject.salamkioskapp.Screen
import com.gicproject.salamkioskapp.common.Constants
import com.gicproject.salamkioskapp.common.Constants.Companion.heartBeatJson
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import kotlinx.coroutines.delay


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SelectDepartmentScreen(
    navController: NavController,
    viewModel: MyViewModel,
) {

    val state = viewModel.stateSelectDepartment.value

    LaunchedEffect(true) {
        while (true) {
            Log.d("TAG", "SelectDepartmentScreen: called GetSelectDepartments" )
            viewModel.onEvent(MyEvent.GetSelectDepartments)
            delay(4000)
        }
    }
    var showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        val second = remember { mutableStateOf(30) }
        LaunchedEffect(key1 = Unit, block = {
            while (true) {
                delay(1000)
                second.value = second.value - 1
                if (second.value == 0) {
                    showDialog.value = false
                }
            }
        })
        Dialog(
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = false
            ),
            onDismissRequest = {
                showDialog.value = false
            },

            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(top = 80.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CustomButton(onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            Constants.STATE_EXTRA, false
                        )
                        navController.navigate(Screen.InsertCivilIdScreen.route)
                    }, text = "Appointment","????????")
                    CustomButton(onClick = {
                        navController.navigate(Screen.SelectDoctorTimeScreen.route)
                    }, text = "Without Appointment","???????? ????????")
                }

                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    Button(
                        onClick = { showDialog.value = false },
                        modifier = Modifier
                            .padding(20.dp)
                            .shadow(50.dp, shape = RoundedCornerShape(5.dp)),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft,
                            contentDescription = "",
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        val fontEnglish = FontFamily(Font(R.font.questrial_regular))
                        val fontArabic = FontFamily(Font(R.font.ge_dinar_one_medium))
                        Row(){
                            Text("Back  ", fontSize = 25.sp, fontFamily = fontEnglish)
                            Text("????????", fontSize = 25.sp, fontFamily = fontArabic)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                    HeartBeatTimeRow(second = second)
                }
            }
        }
    }


    Scaffold { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colors.surface,
                )
        ) {
            Modifier.padding(innerPadding)
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = Constants.BACKGROUND_IMAGE),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "bg",
                    modifier = Modifier.fillMaxSize()
                )
            }
            /* Column(
                 modifier = Modifier.fillMaxSize(),
                 horizontalAlignment = Alignment.Start,
                 verticalArrangement = Arrangement.Bottom
             ) {
                 Row(
                     horizontalArrangement = Arrangement.Center,
                     verticalAlignment = Alignment.CenterVertically,
                     modifier = Modifier.padding(20.dp)
                 ) {
                    GoBack(navController)
                 }
             }*/
            // HeartBeatTime(second = second)

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HeaderDesign("Select Department","?????? ??????????", navController)
              /*  AndroidView(
                    factory = { context ->
                        val view = LayoutInflater.from(context).inflate(R.layout.myfatoorah_layout, null, false)
                        val paymentView = view.findViewById<MFPaymentCardView>(R.id.mfPaymentView)
                        viewModel.setPaymentView(paymentView)
                        viewModel.getPaymentView()?.startNfcReadCard()

                        // do whatever you want...
                        view // return the view
                    },
                    update = { view ->
                        // Update the view
                    }
                )*/
                FlowColumn(
                    Modifier.fillMaxSize(),
                    crossAxisAlignment = FlowCrossAxisAlignment.Center,
                    mainAxisAlignment = FlowMainAxisAlignment.Center,
                ) {
                    LazyVerticalGrid(
                        verticalArrangement = Arrangement.Center,
                        horizontalArrangement = Arrangement.Center,
                        state = rememberLazyGridState(),
                        contentPadding = PaddingValues(70.dp),
                        modifier = Modifier
                            .width(730.dp)
                            .height(950.dp),
                        columns = GridCells.Fixed(2),
                    ) {
                        items(state.departments.size) { index ->
                            CustomButton(onClick = {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    Constants.STATE_SELECT_DEPARTMENT, state.departments[index]
                                )
                               navController.navigate(Screen.SelectServiceScreen.route)
                               // showDialog.value = true
                            }, text = state.departments[index].DepartmentNameEN
                                ?: "" , textAr =
                            state.departments[index].DepartmentNameAR ?: "",
                            )
                        }
                    }
                }
            }
            /*
             if (state.isLoading) {
                 CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
             }
             if (state.success.isNotBlank()) {
                 LaunchedEffect(key1 = true) {

                 }
             }*/

        }
        if (state.error.isNotBlank()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom,
            ) {
                Text(state.error, color = MaterialTheme.colors.error, fontSize = 24.sp)
            }
        }


        if (state.isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator()
            }
        }

    }
}

@Composable
fun HeartBeatAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(heartBeatJson))
    LottieAnimation(
        composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(80.dp),
        isPlaying = true,

        )
}


@Composable
fun GoBack(navController: NavController) {
    Button(
        onClick = { navController.popBackStack() },
        modifier = Modifier
            .padding(20.dp)
            .shadow(50.dp, shape = RoundedCornerShape(5.dp)),
        shape = RoundedCornerShape(30.dp)
    ) {
        Icon(
            Icons.Default.KeyboardArrowLeft,
            contentDescription = "",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        val fontEnglish = FontFamily(Font(R.font.questrial_regular))
        val fontArabic = FontFamily(Font(R.font.ge_dinar_one_medium))
        Row(){
            Text("Back  ", fontSize = 25.sp, fontFamily = fontEnglish)
            Text("????????", fontSize = 25.sp, fontFamily = fontArabic)
        }
        Spacer(modifier = Modifier.width(10.dp))
    }
}

@Composable
fun HeartBeatTime(second: MutableState<Int>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HeartBeatAnimation()
            Spacer(modifier = Modifier.width(20.dp))
            Text(second.value.toString(), fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}


@Composable
fun HeartBeatTimeRow(second: MutableState<Int>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom,
    ) {
        HeartBeatAnimation()
        Spacer(modifier = Modifier.width(20.dp))
        Text(second.value.toString(), fontSize = 40.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(20.dp))
    }
}

