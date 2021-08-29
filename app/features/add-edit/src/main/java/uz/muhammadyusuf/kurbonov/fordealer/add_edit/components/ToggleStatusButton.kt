package uz.muhammadyusuf.kurbonov.fordealer.add_edit.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

@Composable
fun ToggleStatusButton(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit = {},
    caption: String,
    activated: Boolean,
    onActivate: () -> Unit
){
    Button(
        modifier = modifier.apply {
                                  if (activated)
                                      scale(1f, 2f)
        },
        elevation = ButtonDefaults.elevation(
            if (activated)
                0.dp
        else 2.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (activated) MaterialTheme.colors.secondary.copy(0.3f)
                                else MaterialTheme.colors.primary
        ),
        onClick = onActivate
    ) {
        Row {
            icon()
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = caption,
                style = MaterialTheme.typography.button
            )
        }
    }
}