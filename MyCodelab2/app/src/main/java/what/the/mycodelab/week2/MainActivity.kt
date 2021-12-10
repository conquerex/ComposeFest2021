package what.the.mycodelab.week2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import what.the.mycodelab.week2.ui.theme.MyCodelab2Theme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyCodelab2Theme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
//          PhotographerCard()
//          LayoutsCodelab()
          ScrollingList()
        }
      }
    }
  }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
  Row(
    modifier
      .padding(8.dp)
      .clip(RoundedCornerShape(4.dp))
      .background(MaterialTheme.colors.surface)
      .clickable(onClick = { /* Ignoring onClick */ })
      .padding(16.dp)
  ) {
    Surface(
      modifier = Modifier.size(50.dp),
      shape = CircleShape,
      color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
    ) {
      // Image goes here
    }
    Column(
      modifier = Modifier
        .padding(start = 8.dp)
        .align(Alignment.CenterVertically)
    ) {
      Text("Alfred Sisley", fontWeight = FontWeight.Bold)
      CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text("3 minutes ago", style = MaterialTheme.typography.body2)
      }
    }
  }
}


@Composable
fun LayoutsCodelab() {
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = "LayoutsCodelab")
        },
        actions = {
          IconButton(onClick = { /* doSomething() */ }) {
            Icon(Icons.Filled.Favorite, contentDescription = null)
          }
        }
      )
    }
  ) { innerPadding ->
    BodyContent(
      Modifier
        .padding(innerPadding)
        .padding(8.dp)
    )
  }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
  Column(modifier = modifier.padding(8.dp)) {
    Text(text = "Hi there!")
    Text(text = "Thanks for going through the Layouts codelab")
  }
}

@Composable
fun ImageListItem(index: Int) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Image(
      painter = rememberImagePainter(
        // 에뮬레이터에서는 이미지가 출력 안될 수 있다.
        data = "https://developer.android.com/images/brand/Android_Robot.png"
      ),
      contentDescription = "Android Logo",
      modifier = Modifier.size(50.dp)
    )
    Spacer(Modifier.width(10.dp))
    Text("Item #$index", style = MaterialTheme.typography.subtitle1)
  }
}

@Composable
fun ScrollingList() {
  val listSize = 100
  // We save the scrolling position with this state
  val scrollState = rememberLazyListState()
  // We save the coroutine scope where our animated scroll will be executed
  val coroutineScope = rememberCoroutineScope()

  Column {
    Row {
      Button(onClick = {
        coroutineScope.launch {
          // 0 is the first item index
          scrollState.animateScrollToItem(0)
        }
      }) {
        Text("Scroll to the top")
      }

      Button(onClick = {
        coroutineScope.launch {
          // listSize - 1 is the last index of the list
          scrollState.animateScrollToItem(listSize - 1)
        }
      }) {
        Text("Scroll to the end")
      }
    }

    LazyColumn(state = scrollState) {
      items(listSize) {
        ImageListItem(it)
      }
    }
  }
}

@Preview
@Composable
fun PhotographerCardPreview() {
  MyCodelab2Theme {
//    PhotographerCard()
//    LayoutsCodelab()
    ScrollingList()
  }
}