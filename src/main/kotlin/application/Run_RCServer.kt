package application

import RCServer
import java.util.*

class Run_RCServer private constructor(){

    private var aPort=18848
    private var rcServer:RCServer = RCServer.Builder().build { port=aPort}
//    val rcSocket=RCSocket("127.0.0.1",aPort)
//
//    init {
//        val sca=Scanner(System.`in`)
//        while (true){
//            val line=sca.nextLine()
//            if (line == null || line == "")continue
//
//            rcSocket.sendLine(line,flush = true)
//        }
//    }
    init {
        autoClientConnect()
    }

    fun getServer():RCServer=rcServer

    class Builder{
        private val build=Run_RCServer()
        var port=18848
        fun build(builder: Builder.() -> Unit) : Run_RCServer{
            this.builder()
            return building()
        }

        private fun building():Run_RCServer{
            build.aPort=port
            return build
        }
    }
}

//自动创建服务器
fun autoServer() {
    val run_rcserver=Run_RCServer.Builder().build{}
    println("IP: ${run_rcserver.getServer().getIP()}")
    println("Port: ${run_rcserver.getServer().getPort()}")
}

//标准服务器
fun standardServer() {
    val sca=Scanner(System.`in`)
    println("输入服务器要开启的端口")
    val aport=sca.nextInt()
    val run_rcserver=Run_RCServer.Builder().build{
        port=aport
    }
    println("IP: ${run_rcserver.getServer().getIP()}")
    println("Port: ${run_rcserver.getServer().getPort()}")
}