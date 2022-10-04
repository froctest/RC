package moder.command

import start
import java.io.BufferedWriter

class CommandExecutor{
    companion object{
        fun isExecuteCommandStart(line: String):Boolean=!(!(line start "/") && !(line start ">"))
        fun isExecuteCommandLine(line:String):Boolean=!(!(line start "/") && !(line start "$") && !(line start ">"))
    }

    private var commanding = true
    private var dating = false
    private var command: Command? = null
    private var commandLine: String? = null
    private var data: Data? = null

    /**
     * 指令执行完毕,不需要额外的参数时返回true
     *
     * @param aCommandLine
     * @param commandExecute
     * @return
     */
    @Synchronized
    fun execute (aCommandLine:String,commandExecute:(Command,String,Data?,BufferedWriter)->Unit,writer: BufferedWriter):Boolean{
        var line = aCommandLine.trim()
        if (!isExecuteCommandLine(line)) {
            println(line)
            return true
        }
        if (commanding) {
            //让用户可以使用">指令"来更简单地执行指令
            val simpleCommand=if (line start ">"){
                line = line.substring(1)
                true
            }else false
            //指令存储

            command = Commander.getCommand(line)
            commandLine = line
            if (simpleCommand){
                commandExecute(command?:throw NullPointerException("没有模组提供适用的指令"), commandLine!!,null,writer)
                return true
            }
            commanding = false
            return false
        }
        if ((!dating) && (line == "$#DataStart")) {
            dating = true
            data = Data()
            return false
        }
        if (dating) {
            if (line == "$#DataEnd") {
                dating = false
                return false
            }
            //数据存储
            if (data == null) data = Data()
            data!!.putBase64Line(line)
            return false
        }
        if (line != "$#End") return false
        //指令处理
        commandExecute(command!!,commandLine!!,data,writer)
        return true
    }
}