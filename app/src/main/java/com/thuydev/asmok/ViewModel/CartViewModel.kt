package com.thuydev.asmok.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thuydev.asmok.GUI.Cart
import com.thuydev.asmok.GUI.Product
import com.thuydev.asmok.GUI.ProductDetail
import com.thuydev.asmok.Interface.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel :ViewModel() {
    val _listCart = MutableLiveData<List<Cart>>()

    var listCart:LiveData<List<Cart>> = _listCart

    init {

    }
   fun GetData(id:String,total:(Number)->Unit){
       val call = API.GetAPI().GetListCarts(id)
       val callTotal = API.GetAPI().GetTotalCart(id)
       call!!.enqueue(object : Callback<List<Cart?>?> {
           override fun onResponse(call: Call<List<Cart?>?>, response: Response<List<Cart?>?>) {
               if(response.isSuccessful){
                   _listCart.value = response.body() as List<Cart>?

               }else{
                   LogOK("$response")
               }
           }
           override fun onFailure(call: Call<List<Cart?>?>, t: Throwable) {
               LogOK("$t")
           }
       })
       callTotal!!.enqueue(object : Callback<Number?> {
           override fun onResponse(call: Call<Number?>, response: Response<Number?>) {
               if(response.isSuccessful){
                   total(response.body()!!)
               }else{
                   LogOK(response)
               }
           }

           override fun onFailure(call: Call<Number?>, t: Throwable) {
               LogOK(t.toString())
           }
       })
   }

    fun AddCart(id: String,idProduct: String,amount:Number,size:String,msg:(String)->Unit){
        if(amount==0){
            msg("So luong phai luong hon 0")
            return
        }
        if(size.isEmpty()){
            msg("Phai chon kich co")
            return
        }
        val call = API.GetAPI().AddCart(Cart().apply {IDUser = id;IDProduct=idProduct;Amount=amount;Size=size  })
        call!!.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
               msg(response.body().toString())
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
               LogOK("$t")
            }
        })
    }

    fun DeleteCart(id:String,msg:(String)->Unit,action:()->Unit){
        val  call = API.GetAPI().DeleteCart(id)
        call!!.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                msg(response.body()!!)
                if(response.isSuccessful){
                    action()
                }
            }
            override fun onFailure(call: Call<String?>, t: Throwable) {
                GetLoi(t.toString())
            }
        })
    }
}

fun GetLoi(msg:String){
    Log.e("TAG", "GetLoi: $msg" )
}