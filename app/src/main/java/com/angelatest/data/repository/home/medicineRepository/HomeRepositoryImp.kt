package com.angelatest.data.repository.home.medicineRepository

import com.angelatest.data.local.dao.home.ProblemsDao
import com.angelatest.data.remote.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val apiService: ApiService, private val dao: ProblemsDao
) : HomeRepository {

    override suspend fun getProblems(): List<Map<String, String>> {
        val response = apiService.getProblems()
        val drugList = mutableListOf<Map<String, String>>()

        if (response.isSuccessful && response.body() != null) {
            val newProblems = response.body()!!

            withContext(Dispatchers.IO) {
                dao.clearProblems()
                dao.insertProblems(newProblems)
            }

            withContext(Dispatchers.IO) {
                val problem = dao.getProblem()?.problems
                problem?.let {
                    drugList.addAll(compileData(it))
                }

            }
        } else {
            withContext(Dispatchers.IO) {
                val problem = dao.getProblem()?.problems
                problem?.let {
                    drugList.addAll(compileData(it))
                }
            }
        }


        return drugList
    }

    private fun compileData(problem: List<Map<String, List<Map<String, Any>>>>): Collection<Map<String, String>> {
        val drugList = mutableListOf<Map<String, String>>()
        problem.forEach { problemMap ->
            problemMap.values.forEach { problemDetails ->
                problemDetails.forEach { detailMap ->
                    val medications = detailMap["medications"] as? List<*>
                    medications?.forEach { medication ->
                        val medicationMap = medication as? Map<*, *>
                        val medicationClasses = medicationMap?.get("medicationsClasses") as? List<*>
                        medicationClasses?.forEach { medicationClass ->
                            val classMap = medicationClass as? Map<*, *>
                            listOf("className", "className2").forEach { classKey ->
                                val classNames = classMap?.get(classKey) as? List<*>
                                classNames?.forEach { className ->
                                    val classNameMap = className as? Map<*, *>
                                    listOf(
                                        "associatedDrug",
                                        "associatedDrug#2"
                                    ).forEach { drugKey ->
                                        val drugs = classNameMap?.get(drugKey) as? List<*>
                                        drugs?.forEach { drug ->
                                            val drugMap = drug as? Map<*, *>
                                            drugList.add(
                                                mapOf(
                                                    "name" to (drugMap?.get("name") as? String
                                                        ?: ""),
                                                    "dose" to (drugMap?.get("dose") as? String
                                                        ?: ""),
                                                    "strength" to (drugMap?.get("strength") as? String
                                                        ?: "")
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return drugList
    }
}