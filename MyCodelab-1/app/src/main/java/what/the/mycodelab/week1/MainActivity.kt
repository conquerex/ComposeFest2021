package what.the.mycodelab.week1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
 */
@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

/**
 * @Preview 를 통해 다양한 프리뷰를 볼 수 있다.
 */
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MyCodelabTheme {
    Greeting("Android")
  }
}