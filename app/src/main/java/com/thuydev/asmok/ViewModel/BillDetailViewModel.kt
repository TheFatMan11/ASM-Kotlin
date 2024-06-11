package com.thuydev.asmok.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thuydev.asmok.GUI.Bill
import com.thuydev.asmok.GUI.BillDetail
import com.thuydev.asmok.GUI.Cart
import com.thuydev.asmok.GUI.Product
import com.thuydev.asmok.GUI.ProductDetail
import com.thuydev.asmok.Interface.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BillDetailViewModel :ViewModel() {
    val _listBillDetail = MutableLiveData<List<BillDetail>>()

    var listBillDetail:LiveData<List<BillDetail>> = _listBillDetail

    init {

    }

    fun GetBillDetail(idUser:String){
        val call = API.GetAPI().GetProductDetail(idUser)
        call!!.enqueue(object : Callback<List<BillDetail>?> {
            override fun onResponse(
                call: Call<List<BillDetail>?>,
                response: Response<List<BillDetail>?>
            ) {
                if(response.isSuccessful){
                    _listBillDetail.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<BillDetail>?>, t: Throwable) {
               LogOK(t)
            }
        })
    }
    fun GetBillDetail(i:Int): BillDetail {
        return listBillDetail.value!!.get(i)
    }
}