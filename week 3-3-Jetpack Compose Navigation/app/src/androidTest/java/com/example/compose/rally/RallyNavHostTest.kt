package com.example.compose.rally

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RallyNavHostTest {

  /**
   * [ 테스트 불가 상황 ]
   *
   * 1.
   * Manifest merger failed with multiple errors, see logs
   * -> Manifest 파일의 Merged Manifest탭에서 에러 발견할 수 없음
   *
   * 2.
   * /Users/barley.son/dev/ComposeFest2021/week 3-3-Jetpack Compose Navigation/app/build/intermediates/tmp/manifest/androidTest/debug/tempFile1ProcessTestManifest8442774342099303899.xml Error:
   * android:exported needs to be explicitly specified for <activity>. Apps targeting Android 12 and higher are required to specify an explicit value for `android:exported` when the corresponding component has an intent filter defined. See https://developer.android.com/guide/topics/manifest/activity-element#exporte
   * -> 이미 android:exported="true"로 되어 있는 상황
   *
   * 결과 : 테스트 불가
   */


  @get:Rule
  val composeTestRule = createComposeRule()
  lateinit var navController: NavHostController

  @Before
  fun setupRallyNavHost() {
    composeTestRule.setContent {
      navController = rememberNavController()
      RallyNavHost(navController = navController)
    }
  }

  @Test
  fun rallyNavHost() {
    composeTestRule
      .onNodeWithContentDescription("Overview Screen")
      .assertIsDisplayed()
  }


  @Test
  fun rallyNavHost_navigateToAllAccounts_viaUI() {
    composeTestRule
      .onNodeWithContentDescription("All Accounts")
      .performClick()
    composeTestRule
      .onNodeWithContentDescription("Accounts Screen")
      .assertIsDisplayed()
  }


  @Test
  fun rallyNavHost_navigateToBills_viaUI() {
    // When click on "All Bills"
    composeTestRule.onNodeWithContentDescription("All Bills").apply {
      performScrollTo()
      performClick()
    }
    // Then the route is "Bills"
    val route = navController.currentBackStackEntry?.destination?.route
    assertEquals(route, "Bills")
  }

  @Test
  fun rallyNavHost_navigateToAllAccounts_callingNavigate() {
    runBlocking {
      withContext(Dispatchers.Main) {
        navController.navigate(RallyScreen.Accounts.name)
      }
    }
    composeTestRule
      .onNodeWithContentDescription("Accounts Screen")
      .assertIsDisplayed()
  }

}