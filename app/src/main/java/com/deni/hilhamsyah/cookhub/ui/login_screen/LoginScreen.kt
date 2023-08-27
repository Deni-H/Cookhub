package com.deni.hilhamsyah.cookhub.ui.login_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.deni.hilhamsyah.cookhub.R
import com.deni.hilhamsyah.cookhub.ui.components.CustomAppBar
import com.deni.hilhamsyah.cookhub.ui.components.CustomTextField
import com.deni.hilhamsyah.cookhub.ui.theme.CookhubTheme
import com.deni.hilhamsyah.cookhub.util.InputValidator
import com.deni.hilhamsyah.cookhub.util.WindowType
import com.deni.hilhamsyah.cookhub.util.rememberWindowInfo

@Composable
fun LoginScreen(
    navController: NavController
) {
    val windowInfo = rememberWindowInfo()

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var emailErrMsg: String? by rememberSaveable { mutableStateOf(null) }
    var passErrMsg: String? by rememberSaveable { mutableStateOf(null) }
    var passwordVisibility by rememberSaveable { mutableStateOf(true) }

    val icon =
        if (passwordVisibility) ImageVector.vectorResource(R.drawable.ic_eye_open)
        else ImageVector.vectorResource(R.drawable.ic_eye_closed)

    ConstraintLayout(
        modifier = Modifier
            .padding(
                vertical =
                if (windowInfo.height == WindowType.COMPACT) 8.dp
                else 20.dp,
                horizontal = 8.dp
            )
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val (
            topBarRef,
            descriptionRef,
            inputContainerRef,
            forgetPassRef,
            loginBtnRef,
            googleBtnRef,
            dividerRef,
            registerTextRef,
            footerRef
        ) = createRefs()

        CustomAppBar(
            modifier = Modifier.constrainAs(topBarRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            navController = navController,
            title = "Hi, Welcome back!",
        )

        Text(
            modifier = Modifier.constrainAs(descriptionRef) {
                top.linkTo(topBarRef.bottom, 20.dp)
                start.linkTo(parent.start)
            },
            text = "Access your Cookhub account \nand explore delightful recipes!"
        )

        Column(
            modifier = Modifier
                .constrainAs(inputContainerRef) {
                    top.linkTo(
                        anchor = descriptionRef.bottom,
                        margin =
                        if (windowInfo.height == WindowType.COMPACT) 30.dp
                        else 50.dp
                    )
                }
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = {
                    email = it
                    emailErrMsg = InputValidator.validateEmail(it)
                },
                placeholder = "Email",
                errorMessage = emailErrMsg,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                )
            )

            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = {
                    password = it
                    passErrMsg = InputValidator.validatePassword(it)
                },
                placeholder = "Password",
                errorMessage = passErrMsg,
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "ic_eye",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                isPassword = passwordVisibility,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
            )
        }

        ClickableText(
            modifier = Modifier.constrainAs(forgetPassRef) {
                top.linkTo(inputContainerRef.bottom)
                end.linkTo(parent.end)
            },
            text = AnnotatedString("Forgot password?"),
            onClick = {}
        )

        Button(
            modifier = Modifier
                .constrainAs(loginBtnRef) {
                    top.linkTo(
                        anchor = forgetPassRef.bottom,
                        margin =
                        if (windowInfo.height == WindowType.COMPACT) 30.dp
                        else 50.dp
                    )
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Row(
            modifier = Modifier.constrainAs(dividerRef) {
                top.linkTo(
                    anchor = loginBtnRef.bottom,
                    margin =
                        if (windowInfo.height == WindowType.COMPACT) 25.dp
                        else 50.dp
                )
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.outlineVariant,
                thickness = 2.dp
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "Or login with",
                textAlign = TextAlign.Center
            )
            Divider(
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.outlineVariant,
                thickness = 2.dp
            )
        }

        Button(
            modifier = Modifier
                .constrainAs(googleBtnRef) {
                    top.linkTo(
                        anchor = dividerRef.bottom,
                        margin =
                        if (windowInfo.height == WindowType.COMPACT) 25.dp
                        else 50.dp
                    )
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
            )
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_google),
                contentDescription = "null",
                tint = Color.Unspecified
            )
        }
        
        Text(
            modifier = Modifier.constrainAs(registerTextRef) {
                top.linkTo(googleBtnRef.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "Don't have an account? Register now"
        )

        Text(
            modifier = Modifier.constrainAs(footerRef) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "Read privacy and policy"
        )
    }
}

//@Preview(showBackground = true, name = "NEXUS_7", device = Devices.NEXUS_7)
//@Preview(showBackground = true, name = "NEXUS_7_2013", device = Devices.NEXUS_7_2013)
@Preview(showBackground = true, name = "NEXUS_5", device = Devices.NEXUS_5)
//@Preview(showBackground = true, name = "NEXUS_6", device = Devices.NEXUS_6)
//@Preview(showBackground = true, name = "NEXUS_9", device = Devices.NEXUS_9)
//@Preview(showBackground = true, name = "NEXUS_10", device = Devices.NEXUS_10)
//@Preview(showBackground = true, name = "NEXUS_5X", device = Devices.NEXUS_5X)
//@Preview(showBackground = true, name = "NEXUS_6P", device = Devices.NEXUS_6P)
//@Preview(showBackground = true, name = "PIXEL_C", device = Devices.PIXEL_C)
//@Preview(showBackground = true, name = "PIXEL", device = Devices.PIXEL)
//@Preview(showBackground = true, name = "PIXEL_XL", device = Devices.PIXEL_XL)
//@Preview(showBackground = true, name = "PIXEL_2", device = Devices.PIXEL_2)
//@Preview(showBackground = true, name = "PIXEL_2_XL", device = Devices.PIXEL_2_XL)
//@Preview(showBackground = true, name = "PIXEL_3", device = Devices.PIXEL_3)
//@Preview(showBackground = true, name = "PIXEL_3_XL", device = Devices.PIXEL_3_XL)
//@Preview(showBackground = true, name = "PIXEL_3A", device = Devices.PIXEL_3A)
//@Preview(showBackground = true, name = "PIXEL_3A_XL", device = Devices.PIXEL_3A_XL)
//@Preview(showBackground = true, name = "PIXEL_4", device = Devices.PIXEL_4)
@Preview(showBackground = true, name = "PIXEL_4_XL", device = Devices.PIXEL_4_XL)
//@Preview(showBackground = true, name = "AUTOMOTIVE_1024p", device = Devices.AUTOMOTIVE_1024p)
@Composable
fun LoginScreenPreview() {
    CookhubTheme {
        LoginScreen(navController = rememberNavController())
    }
}