import moder.command.Data

interface RCConnected {
    //发送一行文字
    fun sendLine(line:String,flush:Boolean=false)
    //发送一条完整的指令(不包括Data)
    fun send(command: String)
    //发送一行指令,后面可以再衔接Data
    fun sendCommandStart(command: String,data: Data? = null)
    //发送最后的数据,指令结束
    fun sendDataEnd(data: Data)
    //发送数据
    fun sendData(data: Data)
    //发送数据行(请保证该数据行已经base64编码过了)
    fun sendDataLine(data: String)
    //指令结束
    fun sendCommandEnd()
    //冲刷缓存
    fun flush()
}