package com.assignment.alkyeassignment.views.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DashboardViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboarddViewModel::class.java)) {
            return DashboarddViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}