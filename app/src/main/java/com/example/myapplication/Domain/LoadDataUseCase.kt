package com.example.myapplication.Domain

import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke() = repository.loadData()
}