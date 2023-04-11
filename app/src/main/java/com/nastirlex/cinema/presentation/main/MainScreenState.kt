package com.nastirlex.cinema.presentation.main

sealed class MainScreenState {
    object FreshLoading: MainScreenState()
    object DoneFreshLoading: MainScreenState()
    object BannerLoading: MainScreenState()
    object DoneBannerLoading: MainScreenState()
    object TrendsLoading: MainScreenState()
    object DoneTrendsLoading: MainScreenState()
    object LastViewLoading: MainScreenState()
    object DoneLastViewLoading: MainScreenState()
}