package moder.command

import moder.register.CommandRegister
import start
import java.io.BufferedWriter
import kotlin.concurrent.thread

object Commander {

    fun getCommand(commandLine: String): Command {
        var command: Command?= getIncCommand(commandLine)
        if (command != null)return command
        var maxPro = -1
        for (commandAction in CommandRegister.getAllRegistration()){
            val pro = commandAction.canCommand(commandLine)
            if (pro != -1){
                if (pro >= maxPro){
                    maxPro=pro
                    command=commandAction
                }
            }
        }
        if (command == null)throw NullPointerException("没有Mod提供需要的指令: $commandLine")
        return command
    }

    private fun getIncCommand(commandLine: String): Command?{
        //TODO 内置指令
        return null
    }


    private val commandExecutors=HashMap<String,CommandExecutor>()
    private var lastKey ="^^NULL^^"
    fun executeCommand(aLine:String,writer: BufferedWriter){
        var line=aLine
        val key=if (line start "^"){
            val aKey=line.split(" ")[0]
            line=line.replaceFirst("$aKey ","")
            aKey
        }else{
            if (CommandExecutor.isExecuteCommandStart(line)){
                lastKey=""+System.nanoTime()
            }
            lastKey
        }
        lastKey=key

        if (!commandExecutors.containsKey(key))commandExecutors[key]=CommandExecutor()
        val commandExecutor=commandExecutors[key]!!
        thread {
            if (commandExecutor.execute(line,commandExecute,writer))
                commandExecutors.remove(key)
        }
    }
    fun executeCommand(aLine: String,result: Result) =executeCommand(aLine,result.bufferedWriter)
    private val commandExecute=fun(command: Command, commandLine:String, data: Data?,writer:BufferedWriter){command.executeCommand(commandLine, Invoker(data), Result(writer))}
}