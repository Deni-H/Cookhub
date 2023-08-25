package com.deni.hilhamsyah.cookhub.use_case

import android.util.Patterns

object InputValidator {
    fun validateEmail(value: String): String? {
        val errorMessage = "Invalid email format"
        return if (value.isEmpty()) errorMessage
        else if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()) errorMessage
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
}