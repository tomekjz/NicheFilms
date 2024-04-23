package presentation.navigation

sealed interface HomeScreenEvent {
    data object ClickNext: HomeScreenEvent
}