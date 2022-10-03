import moder.command.Data
import moder.register.EventRegister
import moder.events.EventCallback

class RCClient internal constructor(private val rcsocket:RCSocket){

    private var ip="127.0.0.1"
    private var port=18848


    private var sentData:Boolean=false
    //发送一行文字
    fun sendLine(line:String,flush:Boolean=false){
        rcsocket.sendLine(line,flush)
    }
    //发送一条完整的指令(不包括Data)
    fun send(command: String){
        rcsocket.send(command)
    }
    //发送一行指令,后面可以再衔接Data
    fun sendCommandStart(command: String,data: Data? = null){
        rcsocket.sendCommandStart(command,data)
    }
    //发送最后的数据,指令结束
    fun sendDataEnd(data: Data){
        rcsocket.sendDataEnd(data)
    }
    //发送数据
    fun sendData(data: Data){
        rcsocket.sendData(data)
    }
    //发送数据行(请保证该数据行已经base64编码过了)
    fun sendDataLine(data: String){
        rcsocket.sendDataLine(data)
    }
    //指令结束
    fun sendCommandEnd(){
        rcsocket.sendCommandEnd()
    }
    //冲刷缓存
    fun flush(){
        rcsocket.flush()
    }

    init {
        EventRegister.notifyRegistration(EventCallback.ClientConnected::class.java){
            connected(this@RCClient)
        }
    }

    class Builder{
        var ip:String="127.0.0.1"
        var port:Int=18848
        fun build(buildFun:Builder.() -> Unit):RCClient{
            this.buildFun()
            return building()
        }

        private fun building():RCClient{
            val rcsocket=RCSocket(ip,port)
            val build=RCClient(rcsocket)
            build.ip=ip
            build.port=port
            return build
        }
    }
}