package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme (darkTheme = false, dynamicColor = false){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GalleryLayout()
                }
            }
        }
    }
}

@Composable
fun GalleryLayout(modifier: Modifier = Modifier) {

    val imageIdList: List<Int> = listOf(
        R.drawable.artwork_1,
        R.drawable.artwork_2,
        R.drawable.artwork_3,
        R.drawable.artwork_4,
        R.drawable.artwork_5
    )
    val titleIdList: List<Int> = listOf(
        R.string.artwork_1_title,
        R.string.artwork_2_title,
        R.string.artwork_3_title,
        R.string.artwork_4_title,
        R.string.artwork_5_title
    )
    val artistIdList: List<Int> = listOf(
        R.string.artwork_1_artist,
        R.string.artwork_2_artist,
        R.string.artwork_3_artist,
        R.string.artwork_4_artist,
        R.string.artwork_5_artist
    )
    var id by remember { mutableStateOf(0) }
    val imageId = imageIdList[id]
    val titleId = titleIdList[id]
    val artistId = artistIdList[id]
    Column (
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .verticalScroll(rememberScrollState())
    ){
        Spacer(modifier = Modifier.height(8.dp))
        ArtWork(imageId, titleId, artistId, Modifier.wrapContentWidth(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(8.dp))
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ){
            Button(onClick = { id = (id-1+imageIdList.size) % imageIdList.size }) {
                Text(text = "Previous")
            }
            Button(onClick = { id = (id+1) % imageIdList.size }) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GalleryPreview() {
    ArtSpaceTheme {
        GalleryLayout()
    }
}

@Composable
fun ArtWork(
    @DrawableRes imageId: Int,
    @StringRes titleId: Int,
    @StringRes artistId: Int,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ){
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "1",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .border(border = BorderStroke(24.dp, color = Color.White))
                .shadow(elevation = 8.dp)
                .padding(24.dp)
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(16.dp))
        Column (
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFecebf4)
                )
//                .padding(8.dp)
        ){
            Text(
                text = stringResource(titleId),
                fontWeight = FontWeight.Bold, fontSize = 24.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = stringResource(artistId),
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }

}