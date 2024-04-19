import androidx.compose.runtime.*
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import navigation.RootComponent
import org.jetbrains.compose.ui.tooling.preview.Preview
import screens.HomeScreen
import screens.ScreenB

@Composable
@Preview
fun App(root: RootComponent) {
    AppTheme {
        val childStack by root.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ){ child ->
            when(val instance = child.instance) {
                is RootComponent.Child.BuildingCalculation -> HomeScreen(instance.component)
                is RootComponent.Child.ScreenB -> ScreenB(instance.component)
            }

        }
    }
}