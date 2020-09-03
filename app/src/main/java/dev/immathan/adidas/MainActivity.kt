package dev.immathan.adidas

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.drawerlayout.widget.DrawerLayout
import androidx.ui.tooling.preview.Preview
import dev.immathan.adidas.ui.AdidasshoeTheme
import dev.immathan.adidas.ui.typography

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val displayMetrics = DisplayMetrics()
            (ContextAmbient.current as Activity)
                .windowManager
                .defaultDisplay
                .getMetrics(displayMetrics)
            val widthPx = displayMetrics.widthPixels.toFloat()
            val heightPx = displayMetrics.heightPixels.toFloat()
            AdidasshoeTheme(darkTheme = false) {
                App(heightPx, widthPx)
            }
        }
    }
}

@Composable
fun BottomButton(isRegistered: Boolean) {
    Button(onClick = {}) {
        Text(if(isRegistered) "Sign In" else "Sign Up")
    }
}

@Composable
fun App(height: Float, width: Float) {
    ScrollableColumn(
        modifier = Modifier.background(
            brush = LinearGradient(
                listOf(Color(0xFF696D77), Color(0xFF292C36)),
                startX = width / 2,
                startY = 0.0f,
                endX = width / 2,
                endY = height,
                tileMode = TileMode.Clamp
            )
        ).fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        TopBar()
        Body()
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.height(52.dp).fillMaxWidth(),
        verticalGravity = Alignment.CenterVertically,
    ) {
        IconButton(onClick = {}) {
            Icon(
                asset = Icons.Default.ArrowBack, tint = MaterialTheme.colors.onPrimary,
            )
        }
        Text(
            "Energy Cloud", color = MaterialTheme.colors.onPrimary,
            style = typography.h6,
            modifier = Modifier.weight(0.8f),
            textAlign = TextAlign.Center
        )
        IconButton(onClick = {}) {
            Icon(
                asset = Icons.Default.Share,
                tint = MaterialTheme.colors.onPrimary,
            )
        }
    }
}

@Composable
fun Body() {
    Spacer(modifier = Modifier.preferredHeight(16.dp))
    Showcase()
    Rating()
    Spacer(modifier = Modifier.preferredHeight(16.dp))
    Description()
    Spacer(modifier = Modifier.preferredHeight(16.dp))
    Size()
    Spacer(modifier = Modifier.preferredHeight(16.dp))
    ColorSelector()
    Spacer(modifier = Modifier.preferredHeight(16.dp))
    Price()
    Spacer(modifier = Modifier.preferredHeight(16.dp))
    AddToCard()
}

@Composable
fun Showcase() {
    Image(
        asset = imageResource(id = R.drawable.adidas),
        modifier = Modifier.height(300.dp)
            .fillMaxWidth()
            .background(Color.Transparent),
        contentScale = ContentScale.FillHeight,
    )
}

@Composable
fun Rating() {
    HeadLine("Rating")
    Spacer(modifier = Modifier.preferredHeight(16.dp))
    Row(verticalGravity = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.preferredWidth(16.dp))
        Icon(
            asset = Icons.Default.Star,
            tint = Color(0xFFFFE600),
        )
        Text(" 4.5", color = Color(0xFFFFE600))
    }
}

@Composable
fun Description() {
    HeadLine("Product Description")
    Spacer(modifier = Modifier.preferredHeight(16.dp))
    Row(verticalGravity = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.preferredWidth(16.dp))
        Text(
            "Get maximum support, comfort and a refreshed look",
            color = MaterialTheme.colors.onPrimary,
            style = typography.body2
        )
    }
}

@Composable
fun Size() {
    val selectedIndex = remember { mutableStateOf(0) }
    HeadLine("Size")
    Spacer(modifier = Modifier.preferredHeight(16.dp))
    ScrollableRow {
        Spacer(modifier = Modifier.preferredWidth(16.dp))
        sizeList.forEachIndexed { index, size ->
            val isSelected = selectedIndex.value == index
            Card(
                modifier = Modifier.size(32.dp).clickable(onClick = {
                    selectedIndex.value = index
                }),
                backgroundColor = if (isSelected) Color(0xFFFC3930) else Color(0xFF525663),
                elevation = if (isSelected) 4.dp else 0.dp
            ) {
                Box(gravity = ContentGravity.Center) {
                    Text(size.toString(), textAlign = TextAlign.Center, color = MaterialTheme.colors.onPrimary)
                }
            }
            Spacer(modifier = Modifier.preferredWidth(8.dp))
        }
    }
}

@Composable
fun ColorSelector() {
    val selectedIndex = remember { mutableStateOf(0) }
    HeadLine(text = "Select Color")
    Spacer(modifier = Modifier.preferredHeight(16.dp))
    ScrollableRow {
        Spacer(modifier = Modifier.preferredWidth(16.dp))
        colors.forEachIndexed { index, color ->
            val isSelected = selectedIndex.value == index
            Card(
                modifier = Modifier
                    .size(32.dp).clickable(onClick = {
                        selectedIndex.value = index
                    }),
                elevation = if (isSelected) 10.dp else 0.dp,
                backgroundColor = color,
                border = if (isSelected) BorderStroke(2.dp, Color.LightGray) else BorderStroke(0.dp, Color.Transparent),
            ) {

            }
            Spacer(modifier = Modifier.preferredWidth(8.dp))
        }
    }
}

@Composable
fun Price() {
    HeadLine(text = "Price")
    Text(
        "$80",
        style = typography.h3,
        color = Color.White,
        modifier = Modifier.padding(start = 16.dp)
    )
}

@Composable
fun HeadLine(text: String) {
    Text(
        text,
        color = Color(0xFF949598),
        style = typography.body1
    )
}

@Composable
fun AddToCard() {
    Button(
        onClick = {},
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color(0xFFF9362E)
    ) {
        Text(
            "Add To Cart",
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center,
            style = typography.body2,
            color = Color.White
        )
    }
}

val sizeList = (7..10).toList()

val colors = listOf(
    Color(0xFFF9362E),
    Color(0xFF003CFF),
    Color(0xFFFFB73A),
    Color(0xFF3AFFFF),
    Color(0xFF1AD12C),
    Color(0xFFD66400),
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AdidasshoeTheme(darkTheme = false) {
        App(1080f, 680f)
    }
}