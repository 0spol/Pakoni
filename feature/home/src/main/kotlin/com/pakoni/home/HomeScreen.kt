package com.pakoni.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.pakoni.model.data.CharacterModel


@Composable
fun HomeScreen(rickListViewModel: HomeViewModel = hiltViewModel()) {
    val characters = rickListViewModel.characters.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.fillMaxSize()) {
            when (characters.loadState.refresh) {
                is LoadState.Loading -> {
                    LoadingIndicator(modifier = Modifier.fillMaxSize())
                }

                is LoadState.Error -> {
                    ErrorScreen(modifier = Modifier.fillMaxSize())
                }

                else -> {
                    if (characters.itemCount == 0) {
                        EmptyListScreen(modifier = Modifier.fillMaxSize())
                    } else {
                        CharactersList(characters)
                    }
                }
            }
            if (characters.loadState.append is LoadState.Loading) {
                LoadingIndicator(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun CharactersList(characters: LazyPagingItems<CharacterModel>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            count = characters.itemCount,
            key = characters.itemKey { it.id }
        ) { index ->
            characters[index]?.let { characterModel ->
                ItemList(characterModel)
            }
        }
    }
}

@Composable
fun ItemList(characterModel: CharacterModel) {
    Box(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(2.dp, Color.Green, shape = RoundedCornerShape(0.dp, 24.dp, 0.dp, 24.dp))
            .fillMaxWidth()
            .height(250.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = characterModel.image,
            contentDescription = "character image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0f),
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 1f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = characterModel.name,
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            color = Color.White
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Ha ocurrido un error", color = Color.White)
    }
}

@Composable
fun EmptyListScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = "Todavía no hay personajes", color = Color.White)
    }
}






