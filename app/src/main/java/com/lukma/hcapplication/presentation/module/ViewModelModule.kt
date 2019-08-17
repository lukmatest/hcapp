package com.lukma.hcapplication.presentation.module

import com.lukma.hcapplication.presentation.main.MainViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<MainViewModel>()
}
