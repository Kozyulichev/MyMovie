package com.example.mymovie.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovie.model.MovieDTO
import com.example.mymovie.model.Repository
import com.example.mymovie.model.RepositoryImpl
import com.example.mymovie.model.Result
import com.example.mymovie.model.api.RemoteDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Thread.sleep

private const val SERVER_ERROR = "Ошибка сервера"
private const val CORRUPTED_DATA = "Неполные данные"

class MainViewModel(
    private val liveDataToObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl(RemoteDataSource())
) :
    ViewModel() {

    fun getLiveData() = liveDataToObserver

    //fun getFilmsFromLocalSource() = getDataFromLocalSource()
    //fun getFilmsFromRemoteSource() = getFilmsFromLocalSource()
    fun getMovieFromRemoteDataSource(language: String) {
        liveDataToObserver.value = AppState.Loading
        repositoryImpl.getFilmsFromServer(language, callback)
    }

    private val callback = object :
        Callback<MovieDTO> {
        override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
            val serverResponse: MovieDTO? = response.body()
            liveDataToObserver.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }

            )
        }

        override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
            TODO("Not yet implemented")
        }

        private fun checkResponse(serverResponse: MovieDTO): AppState {
            var fact: List<Result> = serverResponse.results
            return if (fact == null) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.Success(serverResponse)
            }
        }
    }
}