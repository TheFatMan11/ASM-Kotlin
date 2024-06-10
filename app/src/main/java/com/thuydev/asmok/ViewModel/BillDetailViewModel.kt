package com.thuydev.asmok.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thuydev.asmok.GUI.Bill
import com.thuydev.asmok.GUI.BillDetail
import com.thuydev.asmok.GUI.Cart
import com.thuydev.asmok.GUI.Product
import com.thuydev.asmok.GUI.ProductDetail

class BillDetailViewModel :ViewModel() {
    val _listBillDetail = MutableLiveData<List<BillDetail>>()

    var listBillDetail:LiveData<List<BillDetail>> = _listBillDetail

    init {
        _listBillDetail.value=BillDetail.getData()
    }
    fun GetBillDetail(i:Int): BillDetail {
        return listBillDetail.value!!.get(i)
    }
}