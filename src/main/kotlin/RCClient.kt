import moder.Data

class RCClient private constructor(private val rcsocket:RCSocket){

    private var ip="127.0.0.1"
    private var port=18848


    private var sentData:Boolean=false
    //发送一行文字
    fun sendLine(line:String,flush:Boolean=false){
        rcsocket.sendLine(line,flush)
    }
    //发送一条完整的指令(不包括Data)
    fun send(command: String){
        sendLine(command)
        sendCommandEnd()
    }
    //发送一行指令,后面可以再衔接Data
    fun sendCommandStart(command: String,data: Data? = null){
        sendLine(command,flush = true)
        if (data != null)sendData(data)
    }
    //发送最后的数据,指令结束
    fun sendDataEnd(data: Data){
        sendDataLine(data.getBase64())
        sendCommandEnd()
    }
    //发送数据
    fun sendData(data: Data){
        sendDataLine(data.getBase64())
    }
    //发送数据行(请保证该数据行已经base64编码过了)
    fun sendDataLine(data: String){
        if (!sentData)sentData=true
        sendLine(data,flush = true)
    }
    //指令结束
    fun sendCommandEnd(){
        if (sentData)sendLine("\$#DataEnd")
        sendLine("\$#End",flush = true)
    }
    //冲刷缓存
    fun flush(){
        rcsocket.flush()
    }

    init {
        println("连接到服务器")
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