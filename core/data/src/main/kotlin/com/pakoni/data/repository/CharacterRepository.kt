package com.pakoni.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pakoni.data.model.Character
import com.pakoni.data.util.CharacterPagingSource
import com.pakoni.network.retrofit.RetrofitPakoniNetwork
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(val api: RetrofitPakoniNetwork) {
    companion object{
        const val MAX_ITEMS = 10
        const val PREFETCH_ITEMS = 3
    }

    fun getAllCharacters(): Flow<PagingData<Character>>{
        return Pager(config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = {
                CharacterPagingSource(api)
            }).flow

    }
}