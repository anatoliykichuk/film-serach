package ru.geekbrains.filmserach.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.geekbrains.filmserach.ui.main.MainViewModel

val appModule = module {
    // View models
    viewModel { MainViewModel() }

}