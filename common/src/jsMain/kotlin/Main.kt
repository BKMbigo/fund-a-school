import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.di.FirebaseConfig
import com.github.bkmbigo.fundaschool.di.commonModule
import com.github.bkmbigo.fundaschool.di.expectedModule
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.navigation.MainNavigation
import com.github.bkmbigo.fundaschool.presentation.screen.home.HomeScreenAction
import com.github.bkmbigo.fundaschool.presentation.screens.home.HomeScreen
import com.github.bkmbigo.fundaschool.presentation.screens.home.HomeScreenPresenter
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import dev.gitlive.firebase.FirebaseUser
import dev.gitlive.firebase.toJson
import org.jetbrains.skiko.wasm.onWasmReady
import org.koin.core.context.startKoin

fun main() {
    onWasmReady {
        startKoin {
            modules(commonModule)
            modules(expectedModule)
        }

        BrowserViewportWindow("Fund A School") {
            val coroutineScope = rememberCoroutineScope()
            val presenter = remember { HomeScreenPresenter(withKoin(), withKoin(), withKoin(), withKoin(), coroutineScope) }

            val state by presenter.state.collectAsState()

            HomeScreen(
                state = state,
                onAction = { action ->
                    when(action) {
                        HomeScreenAction.NavigateToAboutUs -> {

                        }
                        HomeScreenAction.NavigateToAdmin -> {

                        }
                        HomeScreenAction.NavigateToDonations -> {

                        }
                        is HomeScreenAction.NavigateToNews -> {

                        }
                        is HomeScreenAction.NavigateToProject -> {

                        }
                        HomeScreenAction.NavigateToProjects -> {

                        }
                        is HomeScreenAction.Search -> {

                        }
                    }
                }
            )
        }
    }
}

