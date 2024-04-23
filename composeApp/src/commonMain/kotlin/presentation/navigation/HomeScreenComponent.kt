package presentation.navigation

import com.arkivanov.decompose.ComponentContext

// These Components can be treated as traditional ViewModels

class HomeScreenComponent(
    componentContext: ComponentContext,
    private val onNavigateToMovieCarouselScreen: (String) -> Unit
) : ComponentContext by componentContext {

    fun onEvent(event: HomeScreenEvent) {
        when(event){
            HomeScreenEvent.ClickNext -> onNavigateToMovieCarouselScreen("")
        }
    }
}