package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun rallyTopAppBarTest() {
    val allScreens = RallyScreen.values().toList()
    composeTestRule.setContent {
      RallyTopAppBar(
        allScreens = allScreens,
        onTabSelected = { },
        currentScreen = RallyScreen.Accounts
      )
    }
    Thread.sleep(5000)
  }

  @Test
  fun rallyTopAppBarTest_currentTabSelected() {
    val allScreens = RallyScreen.values().toList()
    composeTestRule.setContent {
      RallyTopAppBar(
        allScreens = allScreens,
        onTabSelected = { },
        currentScreen = RallyScreen.Accounts
      )
    }

    composeTestRule.onRoot().printToLog("currentLabelExists")

    composeTestRule
      .onNodeWithContentDescription(RallyScreen.Accounts.name)
      .assertIsSelected()
  }

  /**
   * 트리 로그를 볼 수 없음
   * 이유를 알 수 없음 (엉엉 ㅠㅠ)
   */
  @Test
  fun rallyTopAppBarTest_currentLabelExists() {
    val allScreens = RallyScreen.values().toList()
    composeTestRule.setContent {
      RallyTopAppBar(
        allScreens = allScreens,
        onTabSelected = { },
        currentScreen = RallyScreen.Accounts
      )
    }

//    composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

    composeTestRule
      .onNode(
        hasText(RallyScreen.Accounts.name.uppercase()) and
          hasParent(
            hasContentDescription(RallyScreen.Accounts.name)
          ),
        useUnmergedTree = true
      )
      .assertExists()
  }
}