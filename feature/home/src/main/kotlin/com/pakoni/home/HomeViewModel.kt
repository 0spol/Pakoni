package com.pakoni.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.pakoni.data.repository.CharacterRepository
import com.pakoni.model.data.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class HomeViewModel @Inject constructor(characterRepository: CharacterRepository) : ViewModel() {

    val characters: Flow<PagingData<CharacterModel>> = characterRepository.getAllCharacters()

}