package com.asalazar.egyptmuseum

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.asalazar.egyptmuseum.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

class EgyptMuseumApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EgyptMuseumApp)
            modules(appModule)
        }
    }
}
