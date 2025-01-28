package com.example.headhunter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headhunter.model.Offer
import com.example.headhunter.repository.OfferRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OfferViewModel: ViewModel() {
    private val repository = OfferRepository()

    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers: StateFlow<List<Offer>> = _offers

    init {
        loadOffers()
    }

    private fun loadOffers() {
        viewModelScope.launch {
            _offers.value = repository.getOffer()
        }
    }
}