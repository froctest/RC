import moder.ModLoader

object Init {
    private var isInit=false
    fun init(){
        if (!isInit)initing()
    }
    //内部初始化
    private fun initing(){
        //初始化ModLoader
        ModLoader.init()
    }

}