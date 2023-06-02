import com.github.bkmbigo.fundaschool.di.commonModule
import com.github.bkmbigo.fundaschool.di.expectedModule
import com.github.bkmbigo.fundaschool.presentation.navigation.MainNavigation
import org.jetbrains.skiko.wasm.onWasmReady
import org.koin.core.context.startKoin

fun main() {
    onWasmReady {

        startKoin {
            modules(commonModule)
            modules(expectedModule)
        }

        BrowserViewportWindow("Fund A School") {
            MainNavigation()
        }
    }
}

