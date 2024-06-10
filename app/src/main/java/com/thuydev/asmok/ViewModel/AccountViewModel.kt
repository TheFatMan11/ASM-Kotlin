package com.thuydev.asmok.ViewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thuydev.asmok.Extention.RegexData
import com.thuydev.asmok.GUI.Account
import com.thuydev.asmok.Interface.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountViewModel :ViewModel() {
    val _account = MutableLiveData<Account>()
    var account :LiveData<Account> = _account


    fun Login (userName:String,pass:String,run:()->Unit,smg:(String)->Unit){
        if(userName.isEmpty()||pass.isEmpty()){
            smg("Khong duoc de trong")
            return
        }
        var call = API.GetAPI().Login(Account().apply {Email = userName;Password = pass})
        call!!.enqueue(object : retrofit2.Callback<Account?> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: retrofit2.Call<Account?>,
                response: retrofit2.Response<Account?>
            ) {
                if (response.isSuccessful)
                    _account.value = response.body()
                    run()
            }

            override fun onFailure(call: retrofit2.Call<Account?>, t: Throwable) {
                Log.e("TAG", "onFailure: $t")
            }
        })
    }
    fun Reg(email:String, pass: String, rePass:String, fullName:String, numberPhone:String, MSG:(String)->Unit,action:()->Unit){
        if (email.isEmpty()||pass.isEmpty()||fullName.isEmpty()||numberPhone.isEmpty()){
            MSG("Khong duoc de trong")
            return
        }
        if(!pass.equals(rePass)){
            MSG("Password khong khop voi nhau")
            return
        }
        if (RegexData.CheckRegex(RegexData.CHECK_EMAIL,email)){
            MSG("Email khong hop le")
            return
        }
        if (RegexData.CheckRegex(RegexData.CHECK_NUMBER_PHONE,numberPhone)){
            MSG("So dien thoai khong hop le")
            return
        }
        if(fullName.length<10){
            MSG("Ten phai dai hon 10 ky tu")
            return
        }
        var call = API.GetAPI().SiginFun(Account().apply { Email=email; Password = pass;FullName = fullName;NumberPhone = numberPhone })
        call!!.enqueue(object : Callback<Account?> {
            override fun onResponse(call: Call<Account?>, response: Response<Account?>) {
                if(response.isSuccessful){
                    MSG("Dang ky thanh cong")
                    action()
                    LogOK("Dang ky thanh cong")
                }else{
                    LogOK(response.toString())
                }
            }

            override fun onFailure(call: Call<Account?>, t: Throwable) {
               LogOK(t)
            }
        })
    }
}
fun LogOK(msg:Any){
    Log.e("TAG", "LogOK: $msg" )
}