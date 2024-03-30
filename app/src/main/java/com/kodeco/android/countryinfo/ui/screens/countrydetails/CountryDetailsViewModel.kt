package com.kodeco.android.countryinfo.ui.screens.countrydetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kodeco.android.countryinfo.repositories.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountryDetailsViewModel(
    private val countryId: Int?,
    private val repository: CountryRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CountryDetailsState>(CountryDetailsState.Loading)

    val uiState: StateFlow<CountryDetailsState> = _uiState

    init {
        getCountryById()
    }

    class CountryDetailsViewModelFactory(
        private val countryIndex: Int?,
        private val repository: CountryRepository,
    ) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            CountryDetailsViewModel(countryIndex, repository) as T
    }

    private fun getCountryById() {
        viewModelScope.launch {
            _uiState.value = CountryDetailsState.Loading

            if (countryId == null) {
                _uiState.value = CountryDetailsState.Error(Exception("Country index is missing"))
                return@launch
            }

            _uiState.value = repository.getCountry(countryId)?.let { country ->
                CountryDetailsState.Success(country)
            } ?: CountryDetailsState.Error(Exception("Country not found"))
        }
    }
}
