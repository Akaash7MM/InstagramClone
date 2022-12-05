package com.example.instagramclone.fragments.compose.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramclone.fragments.ui.theme.EtGrey

@Composable
fun GreyTextField(text: String, hintText: String, onValueChange: (String) -> Unit) {
    TextField(
        modifier = Modifier.width(300.dp)
            .height(46.dp),
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        shape = RoundedCornerShape(5.dp),
        placeholder = {
            Text(text = hintText, fontSize = 12.sp, color = Color.Gray)
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = MaterialTheme.colors.EtGrey
        )
    )
}
