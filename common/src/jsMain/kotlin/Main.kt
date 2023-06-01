import com.github.bkmbigo.fundaschool.presentation.navigation.MainNavigation
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("Fund A School") {
            MainNavigation()
        }
    }
}

