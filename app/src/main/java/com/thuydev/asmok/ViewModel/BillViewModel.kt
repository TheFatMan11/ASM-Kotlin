package com.thuydev.asmok.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thuydev.asmok.GUI.Bill
import com.thuydev.asmok.GUI.Cart
import com.thuydev.asmok.GUI.Product
import com.thuydev.asmok.GUI.ProductDetail

class BillViewModel :ViewModel() {
    val _listBill = MutableLiveData<List<Bill>>()

    var listBill:LiveData<List<Bill>> = _listBill

    init {
        _listBill.value=Bill.getData()
    }
    fun GetBill(i:Int): Bill {
        return listBill.value!!.get(i)
    }
}