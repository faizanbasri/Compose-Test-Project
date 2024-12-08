package com.angelatest.data.repository.home.medicineRepository

interface HomeRepository {

    suspend fun getProblems(): List<Map<String, String>>
}