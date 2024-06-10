package com.thuydev.asmok.GUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.thuydev.asmok.Extention.colorFromHex

@Preview
@Composable
fun CardView(image:String,price:Number,namePro:String,category:String){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp, 8.dp)
            .background(Color(0xFFB3B3B3), RoundedCornerShape(10.dp)).padding(start = 5.dp,top = 10.dp, bottom = 10.dp)) {
        Column(
            Modifier
                .width(150.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
           AsyncImage(model = image, contentDescription = image,Modifier.size(150.dp).padding(0.dp,0.dp,0.dp,10.dp))
            Text(text = "Gia: $price",
                Modifier
                    .background(Color.White)
                    , fontSize = 13.sp)
        }
        Column {
            Text(text = namePro, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = category, fontWeight = FontWeight.Bold, fontSize = 20.sp)

        }
    }
}
@Composable
fun CartScreen(){
    var listCart  = listOf<Cart>(
        Cart("1",)
    )
    var listPro = listOf<Product>()
    var listDetail = listOf<ProductDetail>()
    var tong = remember {
        mutableLongStateOf(0)
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(Modifier.weight(1f)) {
                items(listCart){itemCart: Cart ->
                    CardView(image = "https://picsum.photos/200",9999,"SP1","Loai 1")
                }
            }
            Text(text = "",modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = colorFromHex(mauCam)))
            Row(
                Modifier
                    .padding(20.dp, 5.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
               Row {
                   Text(text = "Tong ", fontWeight = FontWeight.Bold, color = colorFromHex(mauCam))
                   Text(text = " { Da bao gom thue }",color = colorFromHex(mauCam))
               }
                Text(text = "${tong.value} VND",color = colorFromHex(mauCam))
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .size(50.dp)
                    .padding(20.dp, 5.dp)
                    .background(colorFromHex("#FEC389"))
                    .padding(10.dp),horizontalArrangement = Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Dat hang", fontWeight = FontWeight.Bold, color = Color.White,)
            Text(text = "-->", fontWeight = FontWeight.Bold, color = Color.White,)
        }
    }
}