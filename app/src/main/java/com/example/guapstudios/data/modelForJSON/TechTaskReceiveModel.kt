package com.skat.database.tech_task

import kotlinx.serialization.Serializable

@Serializable
data class TechTaskUpdateExecutor(
    val id: String,
    val executor: String,
)

@Serializable
data class TechTaskUpdateIsTake(
    val id: String,
    val isTake: Boolean
)
