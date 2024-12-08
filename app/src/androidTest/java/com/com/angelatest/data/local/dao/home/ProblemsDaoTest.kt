package com.com.angelatest.data.local.dao.home

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import com.angelatest.data.local.dao.home.ProblemsDao
import com.angelatest.data.local.db.AppDatabase
import com.angelatest.data.model.home.medicineResponse.Problems
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProblemsDaoTest {

    private lateinit var problemsDao: ProblemsDao
    private lateinit var database: RoomDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).allowMainThreadQueries().build()

        problemsDao = (database as AppDatabase).problemsDao()
    }


    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertEmptyAndGetProblems() = runTest {
        val problem = Problems(id = 1, problems = listOf())
        problemsDao.insertProblems(problem)
        val fetchedProblems = problemsDao.getProblem()
        assertNotNull(fetchedProblems)
        assertEquals(problem, fetchedProblems)
    }

    @Test
    fun testInsertAndGetProblems() = runTest {
        val problem = Problems(id = 1, problems = listOf(mapOf("problem1" to listOf(mapOf("key1" to "value1")))))
        problemsDao.insertProblems(problem)
        val fetchedProblems = problemsDao.getProblem()
        assertNotNull(fetchedProblems)
        assertEquals(problem, fetchedProblems)
    }

    @Test
    fun testClearProblems() = runTest {
        val problem = Problems(id = 1, problems = listOf(mapOf("problem1" to listOf(mapOf("key1" to "value1")))))
        problemsDao.insertProblems(problem)
        problemsDao.clearProblems()
        delay(100)
        val fetchedProblem = problemsDao.getProblem()
        assertNull(fetchedProblem)
    }
}