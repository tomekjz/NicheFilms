package navigation

import com.arkivanov.decompose.ComponentContext

// These Components can be treated as traditional ViewModels

class BuildingCalculationComponent(
    componentContext: ComponentContext,
    private val onNavigateToScreenB: (String) -> Unit
) : ComponentContext by componentContext {

    fun onEvent(event: BuildingCalculationEvent) {
        when(event){
            BuildingCalculationEvent.ClickNext -> onNavigateToScreenB("")
        }
    }
}