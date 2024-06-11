package com.thuydev.asmok.Interface

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.thuydev.asmok.GUI.Account
import com.thuydev.asmok.GUI.Bill
import com.thuydev.asmok.GUI.BillDetail
import com.thuydev.asmok.GUI.Cart
import com.thuydev.asmok.GUI.Category
import com.thuydev.asmok.GUI.Product
import com.thuydev.asmok.GUI.ProductDetail
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface API {
    companion object {
        val domain: String = "http://10.0.2.2:3000/";

        fun GetAPI(): API {
            val gson: Gson = GsonBuilder().setLenient().create()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(domain)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(API::class.java)
        }
    }

    @GET("apiuser/user/total/cart/{id}")
    fun GetTotalCart(@Path("id") id:String):Call<Number>
    @GET("apiuser/product")
    fun GetListProducts(): Call<List<Product>?>?

    @GET("apiuser/product/{id}")
    fun GetProduct(@Path("id") id: String?): Call<Product?>?

    @GET("apiuser/productdetail")
    fun GetProductDetails(): Call<List<ProductDetail?>?>?

    @GET("apiuser/user/billdetail/{id}")
    fun GetProductDetail(@Path("id") id: String?): Call<List<BillDetail>>?

    @GET("apiuser/category")
    fun GetListCategory(): Call<List<Category?>?>?

    @GET("apiuser/category/{id}")
    fun GetCategory(@Path("id") id: String?): Call<Category?>?

    @GET("apiuser/product/category/{id}")
    fun GetListProductToCate(@Path("id") id: String?): Call<List<Product?>?>?

    @GET("apiuser/user/cart/{id}")
    fun GetListCarts(@Path("id") id: String?): Call<List<Cart?>?>?

    @POST("apiuser/user/cart/add")
    fun AddCart(@Body dto: Cart?): Call<String?>?

    @PUT("apiuser/cart/edit/{id}")
    fun EditCart(@Path("id") id: String?): Call<String?>?

    @DELETE("apiuser/cart/delete/{id}")
    fun DeleteCart(@Path("id") id: String?): Call<String?>?

    @POST("apiuser/bill/add")
    fun AddBill(@Body Data: HashMap<String, Any>?): Call<String?>?

    @GET("apiuser/user/bill/{id}")
    fun GetBills(@Path("id") id: String?): Call<List<Bill?>?>?

    @GET("apiuser/bill/{id}")
    fun GetBill(@Path("id") id: String?): Call<Bill?>?

    @GET("apiuser/user/billdetail/{id}")
    fun GetBillDetails(@Path("id") id: String?): Call<List<BillDetail?>?>?

    @GET("apiuser/user/billdetail/{id}/{firt}/{end}")
    fun GetBillDetailsToMonth(
        @Path("id") id: String?,
        @Path("firt") firt: String?,
        @Path("end") end: String?
    ): Call<List<BillDetail?>?>?

    @POST("apiuser/Reg")
    fun SiginFun(@Body account: Account?): Call<Account?>?

    @POST("apiuser/login")
    fun Login(@Body account: Account?): Call<Account?>?

    /*  @PUT("apiuser/changepassword")
      fun changePassword(@Body request: ChangePasswordRequest?): Call<ResponseBody?>?
  */

    @PUT("apiuser/user/update/{id}")
    fun UpdateProfile(@Path("id") id: String?, @Body account: Account?): Call<String?>?

    @Multipart
    @PUT("apiuser/user/update/{id}")
    fun UpdateProfile(
        @Path("id") id: String?,
        @Part avt: MultipartBody.Part?,
        @Part("Email") Email: RequestBody?,
        @Part("FullName") FullName: RequestBody?,
        @Part("NumberPhone") NumberPhone: RequestBody?
    ): Call<String?>?

    @GET("apiuser/Account")
    fun GetListAccount(): Call<List<Account?>?>?

    @GET("apiuser/DsBill")
    fun GetListxacnhan(): Call<List<BillDetail?>?>?

    // xóa ẩn(đổi status)
    @PUT("apiuser/xoahoadon/{id}")
    fun Xoahoadon(@Path("id") id: String?, @Body bill: Bill?): Call<Void?>?

    @PUT("apiuser/chapnhanhoadon/{id}")
    fun chapnhanhoadon(@Path("id") id: String?, @Body bill: Bill?): Call<Void?>?

    @Multipart
    @POST("apiuser/user/recharge")
    fun YeuCauNap(
        @Part image: MultipartBody.Part?,
        @Part("Email") Email: RequestBody?,
        @Part("IDUser") IDUser: RequestBody?,
        @Part("Money") Money: RequestBody?,
        @Part("Time") Time: RequestBody?
    ): Call<String?>?

    /* @GET("apiuser/user/recharge/{id}")
     fun GetRecharge(@Path("id") id: String?): Call<List<Recharge?>?>?*/
}