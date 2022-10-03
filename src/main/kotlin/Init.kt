import moder.ModLoader

object Init {
    private var isInit=false
    fun init(){
        if (!isInit)initing()
    }

    private fun initing(){
        ModLoader.init()
    }

}