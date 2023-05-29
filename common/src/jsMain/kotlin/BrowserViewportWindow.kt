@file:Suppress(
    "INVISIBLE_MEMBER",
    "INVISIBLE_REFERENCE",
    "EXPOSED_PARAMETER_TYPE",
)

import androidx.compose.runtime.*
import androidx.compose.ui.window.ComposeWindow
import com.github.bkmbigo.fundaschool.presentation.theme.FundASchoolTheme
import com.github.bkmbigo.fundaschool.presentation.utils.FormFactor
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLStyleElement
import org.w3c.dom.HTMLTitleElement

private const val CANVAS_ELEMENT_ID = "ComposeTarget" // Hardwired into ComposeWindow

/**
 * A Skiko/Canvas-based top-level window using the browser's entire viewport. Supports resizing.
 */
@Suppress("FunctionName")
fun BrowserViewportWindow(
    title: String = "Untitled",
    content: @Composable ComposeWindow.(FormFactor) -> Unit
) {
    val htmlHeadElement = document.head!!
    htmlHeadElement.appendChild(
        (document.createElement("style") as HTMLStyleElement).apply {
            type = "text/css"
            appendChild(
                document.createTextNode(
                    """
                    html, body {
                        overflow: hidden;
                        margin: 0 !important;
                        padding: 0 !important;
                    }
                    #$CANVAS_ELEMENT_ID {
                        outline: none;
                    }
                    """.trimIndent()
                )
            )
        }
    )

    fun HTMLCanvasElement.fillViewportSize() {
        setAttribute("width", "${window.innerWidth}")
        setAttribute("height", "${window.innerHeight}")
    }

    val canvas = (document.getElementById(CANVAS_ELEMENT_ID) as HTMLCanvasElement).apply {
        fillViewportSize()
    }

    ComposeWindow().apply {
//        var formFactor by remember { mutableStateOf(FormFactor.SMALL) }

        fun ComposeWindow.setNewCanvasSize() {
            val scale = layer.layer.contentScale
            val density = window.devicePixelRatio.toFloat()
            canvas.fillViewportSize()
            layer.layer.attachTo(canvas)
            layer.layer.needRedraw()
            val width = (canvas.width / scale * density).toInt()
            val height = (canvas.height / scale * density).toInt()
            layer.setSize(width, height)

            val formFactor = if(width < 1024) FormFactor.SMALL else FormFactor.LARGE

            setContent {
                FundASchoolTheme {
                    content(formFactor)
                }
            }
        }
        window.addEventListener("resize", {
            setNewCanvasSize()
        })

        // WORKAROUND: ComposeWindow does not implement `setTitle(title)`
        val htmlTitleElement = (
                htmlHeadElement.getElementsByTagName("title").item(0)
                    ?: document.createElement("title").also { htmlHeadElement.appendChild(it) }
                ) as HTMLTitleElement
        htmlTitleElement.textContent = title

        setNewCanvasSize()
    }
}
