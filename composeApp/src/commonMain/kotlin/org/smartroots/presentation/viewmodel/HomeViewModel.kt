package org.smartroots.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.smartroots.domain.GetSensorReadingsUseCase
import org.smartroots.presentation.state.HomeUI

class HomeViewModel(private val getSensorReadingsUseCase: GetSensorReadingsUseCase):ViewModel() {
    private val _homeUIState = MutableStateFlow(HomeUI())
    val homeUIState: StateFlow<HomeUI> = _homeUIState.asStateFlow()

    fun updateLiveReadings(){
        viewModelScope.launch {
            val readingsFromTent = getSensorReadingsUseCase()
            _homeUIState.update {currentState->
                currentState.copy(sensorReadings = readingsFromTent)
            }
        }
    }
    fun updateLanguagePreference(languagePreference:String){
        _homeUIState.update { currentUI->
            currentUI.copy(languagePreference = languagePreference)
        }
    }
}

val homeViewModelModule= module{
    viewModelOf(::HomeViewModel)
}