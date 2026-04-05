package com.asalazar.egyptmuseum.di

import com.asalazar.egyptmuseum.data.discovery.EgyptRepository
import com.asalazar.egyptmuseum.data.discovery.MockEgyptRepository
import com.asalazar.egyptmuseum.data.settings.SettingsRepository
import com.asalazar.egyptmuseum.dataStore
import com.asalazar.egyptmuseum.ui.articles.ArticlesViewModel
import com.asalazar.egyptmuseum.ui.discovery.DiscoveryViewModel
import com.asalazar.egyptmuseum.ui.settings.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {

    single { androidContext().dataStore }

    single { SettingsRepository(get()) }

    single<EgyptRepository> { MockEgyptRepository() }

    viewModel {
        SettingsViewModel(get())
    }

    viewModelOf(::DiscoveryViewModel)
    viewModelOf(::ArticlesViewModel)
}
