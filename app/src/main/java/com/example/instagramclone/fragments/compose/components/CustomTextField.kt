package com.example.instagramclone.fragments.compose.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramclone.fragments.ui.theme.EtGrey2

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: () -> String,
    hintText: String,
    backgroundColor: Color,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.width(336.dp)
            .height(48.dp),
        value = text(),
        onValueChange = onValueChange,
        shape = RoundedCornerShape(4.dp),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        placeholder = {
            Text(text = hintText, fontSize = 14.sp, color = Color.Gray)
        },
        textStyle = TextStyle(fontSize = 14.sp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = MaterialTheme.colors.secondaryVariant,
            disabledIndicatorColor = MaterialTheme.colors.secondaryVariant,
            unfocusedIndicatorColor = MaterialTheme.colors.secondaryVariant,
            backgroundColor = backgroundColor
        )
    )
}
