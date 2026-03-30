package com.asalazar.egyptmuseum.di

import com.asalazar.egyptmuseum.data.settings.SettingsRepository
import com.asalazar.egyptmuseum.dataStore
import com.asalazar.egyptmuseum.ui.settings.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single { androidContext().dataStore }

    single { SettingsRepository(get()) }

    viewModel {
        SettingsViewModel(get())
    }
}
