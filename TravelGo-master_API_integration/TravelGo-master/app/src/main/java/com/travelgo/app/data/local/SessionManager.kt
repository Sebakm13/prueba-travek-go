
package com.travelgo.app.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SessionManager(private val context: Context) {
    companion object {
        private val Context.dataStore by preferencesDataStore(name = "session_prefs")
        private val KEY_AUTH_TOKEN = stringPreferencesKey("auth_token")
    }
    suspend fun saveAuthToken(token: String) {
        context.dataStore.edit { it[KEY_AUTH_TOKEN] = token }
    }
    suspend fun getAuthToken(): String? {
        return context.dataStore.data.map { it[KEY_AUTH_TOKEN] }.first()
    }
    suspend fun clearAuthToken() {
        context.dataStore.edit { it.remove(KEY_AUTH_TOKEN) }
    }
}
