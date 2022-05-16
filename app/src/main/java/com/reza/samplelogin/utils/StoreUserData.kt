package com.reza.samplelogin.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreUserData @Inject constructor(@ApplicationContext val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.USER_INFO_DATASTORE)
        val userTokens = stringPreferencesKey(Constants.USER_TOKEN)
    }

    suspend fun saveUserToken(token: String) {
        context.dataStore.edit {
            it[userTokens] = token
        }
    }

    fun getUserToke() = context.dataStore.data.map {
        it[userTokens] ?: ""
    }
}