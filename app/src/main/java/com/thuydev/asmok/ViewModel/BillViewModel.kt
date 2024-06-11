package com.thuydev.asmok.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thuydev.asmok.GUI.Bill
import com.thuydev.asmok.GUI.Cart
import com.thuydev.asmok.GUI.Product
import com.thuydev.asmok.GUI.ProductDetail
import com.thuydev.asmok.Interface.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BillViewModel :ViewModel() {
    val _listBill = MutableLiveData<List<Bill>>()

    var listBill:LiveData<List<Bill>> = _listBill

    init {
        _listBill.value=Bill.getData()
    }
    fun AddBill(data:HashMap<String,Any>,msg:(String)->Unit,action:()->Unit){
        val  call = API.GetAPI().AddBill(data)
        call!!.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                msg(response.body()!!)
                if (response.isSuccessful){
                    action()
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {

            }
        })
    }
    fun GetBill(i:Int): Bill {
        return listBill.value!!.get(i)
    }

}