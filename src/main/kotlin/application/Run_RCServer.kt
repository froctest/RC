package application

import Init
import RCServer
import java.util.*

class Run_RCServer private constructor(val aPort:Int){
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

    fun getServer():RCServer=rcServer

    class Builder{
        var port=18848
        fun build(builder: Builder.() -> Unit) : Run_RCServer{
            this.builder()
            return building()
        }

        private fun building(): Run_RCServer {
            return Run_RCServer(port)
        }
    }
}

//自动创建服务器
fun autoServer() {
    Init.init()
    val run_rcserver=Run_RCServer.Builder().build{}
    println("IP: ${run_rcserver.getServer().getIP()}")
    println("Port: ${run_rcserver.getServer().getPort()}")
}

//标准服务器
fun standardServer() {
    Init.init()
    val sca=Scanner(System.`in`)
    println("输入服务器要开启的端口")
    val aport=sca.nextInt()
    val run_rcserver=Run_RCServer.Builder().build{
        port=aport
    }
    println("IP: ${run_rcserver.getServer().getIP()}")
    println("Port: ${run_rcserver.getServer().getPort()}")
}