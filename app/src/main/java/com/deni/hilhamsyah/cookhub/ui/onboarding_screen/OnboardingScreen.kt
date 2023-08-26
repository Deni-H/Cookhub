package com.deni.hilhamsyah.cookhub.ui.onboarding_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.deni.hilhamsyah.cookhub.R
import com.deni.hilhamsyah.cookhub.navigation.Screen
import com.deni.hilhamsyah.cookhub.ui.theme.CookhubTheme

@Composable
fun OnboardingScreen(
    navController: NavController,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentScale = ContentScale.Crop,
            contentDescription = "image_background"
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "ic_star",
                        tint = Color.White
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 6.dp, end = 6.dp)
                            .height(23.dp),
                        text = "60k+",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                    )
                    Text(
                        modifier = Modifier.height(23.dp),
                        text = "Premium recipes",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Let’s\nCooking",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    fontWeight = FontWeight(500),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = "Find best recipes for cooking",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
                Button(
                    modifier = Modifier.padding(bottom = 8.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCB3838)),
                    onClick = {
                        navController.navigate(Screen.LoginScreen.route)
                    },
                ) {
                    Row (
                        modifier = Modifier.width(200.dp),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Login",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
                Button(
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8C2262)),
                    onClick = {
                        navController.navigate(Screen.RegisterScreen.route)
                    },
                ) {
                    Row (
                        modifier = Modifier.width(200.dp),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Register",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    CookhubTheme {
        OnboardingScreen(rememberNavController())
    }
}