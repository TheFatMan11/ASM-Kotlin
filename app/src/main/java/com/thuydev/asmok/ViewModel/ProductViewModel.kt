package com.thuydev.asmok.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thuydev.asmok.GUI.Product
import com.thuydev.asmok.Interface.API
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel :ViewModel() {
    val _listProducts = MutableLiveData<List<Product>>()

    var listProduct:LiveData<List<Product>> = _listProducts

    init {
        GetData()

    }
    fun GetData(){
            var call = API.GetAPI().GetListProducts()
            call!!.enqueue(object : Callback<List<Product>?> {
                override fun onResponse(
                    call: Call<List<Product>?>,
                    response: Response<List<Product>?>
                ) {
                    if(response.isSuccessful){
                        Log.e("TAG", "onResponse: "+response.body() )
                        _listProducts.value = response.body()
                    }else{
                        Log.e("TAG", "onResponse: "+12 )
                    }
                }

                override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                    Log.e("TAG", "onResponse: "+t)
                }
            })
    }
    fun GetProduct(i:Int): Product {
        return listProduct.value!!.get(i)
    }
}

