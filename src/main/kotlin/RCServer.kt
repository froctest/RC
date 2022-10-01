import java.net.ServerSocket
import kotlin.concurrent.thread

class RCServer private constructor(){


    private var port: Int=18848
    private lateinit var server: ServerSocket
    private var isStop = false
    private fun start():RCServer{
        server=ServerSocket(port)

        thread {
            while (!isStop){
                val socket=server.accept()
                println("用户连接到服务器: ${socket.inetAddress.hostAddress}")
                thread {
                    RCSocket(socket,this)
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


    class Builder(){

        var port=18848

        fun build(builder: Builder.() -> Unit) : RCServer{
            this.builder()
            return building()
        }

        private fun building():RCServer{
            val build=RCServer()
            build.port=port
            return build.start()
        }

    }
}