package application

import RCClient
import java.util.*

class Run_RCClient private constructor(){

    private var ip="127.0.0.1"
    private var port=18848
    private var rcClient:RCClient = RCClient.Builder().build{}

    init {
        val sca=Scanner(System.`in`)
        while (true){
            val line=sca.nextLine()
            if (line == null || line == "")continue

            rcClient.sendLine(line,flush = true)
        }
    }

    class Builder{
        private val build=Run_RCClient()
        var ip="127.0.0.1"
        var port=18848
        fun build(builder: Builder.() -> Unit) : Run_RCClient{
            this.builder()
            return building()
        }

        private fun building():Run_RCClient{
            build.ip=ip
            build.port=port
            return build
        }
    }
}

//自动创建服务器
fun autoClient() {
    Init.init()
    val run_rcserver=Run_RCClient.Builder().build{}
}
fun autoClientConnect(aip:String="127.0.0.1",aport:Int=18848) {
    Init.init()
    val run_rcserver=Run_RCClient.Builder().build{
        ip=aip
        port=aport
    }
}

//标准服务器
fun standardClient() {
    Init.init()
    val sca=Scanner(System.`in`)
    println("请输入服务器IP")
    val aip=sca.nextLine()
    println("输入服务器端口")
    val aport=sca.nextInt()
    val run_rcclient=Run_RCClient.Builder().build{
        ip=aip
        port=aport
    }
}