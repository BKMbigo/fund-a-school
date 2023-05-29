import cafe.adriel.voyager.navigator.Navigator
import com.github.bkmbigo.fundaschool.presentation.screens.home.HomeScreen
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("Fund A School") { formFactor ->
            Navigator(
                HomeScreen(
                    formFactor = formFactor
                )
            )
        }
    }
}

