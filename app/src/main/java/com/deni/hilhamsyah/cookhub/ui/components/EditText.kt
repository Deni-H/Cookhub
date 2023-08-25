package com.deni.hilhamsyah.cookhub.ui.components

import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deni.hilhamsyah.cookhub.ui.theme.CookhubTheme

@Composable
fun EditText(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    enabled: Boolean = true,
    maxLines: Int = 1,
    leadingIconId: Int? = null,
    trailingIconId: Int? = null,
    errorMessage: String? = null
) {
    var leadingIcon: @Composable (() -> Unit)? = null
    var trailingIcon: @Composable (() -> Unit)? = null

    if(leadingIconId != null) leadingIcon = { CreateIcon(leadingIconId) }
    if(trailingIconId != null) trailingIcon = { CreateIcon(trailingIconId) }

    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.padding(bottom = 8.dp),
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            shape = RoundedCornerShape(10.dp),
            placeholder = { Text(text = placeholder, color = Color(0xFFD9D9D9)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFCB3838),
                unfocusedBorderColor = Color(0xFFD9D9D9),
                disabledContainerColor = Color(0xFFF1F1F1),
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                errorContainerColor = Color.White,
                focusedTextColor = Color(0xFF303030),
                unfocusedLeadingIconColor = Color(0xFFD9D9D9),
                focusedLeadingIconColor = Color(0xFFD9D9D9),
                unfocusedTrailingIconColor = Color(0xFFD9D9D9),
                focusedTrailingIconColor = Color(0xFFD9D9D9),
            ),
            maxLines = maxLines,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = errorMessage != null,
        )
        if (errorMessage != null) Text(
            text = errorMessage,
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFFCB3838)
        )
    }
}

@Composable
private fun CreateIcon(iconId: Int) {
    Icon(
        imageVector = ImageVector.vectorResource(id = iconId),
        contentDescription = null
    )
}

fun validateEmail(value: String): String? {
    val errorMessage = "Invalid email format"
    return if (value.isEmpty()) errorMessage
    else if(!EMAIL_ADDRESS.matcher(value).matches()) errorMessage
    else null
}

fun validatePassword(password: String): String? {
    if (password.length < 8) {
        return "Password must be at least 8 characters long"
    }

    if (!password.matches(".*[a-z].*".toRegex())) {
        return "Password must contain at least one lowercase letter"
    }

    if (!password.matches(".*[A-Z].*".toRegex())) {
        return "Password must contain at least one uppercase letter"
    }

    if (!password.matches(".*\\d.*".toRegex())) {
        return "Password must contain at least one digit"
    }

    if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*".toRegex())) {
        return "Password must contain at least one special character"
    }

    return null
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun EditTextPreview() {
    CookhubTheme {
        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var emailErrorMsg: String? by remember { mutableStateOf(null) }
        var passwordErrorMsg: String? by remember { mutableStateOf(null) }

        Column {
            EditText(
                modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                value = email,
                onValueChange = {
                    email = it
                    emailErrorMsg = validateEmail(it)
                },
                placeholder = "Email",
                errorMessage = emailErrorMsg
            )
            EditText(
                modifier = Modifier.padding(10.dp),
                value = password,
                onValueChange = {
                    password = it
                    passwordErrorMsg = validatePassword(it)
                },
                placeholder = "Password",
                errorMessage = passwordErrorMsg
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun EditTextDisabledPreview() {
    CookhubTheme {
        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        Column {
            EditText(
                modifier = Modifier.padding(10.dp),
                value = password,
                onValueChange = { password = it },
                placeholder = "Email",
                enabled = true,
            )
            EditText(
                modifier = Modifier.padding(10.dp),
                value = email,
                onValueChange = { email = it },
                placeholder = "Password",
                enabled = true,
            )
        }
    }
}