import moder.Mod
import pluginer.EventRegister
import pluginer.Plugin
import java.util.*

object Init {
    private var isInit=false
    fun init(){
        if (!isInit)initing()
    }

    private fun initing(){
        PluginLoader.init()
    }

    object ModLoader{
        val mods by lazy {
            ServiceLoader.load(Mod::class.java)
        }
    }

    object PluginLoader{
        val plugins by lazy {
            ServiceLoader.load(Plugin::class.java)
        }

        private var isInit=false
        internal fun init(){
            if (isInit)return
            for (plugin in plugins){
                plugin.loaded(EventRegister)
            }
        }
    }

}