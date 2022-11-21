package com.transistorapps.userstestapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transistorapps.userstestapp.domain.ApiError
import com.transistorapps.userstestapp.domain.ApiResult
import com.transistorapps.userstestapp.domain.DataRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentVM @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _loadingFinishedEvent = MutableStateFlow(false)
    val loadingFinishedEvent = _loadingFinishedEvent.asStateFlow()

    private val _errorSnackBar = MutableSharedFlow<ApiError>()
    val errorSnackBar = _errorSnackBar.asSharedFlow()

    val usersFlow = dataRepository.getUsers()

    init {
        syncData()
    }

    fun syncData() {
        viewModelScope.launch {
            _loading.value = true
            when (val apiResult = dataRepository.syncData()) {
                is ApiResult.Success -> {
                    _loading.value = false
                }

                is ApiResult.Error -> {
                    _loading.value = false
                    _errorSnackBar.emit(apiResult.error)
                }
            }
            _loadingFinishedEvent.value = true
        }
    }
}