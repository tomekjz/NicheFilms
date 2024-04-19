package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()
    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.BuildingCalucation,
        handleBackButton = true,
        childFactory = ::createChild
    )

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ): Child {
        return when (config) {
            Configuration.BuildingCalucation -> Child.BuildingCalculation(
                BuildingCalculationComponent(
                    componentContext = context,
                    onNavigateToScreenB = { _ ->
                        navigation.pushNew(Configuration.ScreenB)
                    }
                )
            )

            Configuration.ScreenB -> Child.ScreenB(
                ScreenBComponenet(componentContext = context)
            )
        }
    }

    sealed class Child {
        data class BuildingCalculation(val component: BuildingCalculationComponent) : Child()
        data class ScreenB(val component: ScreenBComponenet) : Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object BuildingCalucation : Configuration()

        @Serializable
        data object ScreenB : Configuration()
    }
}