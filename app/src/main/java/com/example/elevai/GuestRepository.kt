package com.example.elevai

class GuestRepository {

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository()
            }
            return repository
        }
    }

}