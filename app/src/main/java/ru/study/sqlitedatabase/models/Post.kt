package ru.study.sqlitedatabase.models

import java.io.Serializable

data class Post (
    val email: String?,
    val title: String,
    val description: String,
    val authorFullName: String
): Serializable
