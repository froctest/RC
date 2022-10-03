package moder.command

import moder.register.CommandRegister

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
}