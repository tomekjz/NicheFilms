package navigation

sealed interface BuildingCalculationEvent {
    data object ClickNext: BuildingCalculationEvent
}