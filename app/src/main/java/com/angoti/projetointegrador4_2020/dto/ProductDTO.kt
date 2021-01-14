package com.angoti.projetointegrador4_2020.dto


data class ProductDTO(

    val id:Long,
    val cor:String,
    val description:String,
    val imgUrl:String,
    val modelo:String,
    val placa:String,
    val precoDiaria: Int,
    val quilometragem: Int,
    val statusveiculo: String
    )