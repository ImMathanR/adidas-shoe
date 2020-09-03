package dev.immathan.adidas

import androidx.compose.foundation.Icon
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun ColorSelector2(color: Color = Color.Red){
    val size = 32.dp.toPx()
    val icon = materialIcon {
        materialPath {
            lineTo(0f, size)
            lineTo(size * 0.2f, size)
            lineTo(size, size * 0.2f)
            lineTo(size, 0f)
            close()
        }
    }
    Icon(asset = icon, modifier = Modifier.size(32.dp))
}

@Composable
fun Dp.toPx(): Float = with(DensityAmbient.current) {
    return@toPx this@toPx.toPx()
}

val Icons.Filled.ArrowBack: VectorAsset
    get() {
        if (icon != null) return icon!!
        icon = materialIcon {
            materialPath {
                moveTo(20.0f, 11.0f)
                horizontalLineTo(7.83f)
                lineToRelative(5.59f, -5.59f)
                lineTo(12.0f, 4.0f)
                lineToRelative(-8.0f, 8.0f)
                lineToRelative(8.0f, 8.0f)
                lineToRelative(1.41f, -1.41f)
                lineTo(7.83f, 13.0f)
                horizontalLineTo(20.0f)
                verticalLineToRelative(-2.0f)
                close()
            }
        }
        return icon!!
    }

private var icon: VectorAsset? = null
