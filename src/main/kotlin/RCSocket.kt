import moder.Command
import moder.Data
import moder.Invoker
import moder.Moder
import moder.Result
import pluginer.EventRegister
import pluginer.events.EventCallback
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.Writer
import java.net.InetAddress
import java.net.Socket
import java.net.SocketException
import kotlin.concurrent.thread

class RCSocket private constructor(private val socket: Socket){
    //给服务器端的构造器
    private lateinit var rcServer: RCServer
    constructor(socket: Socket,rcServer: RCServer):this(socket){
        EventRegister.notifyEvent(EventCallback.RCSocketConnectedClient::class.java){
            connect(this@RCSocket)
        }
        this.rcServer=rcServer
    }
    //给客户端的构造器
    constructor(ip:String,port:Int):this(Socket(ip,port)){
        EventRegister.notifyEvent(EventCallback.RCSocketConnectedServer::class.java){
            connect(this@RCSocket)
        }
    }
    private val input = socket.getInputStream()
    private val output = socket.getOutputStream()

    private val reader = BufferedReader(InputStreamReader(input))
    private val writer = BufferedWriter(OutputStreamWriter(output))
    init {
        EventRegister.notifyEvent(EventCallback.RCSocketConnected::class.java){
            connect(this@RCSocket)
        }
        thread {
            try {
                val commandExecute=fun(command:Command,commandLine:String,data:Data?){command.executeCommand(commandLine, Invoker(data), Result(writer))}

                var commanding = true
                var dating = false
                var command: Command? = null
                var commandLine: String? = null
                var data: Data? = null
                while (true) {
                    var line = reader.readLine() ?: continue
                    line = line.trim()
                    if (!(line start "/") && !(line start "$") && !(line start ">")) {
                        println(line)
                        continue
                    }
                    if (commanding) {
                        //让用户可以使用">指令"来更简单地执行指令
                        val simpleCommand=if (line start ">"){
                            line = line.substring(1)
                            true
                        }else false
                        //指令存储

                        command = Moder.getCommand(line)
                        commandLine = line
                        if (simpleCommand){
                            commandExecute(command,commandLine,null)
                            continue
                        }
                        commanding = false
                        continue
                    }
                    if ((!commanding) && (!dating) && (line == "$#DataStart")) {
                        dating = true
                        data = Data()
                        continue
                    }
                    if (dating) {
                        if (line == "$#DataEnd") {
                            dating = false
                            continue
                        }
                        //数据存储
                        if (data == null) data = Data()
                        data.putBase64Line(line)
                        continue
                    }
                    if (line != "$#End") continue
                    //指令处理
                    commandExecute(command!!,commandLine!!,data)
                }
            }catch (connectionReset:SocketException){
                EventRegister.notifyEvent(EventCallback.RCSocketCloseConnected::class.java){
                    close(this@RCSocket)
                }
            }
        }

        fun getWriter():Writer{
            return writer
        }
    }

    fun getInetAddress(): InetAddress =socket.inetAddress

    private var sentData:Boolean=false
    //发送一行文字
    fun sendLine(line:String,flush:Boolean=false){
        writer.write(line)
        writer.newLine()
        if (flush)writer.flush()
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
        writer.flush()
    }
}