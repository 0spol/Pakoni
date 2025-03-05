package com.pakoni.bookmarks

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.pakoni.data.repository.CharacterRepository
import com.pakoni.model.data.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class BookMarksViewModel @Inject constructor(
    characterRepository: CharacterRepository
) : ViewModel() {
    val characters: Flow<PagingData<CharacterModel>> = characterRepository.getAllCharacters()
}

