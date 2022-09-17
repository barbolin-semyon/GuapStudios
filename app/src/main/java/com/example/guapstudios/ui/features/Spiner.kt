package com.example.doctors.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.guapstudios.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.guapstudios.data.emptities.Studio

@Composable
fun StudiousSpinner(
    items: List<Studio>,
    hint: String = items[0].title,
    tint: Color = Color.White,
    padding: PaddingValues = PaddingValues(),
    onClick: (text: Studio) -> Unit,
) {

    val isExpand = remember { mutableStateOf(false)}
    val label = remember { mutableStateOf(hint)}

    Box(contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .clickable { isExpand.value = isExpand.value.not() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = label.value,
                fontWeight = FontWeight.Bold,
                //color = tint
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_drop_down),
                contentDescription = "to expand",
                Modifier
                    .height(25.dp)
                    .width(25.dp),
                tint = tint
            )
        }

        DropdownMenu(
            expanded = isExpand.value,
            onDismissRequest = { isExpand.value = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            for (item in items) {
                DropdownMenuItem(onClick = {
                    onClick(item)
                    label.value = item.title
                    isExpand.value = false
                }) {
                    Text(text = item.title)
                }
            }
        }
    }
}