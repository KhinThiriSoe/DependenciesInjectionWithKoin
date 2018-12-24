package com.raywenderlich.android.insertkoin.di

import com.raywenderlich.android.insertkoin.repository.RemoteRepository
import com.raywenderlich.android.insertkoin.repository.Repository
import com.raywenderlich.android.insertkoin.ui.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val mainModule = module {
    single { RemoteRepository(get()) as Repository }

    viewModel { MainViewModel(get()) }
}