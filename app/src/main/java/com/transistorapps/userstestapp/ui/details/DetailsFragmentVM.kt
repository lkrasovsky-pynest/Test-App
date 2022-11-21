package com.transistorapps.userstestapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transistorapps.userstestapp.domain.DataRepository
import com.transistorapps.userstestapp.domain.UserUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsFragmentVM @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {
    private val _user = MutableStateFlow<UserUiModel?>(null)
    val user = _user.asStateFlow()

    fun fetchUserWithPosts(userId: Int) {
        viewModelScope.launch {
            _user.value = dataRepository.getUserWithPosts(userId)
        }
    }
}