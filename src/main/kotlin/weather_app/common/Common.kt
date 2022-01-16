package weather_app.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun CommonCard(
    color: Color,
    title: String,
    des: String,
    textColor: Color
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = Modifier.background(color)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(title, color = textColor)
                Text(
                    des, color = textColor, fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}