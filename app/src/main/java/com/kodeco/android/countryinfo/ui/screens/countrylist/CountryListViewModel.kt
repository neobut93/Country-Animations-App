package com.kodeco.android.countryinfo.ui.screens.countrylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.repositories.CountryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CountryListViewModel(
    private val repository: CountryRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CountryListState>(CountryListState.Loading)

    val uiState: StateFlow<CountryListState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository
                .countries
                .catch {
                    _uiState.value = CountryListState.Error(it)
                }
                .collect {
                    _uiState.value = CountryListState.Success(it)
                }
        }

        fetchCountries()
    }

    class CountryInfoViewModelFactory(private val repository: CountryRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            CountryListViewModel(repository) as T
    }

    fun fetchCountries() {
        _uiState.value = CountryListState.Loading

        viewModelScope.launch {
            delay(1_500) // Delay added for visual effect
            repository.fetchCountries()
        }
    }

    fun favorite(country: Country) {
        viewModelScope.launch {
            repository.favorite(country)
        }
    }
}
