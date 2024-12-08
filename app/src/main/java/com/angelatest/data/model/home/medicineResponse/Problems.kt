package com.angelatest.data.model.home.medicineResponse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "problems")
data class Problems(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val problems: List<Map<String, List<Map<String, Any>>>> = emptyList()
)