import moder.command.Commander
import moder.command.Data
import moder.events.EventCallback
import moder.register.EventRegister
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.InetAddress
import java.net.Socket
import java.net.SocketException
import kotlin.concurrent.thread

class RCSocket private constructor(private val socket: Socket) : RCConnected{
    //给服务器端的构造器
    private lateinit var rcServer: RCServer
    internal constructor(socket: Socket,rcServer: RCServer):this(socket){
        EventRegister.notifyRegistration(EventCallback.RCSocketConnectedClient::class.java){
            connect(this@RCSocket)
        }
        this.rcServer=rcServer
    }
    //给客户端的构造器
    internal constructor(ip:String,port:Int):this(Socket(ip,port)){
        EventRegister.notifyRegistration(EventCallback.RCSocketConnectedServer::class.java){
            connect(this@RCSocket)
        }
    }
    private val input = socket.getInputStream()
    private val output = socket.getOutputStream()

    private val reader = BufferedReader(InputStreamReader(input))
    private val writer = BufferedWriter(OutputStreamWriter(output))
    init {
        EventRegister.notifyRegistration(EventCallback.RCSocketConnected::class.java){
            connect(this@RCSocket)
        }
        thread {
            try {

                while (true){
                    val line=reader.readLine() ?:continue
                    try {
                        Commander.executeCommand(line,writer)
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                }

            }catch (connectionReset:SocketException){
                EventRegister.notifyRegistration(EventCallback.RCSocketCloseConnected::class.java){
                    close(this@RCSocket)
                }
            }
        }
    }

    fun getInetAddress(): InetAddress =socket.inetAddress

    private var sentData:Boolean=false
    //发送一行文字
    override fun sendLine(line:String,flush:Boolean){
        writer.write(line)
        writer.newLine()
        if (flush)writer.flush()
    }
    //发送一条完整的指令(不包括Data)
    override fun send(command: String){
        sendLine(command)
        sendCommandEnd()
    }
    //发送一行指令,后面可以再衔接Data
    override fun sendCommandStart(command: String,data: Data?){
        sendLine(command,flush = true)
        if (data != null)sendData(data)
    }
    //发送最后的数据,指令结束
    override fun sendDataEnd(data: Data){
        sendDataLine(data.getBase64())
        sendCommandEnd()
    }
    //发送数据
    override fun sendData(data: Data){
        sendDataLine(data.getBase64())
    }
    //发送数据行(请保证该数据行已经base64编码过了)
    override fun sendDataLine(data: String){
        if (!sentData)sentData=true
        sendLine(data,flush = true)
    }
    //指令结束
    override fun sendCommandEnd(){
        if (sentData)sendLine("\$#DataEnd")
        sendLine("\$#End",flush = true)
    }
    //冲刷缓存
    override fun flush(){
        writer.flush()
    }

    override fun getHandleId(): String {
        throw IllegalArgumentException("RCSocket没有HandleId")
    }
}