package moder

import java.util.*

object Moder {

    fun getCommand(commandLine: String): Command {
        var command: Command?= getIncCommand(commandLine)
        if (command != null)return command
        var maxPro = -1
        val services = ServiceLoader.load(Mod::class.java)
        for (item in services) {
            for (commandAction in item.getCommands()){
                val pro = commandAction.canCommand(commandLine)
                if (pro != -1){
                    if (pro >= maxPro){
                        maxPro=pro
                        command=commandAction
                    }
                }
            }
        }
        if (command == null)throw NullPointerException("没有Mod提供需要的指令: $commandLine")
        return command
    }

    fun getIncCommand(commandLine: String): Command?{
        //TODO 内置指令
        return null
    }
}