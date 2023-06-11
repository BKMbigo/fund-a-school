import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.softwork.routingcompose.BrowserRouter
import app.softwork.routingcompose.Router
import app.softwork.routingcompose.navigate
import com.github.bkmbigo.fundaschool.di.commonModule
import com.github.bkmbigo.fundaschool.di.expectedModule
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.domain.models.firebase.News
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.NewsRepository
import com.github.bkmbigo.fundaschool.presentation.navigation.LocalDestinations
import com.github.bkmbigo.fundaschool.presentation.screens.admin.AdminScreen
import com.github.bkmbigo.fundaschool.presentation.screens.admin.AdminScreenAction
import com.github.bkmbigo.fundaschool.presentation.screens.admin.AdminScreenPresenter
import com.github.bkmbigo.fundaschool.presentation.screens.admin.SmallAdminScreenContent
import com.github.bkmbigo.fundaschool.presentation.screens.home.HomeScreen
import com.github.bkmbigo.fundaschool.presentation.screens.home.HomeScreenAction
import com.github.bkmbigo.fundaschool.presentation.screens.home.HomeScreenPresenter
import com.github.bkmbigo.fundaschool.presentation.screens.news.NewsScreen
import com.github.bkmbigo.fundaschool.presentation.screens.news.NewsScreenAction
import com.github.bkmbigo.fundaschool.presentation.screens.news.NewsScreenPresenter
import com.github.bkmbigo.fundaschool.presentation.screens.news.SmallNewsScreen
import com.github.bkmbigo.fundaschool.presentation.screens.project.ProjectScreen
import com.github.bkmbigo.fundaschool.presentation.screens.projects.ProjectsScreen
import com.github.bkmbigo.fundaschool.presentation.theme.FundASchoolTheme
import kotlinx.browser.window
import org.jetbrains.skiko.wasm.onWasmReady
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(commonModule)
        modules(expectedModule)
    }

    onWasmReady {
        BrowserViewportWindow("Fund A School") { screenWidth ->
            BrowserRouter(initPath = LocalDestinations.HOME.route) {
                route(LocalDestinations.HOME.route) {
                    val router = Router.current

                    FundASchoolTheme(
                        screenWidth = screenWidth
                    ) {
                        HomeScreen(router = router)
                    }
                }

                route(LocalDestinations.ADMIN.route) {
                    val router = Router.current

                    FundASchoolTheme(
                        screenWidth = screenWidth
                    ) {
                        AdminScreen(router = router)
                    }

                }

                route(LocalDestinations.ABOUT_US.route) {

                }

                route(LocalDestinations.DONATIONS.route) {

                }

                route(LocalDestinations.PROJECTS.route) {
                    val router = Router.current

                    FundASchoolTheme(
                        screenWidth = screenWidth
                    ) {
                        ProjectsScreen(
                            router = router
                        )
                    }
                }

                route(LocalDestinations.NEWS.route) {
                    val router = Router.current
                    val newsId = remember { parameters?.map?.get("newsId")?.toString() }

                    FundASchoolTheme(
                        screenWidth = screenWidth
                    ) {
                        NewsScreen(
                            newsId = newsId,
                            router = router
                        )
                    }
                }


                route(LocalDestinations.PROJECT.route) {
                    val router = Router.current
                    val projectId = parameters?.map?.get("projectId").toString()

                    FundASchoolTheme(
                        screenWidth = screenWidth
                    ) {
                        ProjectScreen(
                            projectId = projectId,
                            router = router
                        )
                    }

                }

                noMatch {
                    FundASchoolTheme(
                        screenWidth = screenWidth
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = "No Matching Page!!!",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}

