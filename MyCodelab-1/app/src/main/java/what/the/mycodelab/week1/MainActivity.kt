package what.the.mycodelab.week1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import what.the.mycodelab.week1.ui.theme.MyCodelabTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // setContent 안에 xml 대신 레이아웃을 구성할 Composable 함수가 들어갈 수 있다
    setContent {
      MyCodelabTheme {
//        Surface(color = MaterialTheme.colors.background) {
//          Greeting("Android")
//        }
        MyApp()
      }
    }
  }
}

/**
 * Composable function 에는 TextView 대신 사용할 수 있는
 * Text 같은 Composavle function들을 넣을 수 있다
 * .
 * 4. Tweaking the UI
 * - Surface
 * - Modifier
 */
@Composable
private fun Greeting(name: String) {

  var expanded by remember { mutableStateOf(false) }

  val extraPadding by animateDpAsState(
    if (expanded) 48.dp else 0.dp,
    animationSpec = spring(
      dampingRatio = Spring.DampingRatioMediumBouncy,
      stiffness = Spring.StiffnessLow
    )
  )

  Surface(
    color = MaterialTheme.colors.primary,
    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
  ) {
    Row(modifier = Modifier.padding(24.dp)) {
      Column(
        modifier = Modifier
          .weight(1f)
          .padding(bottom = extraPadding.coerceAtLeast(0.dp))
      ) {
        Text(text = "Hello, ")
        Text(text = name)
      }
      OutlinedButton(
        onClick = { expanded = !expanded }
      ) {
        Text(if (expanded) "Show less" else "Show more")
      }

    }
  }
}


/**
 * @Preview 를 통해 다양한 프리뷰를 볼 수 있다.
 */
@Preview(showBackground = true, widthDp = 320)
@Composable
private fun DefaultPreview() {
  MyCodelabTheme {
    MyApp()
  }
}

/**
 * 5. Reusing composables
 * - @Composable 어노테이션을 추가하여 함수를 분리하거나 독립적으로 편집이 가능
 * - 마치 xml 에서 <include> 를 사용했던 것처럼
 */
@Composable
private fun MyApp() {
//  Surface(color = MaterialTheme.colors.background) {
//    Greeting("Android")
//  }
//  Column(modifier = Modifier.padding(vertical = 4.dp)) {
//    for (name in names) {
//      Greeting(name = name)
//    }
//  }
  var shouldShowOnboarding by remember { mutableStateOf(true) }

  if (shouldShowOnboarding) {
    OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
  } else {
    Greetings()
  }
}

@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
//  Column(modifier = Modifier.padding(vertical = 4.dp)) {
//    for (name in names) {
//      Greeting(name = name)
//    }
//  }
  LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
    items(items = names) { name ->
      Greeting(name = name)
    }
  }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
//  var shouldShowOnboarding by remember { mutableStateOf(true) }
  Surface {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text("Welcome to the Basics Codelab!")
      Button(
        modifier = Modifier
          .padding(vertical = 24.dp),
        onClick = onContinueClicked
      ) {
        Text("Continue")
      }
    }
  }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
  MyCodelabTheme {
    OnboardingScreen(onContinueClicked = {}) // Do nothing on click.
  }
}