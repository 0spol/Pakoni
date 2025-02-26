package com.pakoni.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.pakoni.model.data.CharacterModel
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun HomeScreen(rickListViewModel: HomeViewModel = hiltViewModel()) {
    var activeTab by remember { mutableStateOf("home") }
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (activeTab) {
                            "home" -> "Rick and Morty"
                            "saved" -> "Guardados"
                            "settings" -> "Configuración"
                            else -> ""
                        },
                        color = Color.White
                    )
                },
                backgroundColor = Color(0xFF262C3A),
                modifier = Modifier
                    .background(Color(0xFF262C3A))
                    .windowInsetsPadding(WindowInsets.statusBars)
            )
        },
        bottomBar = {
            BottomNavigationBar(activeTab) { activeTab = it }
        },
        modifier = Modifier
            .background(Color(0xFF202530))
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.navigationBars) // Add padding for the navigation bar
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (activeTab) {
                "home" -> CharacterListScreen(rickListViewModel, listState)
                "saved" -> PlaceholderScreen(
                    Icons.Filled.Bookmark,
                    "Favoritos guardados",
                    "Aquí verás los personajes que guardes como favoritos"
                )

                "settings" -> PlaceholderScreen(
                    Icons.Filled.Settings,
                    "Configuración",
                    "Aquí podrás ajustar las preferencias de la aplicación"
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(activeTab: String, onTabSelected: (String) -> Unit) {
    BottomNavigation(backgroundColor = Color(0xFF2C3444)) {
        listOf(
            "saved" to Icons.Filled.Bookmark,
            "home" to Icons.Filled.Home,
            "settings" to Icons.Filled.Settings
        ).forEach { (tab, icon) ->
            BottomNavigationItem(
                selected = activeTab == tab,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        icon,
                        contentDescription = tab,
                        tint = if (activeTab == tab) Color(0xFF40B1D8) else Color.Gray
                    )
                },
                label = { Text(tab.replaceFirstChar { it.uppercase() }) },
                selectedContentColor = Color(0xFF40B1D8),
                unselectedContentColor = Color.Gray
            )
        }
    }
}

@Composable
fun CharacterListScreen(rickListViewModel: HomeViewModel, listState: LazyListState) {
    val characters = rickListViewModel.characters.collectAsLazyPagingItems()
    val loadState = characters.loadState.mediator

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF202530)),
        state = listState
    ) {
        when {
            loadState?.refresh is LoadState.Loading -> {
                item { LoadingIndicator(Modifier.fillParentMaxSize()) }
            }

            loadState?.append is LoadState.Loading -> {
                item { LoadingIndicator(Modifier.fillMaxWidth()) }
            }

            loadState?.refresh is LoadState.Error -> {
                item { ErrorScreen(Modifier.fillParentMaxSize()) }
            }

            loadState?.append is LoadState.Error -> {
                item { ErrorScreen(Modifier.fillMaxWidth()) }
            }
            characters.itemCount == 0 -> {
                item { EmptyListScreen(Modifier.fillParentMaxSize()) }
            }

            else -> {
                items(count = characters.itemCount) { index ->
                    characters[index]?.let { CharacterItem(it) }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: CharacterModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFF2C3444), MaterialTheme.shapes.medium)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "Character Image",
            modifier = Modifier
                .size(80.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 12.dp,
                        bottomStart = 12.dp
                    )
                ),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                character.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(getStatusColor(character.status))
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    "${character.status} - ${character.species}",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun PlaceholderScreen(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF202530)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = Color(0xFF40B1D8),
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(title, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(10.dp))
        Text(subtitle, fontSize = 16.sp, color = Color.Gray, textAlign = TextAlign.Center)
    }
}

fun getStatusColor(status: String): Color {
    return when (status) {
        "Alive" -> Color(0xFF55CC44)
        "Dead" -> Color(0xFFD63D2E)
        else -> Color.Gray
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
        modifier = modifier.fillMaxSize().background(Color.Red),
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