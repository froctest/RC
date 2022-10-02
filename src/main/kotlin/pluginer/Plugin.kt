package pluginer

interface Plugin {
    fun loaded(eventRegistry:EventRegister)
}