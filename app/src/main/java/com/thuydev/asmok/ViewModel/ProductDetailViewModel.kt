package com.thuydev.asmok.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thuydev.asmok.GUI.Product
import com.thuydev.asmok.GUI.ProductDetail
import com.thuydev.asmok.Interface.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailViewModel :ViewModel() {
    val _list_productDetail = MutableLiveData<List<ProductDetail>>()
    val _productDetail = MutableLiveData<ProductDetail>()
    var listProductDetail:LiveData<List<ProductDetail>> = _list_productDetail
    var productDetail : LiveData<ProductDetail> = _productDetail
    init {
        GetData()
    }

    fun GetData() {
        var call = API.GetAPI().GetProductDetails()
        call!!.enqueue(object : Callback<List<ProductDetail?>?> {
            override fun onResponse(
                call: Call<List<ProductDetail?>?>,
                response: Response<List<ProductDetail?>?>
            ) {
                if(response.isSuccessful){
                    _list_productDetail.value = response.body() as List<ProductDetail>?
                }
            }

            override fun onFailure(call: Call<List<ProductDetail?>?>, t: Throwable) {

            }
        })
    }

    fun GetProductDetail(id:String): ProductDetail {
       for (data in listProductDetail.value!!){
           if(id.equals(data.IDProduct)) return data
       }
        return ProductDetail("", listOf(),"",0)
    }
}