package com.ifs21053.lostfounds.data.repository


import com.google.gson.Gson
import com.ifs21053.lostfounds.data.remote.MyResult
import com.ifs21053.lostfounds.data.remote.response.DelcomResponse
import com.ifs21053.lostfounds.data.remote.retrofit.IApiService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class LostFoundRepository private constructor(
    private val apiService: IApiService,
) {
    fun postTodo(
        title: String,
        description: String,
    ) = flow {
        emit(MyResult.Loading)
        try {
            //get success message
            emit(
                MyResult.Success(
                    apiService.postLostFound(title, description).data
                )
            )
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            emit(
                MyResult.Error(
                    Gson()
                        .fromJson(jsonInString, DelcomResponse::class.java)
                        .message
                )
            )
        }
    }

    fun putTodo(
        todoId: Int,
        title: String,
        description: String,
        isFinished: Boolean,
    ) = flow {
        emit(MyResult.Loading)
        try {
            //get success message
            emit(
                MyResult.Success(
                    apiService.putLostFound(
                        todoId,
                        title,
                        description,
                        if (isFinished) 1 else 0
                    )
                )
            )
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            emit(
                MyResult.Error(
                    Gson()
                        .fromJson(jsonInString, DelcomResponse::class.java)
                        .message
                )
            )
        }
    }

    fun getTodos(
        isFinished: Int?,
    ) = flow {
        emit(MyResult.Loading)
        try {
            //get success message
            emit(MyResult.Success(apiService.getLostFounds(isFinished)))
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            emit(
                MyResult.Error(
                    Gson()
                        .fromJson(jsonInString, DelcomResponse::class.java)
                        .message
                )
            )
        }
    }

    fun getTodo(
        todoId: Int,
    ) = flow {
        emit(MyResult.Loading)
        try {
            //get success message
            emit(MyResult.Success(apiService.getLostFound(todoId)))
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            emit(
                MyResult.Error(
                    Gson()
                        .fromJson(jsonInString, DelcomResponse::class.java)
                        .message
                )
            )
        }
    }

    fun deleteTodo(
        todoId: Int,
    ) = flow {
        emit(MyResult.Loading)
        try {
            emit(MyResult.Success(apiService.deleteLostFound(todoId)))
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            emit(
                MyResult.Error(
                    Gson()
                        .fromJson(jsonInString, DelcomResponse::class.java)
                        .message
                )
            )
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: LostFoundRepository? = null
        fun getInstance(
            apiService: IApiService,
        ): LostFoundRepository {
            synchronized(LostFoundRepository::class.java) {
                INSTANCE = LostFoundRepository(
                    apiService
                )
            }
            return INSTANCE as LostFoundRepository
        }
    }
}


