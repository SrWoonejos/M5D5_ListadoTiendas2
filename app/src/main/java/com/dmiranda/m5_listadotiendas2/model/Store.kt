package com.dmiranda.m5_listadotiendas2.model

import android.os.Parcelable

@Parcelize
data class Store (
    val id:Int,
    val name: String,
    val address: String,
    val officeHours: String,
    val photo: String,
    val history: String
): Parcelable

annotation class Parcelize

