package com.example.mymovie.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovie.AppState
import com.example.mymovie.Repository
import com.example.mymovie.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) :
    ViewModel() {

    fun getLiveData() = liveDataToObserver

    fun getFilmsFromLocalSource() = getDataFromLocalSource()
    fun getFilmsFromRemoteSource() = getFilmsFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserver.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserver.postValue(
                AppState.Success(
                    repositoryImpl.getPopularFilmsFromLocaleStorage(),
                    repositoryImpl.getComedyFilmsFromLocaleStorage()
                )
            )
        }.start()
    }
}