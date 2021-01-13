package com.angoti.projetointegrador4_2020.httpclient

import com.angoti.projetointegrador4_2020.dto.*
import retrofit2.Call
import retrofit2.http.*

interface EndPoint {

    //User ===========================================================================================

    @GET("users")
    fun obterUsuarios(@Header("Authorization") token: String?): Call<List<UserDto>>

    @POST("users")
    fun cadastraUsuario(@Body user: UserDto): Call<UserDto>

    @POST("auth/login")
    fun login(@Body loginDTO: LoginDTO): Call<LoginDTO>

    @PUT("users/{id}")
    fun alterarUsuario(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body user: UserDto): Call<Void>

    @DELETE("users/{id}")
    fun excluirUsuario(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String): Call<Void>

    //Address User ===========================================================================================

    @GET("address_user")
    fun obterEnderecoUsuarios(@Header("Authorization") token: String?): Call<List<AddressDTO>>

    @POST("address_user/{id}")
    fun cadastraEnderecoUsuario(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body user: AddressDTO): Call<AddressDTO>

    @PUT("address_user/{id}")
    fun alterarEnderecoUsuario(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body user: AddressDTO): Call<Void>

    @DELETE("address_user/{id}")
    fun excluirEnderecoUsuario(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String): Call<Void>

    //User ===========================================================================================

    @GET("dealership")
    fun obterConcessionarias(@Header("Authorization") token: String?): Call<List<DtoDealership>>

    @POST("dealership")
    fun cadastraConcessionaria(@Body user: DtoDealership,@Header("Authorization") token: String?): Call<DtoDealership>

    @PUT("dealership/{id}")
    fun alterarConcessionaria(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body user: DtoDealership): Call<Void>

    @DELETE("dealership/{id}")
    fun excluirConcessionaria(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String): Call<Void>

    //Address Dealership ===========================================================================================

    @GET("address_dealership")
    fun obterEnderecoConcessionaria(@Header("Authorization") token: String?): Call<List<AddressDTO>>

    @POST("address_dealership/{id}")
    fun cadastraEnderecoConcessionaria(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body user: AddressDTO): Call<AddressDTO>

    @PUT("address_dealership/{id}")
    fun alterarEnderecoConcessionaria(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body user: AddressDTO): Call<Void>

    @DELETE("address_dealership/{id}")
    fun excluirEnderecoConcessionaria(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String): Call<Void>


















}