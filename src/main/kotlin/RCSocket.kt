import moder.Command
import moder.Data
import moder.Invoker
import moder.Moder
import moder.Result
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.Writer
import java.net.Socket
import java.net.SocketException
import kotlin.concurrent.thread

class RCSocket private constructor(private val socket: Socket){
    //给服务器端的构造器
    private lateinit var rcServer: RCServer
    constructor(socket: Socket,rcServer: RCServer):this(socket){
        this.rcServer=rcServer
    }
    //给客户端的构造器
    constructor(ip:String,port:Int):this(Socket(ip,port))
    private val input = socket.getInputStream()
    private val output = socket.getOutputStream()

    private val reader = BufferedReader(InputStreamReader(input))
    private val writer = BufferedWriter(OutputStreamWriter(output))
    init {
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
                println("用户断开连接")
            }
        }

        fun getWriter():Writer{
            return writer
        }
    }
    //发送一行文字
    fun sendLine(line:String,flush:Boolean=false){
        writer.write(line)
        writer.newLine()
        if (flush)writer.flush()
    }

    //冲刷缓存
    fun flush(){
        writer.flush()
    }
}