package com.pakoni.data.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pakoni.data.model.Character
import com.pakoni.network.retrofit.RetrofitPakoniNetwork
import java.io.IOException
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(private val api: RetrofitPakoniNetwork) :
    PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

        return try {
            val page = params.key ?: 1
            val response = api.getCharacters(page)
            val characters = response.results

            val prevKey = if (page > 0) page - 1 else null
            val nextKey = if (response.information.next != null) page + 1 else null

            LoadResult.Page(
                data = characters.map { it.toPresentation() },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }

    }

}