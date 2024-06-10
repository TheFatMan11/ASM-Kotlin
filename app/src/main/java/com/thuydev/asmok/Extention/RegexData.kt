package com.thuydev.asmok.Extention

class RegexData {
    companion object{
        val CHECK_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
        val CHECK_NUMBER_PHONE = "^0\\d{9,10}$";

        fun CheckRegex(regex: String,input:String):Boolean{
            return !input.matches(Regex(regex))
        }

    }
}