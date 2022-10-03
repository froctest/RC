import moder.Mod
import moder.register.Registers
import java.util.*

object Init {
    private var isInit=false
    fun init(){
        if (!isInit)initing()
    }

    private fun initing(){
        ModLoader.init()
    }

    object ModLoader{
        private val mods by lazy {
            ServiceLoader.load(Mod::class.java)
        }
        private var isInit=false
        internal fun init(){
            if (isInit)return
            for (mod in mods){
                mod.loaded(Registers)
            }
        }
    }
}