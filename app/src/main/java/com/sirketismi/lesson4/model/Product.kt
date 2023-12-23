package com.sirketismi.lesson4.model

import android.os.Parcelable

@Parcelize
data class Product ( val id:String,
                     val name:String,
                     val description :String ) :Parcelable{
}