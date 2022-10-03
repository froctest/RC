package moder

import com.github.linyuzai.plugin.core.autoload.PluginAutoLoader
import com.github.linyuzai.plugin.core.autoload.PluginLocation
import com.github.linyuzai.plugin.core.autoload.WatchServicePluginAutoLoader
import com.github.linyuzai.plugin.jar.autoload.JarNotifier
import com.github.linyuzai.plugin.jar.concept.JarPluginConcept
import com.github.linyuzai.plugin.jar.extract.ClassExtractor
import moder.register.Registers
import java.io.File
import java.util.concurrent.Executors


object ModLoader {
    /**
     * 插件提取配置
     */
    private val concept = JarPluginConcept.Builder() //添加类提取器
        .addExtractor(object : ClassExtractor<Class<out Mod?>?>() {
            override fun onExtract(modClass: Class<out Mod?>?) {
                if (modClass == null)return
                val mod= modClass.getDeclaredConstructor().newInstance() ?: throw ExceptionInInitializerError("无法初始化Mod: $modClass")
                mod.loaded(Registers)
            }

        })
        .build()
    //自动加载器
    private val loader: PluginAutoLoader = WatchServicePluginAutoLoader.Builder()
        .locations(PluginLocation.Builder() //监听目录
            .path("Mods") //所有jar
            .filter { it: String -> it.endsWith(".jar") }
            .build()) //指定线程池
        .executor(Executors.newSingleThreadExecutor()) //增删改时触发自动加载，自动重新加载，自动卸载
        .onNotify(JarNotifier(concept))
        //异常回调
        .onError { e: Throwable? -> println(e) }
        .build()

    /**
     * 开始监听
     */
    private fun startLoaderListener() {
        loader.start()
    }

//    /**
//     * 结束监听
//     */
//    private fun stopLoaderListener() {
//        loader.stop()
//    }


    private var isInit=false
    internal fun init(){
        if (isInit)return
        //确保Mods文件夹存在
        val modsDir=File("Mods")
        if (!modsDir.exists()){
            modsDir.mkdirs()
        }

        startLoaderListener()
    }
}