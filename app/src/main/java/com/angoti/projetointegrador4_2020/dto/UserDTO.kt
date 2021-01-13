package com.angoti.projetointegrador4_2020.dto

data class UserDto(
    val email: String,
    val id: Long,
    val name: String,
    val password: String,
    val phone: String,
    val cpfCnpj: String,
    val cnh: String,
    val dataNascimento: String
)