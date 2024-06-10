package com.thuydev.asmok.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thuydev.asmok.GUI.Cart
import com.thuydev.asmok.GUI.Category
import com.thuydev.asmok.GUI.Product
import com.thuydev.asmok.GUI.ProductDetail
import com.thuydev.asmok.Interface.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel :ViewModel() {
    val _listCategory = MutableLiveData<List<Category>>()

    var listCategory:LiveData<List<Category>> = _listCategory

    init {
        GetData()
    }

       fun GetData(){
            var call = API.GetAPI().GetListCategory()
            call!!.enqueue(object : Callback<List<Category?>?> {
                override fun onResponse(
                    call: Call<List<Category?>?>,
                    response: Response<List<Category?>?>
                ) {
                    if (response.isSuccessful){
                        _listCategory.value = response.body() as List<Category>?
                    }
                }

                override fun onFailure(call: Call<List<Category?>?>, t: Throwable) {

                }
            })

        }

    fun GetCart(i:Int): Category {
        return listCategory.value!!.get(i)
    }
}