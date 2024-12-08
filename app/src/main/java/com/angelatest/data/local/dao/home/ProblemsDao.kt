package com.angelatest.data.local.dao.home

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.angelatest.data.model.home.medicineResponse.Problems

@Dao
interface ProblemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProblems(problem: Problems)

    @Query("SELECT * FROM problems")
    suspend fun getProblem(): Problems?

    @Query("DELETE FROM problems")
    suspend fun clearProblems()
}