package com.deni.hilhamsyah.cookhub.data.local

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.deni.hilhamsyah.cookhub.domain.repository.DatastoreRepository
import com.deni.hilhamsyah.cookhub.util.Constant
import com.deni.hilhamsyah.cookhub.util.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constant.DATASTORE_NAME)
class DatastoreRepositoryImpl(context: Context) : DatastoreRepository {

    private val dataStore = context.dataStore

    override suspend fun saveOnboardingState(isComplete: Boolean) {
        dataStore.edit { preference ->
            preference[onBoardingKey] = isComplete
        }
    }

    override fun readOnboardingState(): Flow<Boolean> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else {
                Log.e(TAG, "readOnboardingState: $exception")
                throw exception
            }
        }.map { preferences ->
            val onBoardingState = preferences[onBoardingKey] ?: false
            onBoardingState
        }
    }

    private companion object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(Constant.ONBOARDING_KEY)
    }
}