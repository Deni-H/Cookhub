package com.deni.hilhamsyah.cookhub.util

import android.util.Patterns

object InputValidator {
    fun validateEmail(value: String): String? {
        return if (value.isEmpty()) "Email cannot be empty"
        else if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()) "Invalid email format"
        else null
    }

    fun validatePassword(password: String): String? {

        if (password.isEmpty()) {
            return "Password cannot be empty"
        }

        if (password.length < 6) {
            return "Password must be at least 6 characters long"
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