package com.pakoni.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pakoni.data.model.Pallete
import com.pakoni.data.util.CharacterPagingSource
import com.pakoni.network.retrofit.RetrofitPakoniNetwork
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PalleteRepository @Inject constructor(val api: RetrofitPakoniNetwork){

    fun getAllPalletes(): Pallete{
        return Pallete(1, "1", true, "1")
    }
}