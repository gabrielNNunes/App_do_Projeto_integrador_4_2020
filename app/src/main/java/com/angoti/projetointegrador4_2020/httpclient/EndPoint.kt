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
    fun cadastraEnderecoUsuario(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body address: AddressDTO): Call<AddressDTO>

    @PUT("address_user/{id}")
    fun alterarEnderecoUsuario(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body address: AddressDTO): Call<Void>

    @DELETE("address_user/{id}")
    fun excluirEnderecoUsuario(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String): Call<Void>

    //User ===========================================================================================

    @GET("dealership")
    fun obterConcessionarias(@Header("Authorization") token: String?): Call<List<DtoDealership>>

    @POST("dealership")
    fun cadastraConcessionaria(@Body dealership: DtoDealership,@Header("Authorization") token: String?): Call<DtoDealership>

    @PUT("dealership/{id}")
    fun alterarConcessionaria(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body dealership: DtoDealership): Call<Void>

    @DELETE("dealership/{id}")
    fun excluirConcessionaria(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String): Call<Void>

    //Address Dealership ===========================================================================================

    @GET("address_dealership")
    fun obterEnderecoConcessionaria(@Header("Authorization") token: String?): Call<List<AddressDTO>>

    @POST("address_dealership/{id}")
    fun cadastraEnderecoConcessionaria(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body address: AddressDTO): Call<AddressDTO>

    @PUT("address_dealership/{id}")
    fun alterarEnderecoConcessionaria(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body address: AddressDTO): Call<Void>

    @DELETE("address_dealership/{id}")
    fun excluirEnderecoConcessionaria(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String): Call<Void>

    //Category ===========================================================================================

    @GET("categories")
    fun obterCategorias(): Call<List<CategoryDTO>>

    @POST("categories")
    fun cadastraCategoria(@Body categoria: CategoryDTO): Call<CategoryDTO>

    @PUT("categories/{id}")
    fun alterarCategoria(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body category: CategoryDTO): Call<Void>

    @DELETE("categories/{id}")
    fun excluirCategoria(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String): Call<Void>

    @GET("categories/product/{id}")
    fun obterCategoriaPorProduto(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String): Call<List<CategoryDTO>>

    //Category ===========================================================================================

    @GET("products")
    fun obterProducts(): Call<List<ProductDTO>>

    @POST("products")
    fun cadastraProduct( @Header("Authorization") tokenAutenticacao: String,@Body product: ProductDTO): Call<ProductDTO>

    @PUT("products/{id}")
    fun alterarProduct(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body product: ProductDTO): Call<Void>

    @DELETE("products/{id}")
    fun excluirProduct(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String): Call<Void>



    @PUT("/products/{id}/addcategory")
    fun adicionarCategoriaAoProduto(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body product: CategoryDTO): Call<Void>

    @PUT("/products/{id}/removecategory")
    fun removerCategoriaDoProduto(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body product: CategoryDTO): Call<Void>

    @PUT("/products/{id}/setcategories")
    fun adicionarUmaListaDeCategoriasAoProduto(@Path("id") id: Long, @Header("Authorization") tokenAutenticacao: String, @Body product: List<CategoryDTO>): Call<Void>

















}