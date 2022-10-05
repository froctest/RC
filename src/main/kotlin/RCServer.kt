import moder.events.EventCallback
import moder.register.EventRegister
import java.net.ServerSocket
import kotlin.concurrent.thread

class RCServer private constructor(private val msgListener: MsgListener){


    private var port: Int=18848
    private lateinit var server: ServerSocket
    private var isStop = false
    private fun start():RCServer{
        server=ServerSocket(port)
        //回调插件监听(服务器被创建)
        EventRegister.notifyRegistration(EventCallback.ServerCreated::class.java){
            created(this@RCServer)
        }

        thread {
            while (!isStop){
                val socket=server.accept()
                thread {
                    val rcsocket=RCSocket(socket,this, msgListener)
                    //回调插件监听(用户连接)
                    EventRegister.notifyRegistration(EventCallback.ServerConnectedUser::class.java){
                        connected(this@RCServer,rcsocket)
                    }
                }
            }
        }

        return this
    }

    fun stop(){
        isStop = true
    }

    fun getIP()=InternetUtils.localIPAddress
    fun getPort()=port


    class Builder {

        var port=18848

        fun build(msgListener: MsgListener,builder: Builder.() -> Unit) : RCServer{
            this.builder()
            return building(msgListener)
        }

        private fun building(msgListener: MsgListener):RCServer{
            val build=RCServer(msgListener)
            build.port=port
            return build.start()
        }

    }
}