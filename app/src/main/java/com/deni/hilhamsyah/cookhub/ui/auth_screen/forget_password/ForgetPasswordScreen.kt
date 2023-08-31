package com.deni.hilhamsyah.cookhub.ui.auth_screen.forget_password

import android.widget.Toast
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.deni.hilhamsyah.cookhub.R
import com.deni.hilhamsyah.cookhub.navigation.Screen
import com.deni.hilhamsyah.cookhub.ui.auth_screen.AuthViewModel
import com.deni.hilhamsyah.cookhub.ui.components.CustomAppBar
import com.deni.hilhamsyah.cookhub.ui.components.CustomProgressDialog
import com.deni.hilhamsyah.cookhub.ui.components.CustomTextField
import com.deni.hilhamsyah.cookhub.ui.theme.CookhubTheme
import com.deni.hilhamsyah.cookhub.ui.theme.neutral50
import com.deni.hilhamsyah.cookhub.ui.theme.poppins
import com.deni.hilhamsyah.cookhub.util.InputValidator
import kotlinx.coroutines.launch

@Composable
fun ForgetPasswordScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    val authState = authViewModel.authState.collectAsState(initial = null)
    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current

    var email by rememberSaveable { mutableStateOf("") }
    var emailErrMsg: String? by rememberSaveable { mutableStateOf(null) }


    if (authState.value?.isLoading == true) {
        CustomProgressDialog()
    }

    LaunchedEffect(key1 = authState.value?.success) {
        if(authState.value?.success != null) {
            navController.popBackStack()
            navController.navigate(Screen.ForgetPasswordSuccessScreen.route)
        }
    }

    LaunchedEffect(key1 = authState.value?.fail) {
        if (authState.value?.fail != null) {
            Toast.makeText(context, context.getString(R.string.fail_reset_password), Toast.LENGTH_LONG).show()
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        val (
            appBarRef,
            descriptionRef,
            textFieldRef,
            btnRef,
            rememberPassContainerRef
        ) = createRefs()

        CustomAppBar(
            modifier = Modifier
                .constrainAs(appBarRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            navController = navController,
            title = stringResource(R.string.forgot_password_title)
        )
        Text(
            modifier = Modifier
                .constrainAs(descriptionRef) {
                    top.linkTo(appBarRef.bottom, 8.dp)
                    start.linkTo(parent.start)
                },
            color = neutral50,
            text = stringResource(R.string.forget_password_sub_title)
        )
        Column(modifier = Modifier.constrainAs(textFieldRef) {
            top.linkTo(descriptionRef.bottom, 20.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = {
                    email = it
                    emailErrMsg = InputValidator.validateEmail(it)
                },
                placeholder = stringResource(R.string.email_placeholder),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                )
            )
        }
        Button(
            modifier = Modifier
                .constrainAs(btnRef) {
                    top.linkTo(
                        anchor = textFieldRef.bottom,
                        margin = 20.dp
                    )
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                coroutine.launch {
                    authViewModel.resetPassword(email)
                }
            },
            enabled = emailErrMsg == null
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }

        Row(
            modifier = Modifier.constrainAs(rememberPassContainerRef) {
                top.linkTo(btnRef.bottom, 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = stringResource(R.string.remember_password))
            ClickableText(
                text = AnnotatedString(stringResource(R.string.login)),
                onClick = {
                    navController.popBackStack()
                },
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Preview
@Composable
fun ForgetPasswordScreenPreview() {
    CookhubTheme {
        ForgetPasswordScreen(rememberNavController())
    }
}