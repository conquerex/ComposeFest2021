package what.the.mycodelab.week1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          Greeting("Android")
        }
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
  Surface(color = MaterialTheme.colors.primary) {
    Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
  }
}

/**
 * @Preview 를 통해 다양한 프리뷰를 볼 수 있다.
 */
@Preview(showBackground = true)
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
  Surface(color = MaterialTheme.colors.background) {
    Greeting("Android")
  }
}