package com.twinrock.mymvvm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: Int?,
    val name: String,
    val email: String,
    val password: String)