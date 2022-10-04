package command

import moder.command.Command
import moder.command.Commander
import moder.command.Invoker
import moder.command.Result
import start

class C_sleep :Command {

    private val sec=1000L
    private val min=sec*60
    private val hour=min*60
    private val day=hour*24


    override fun canCommand(command: String): Int {
        return when{
            command start "/sleep" -> 1

            else -> -1
        }
    }

    override fun help()="用于休息当前指令执行器的线程"

    override fun executeCommand(command: String, invoker: Invoker, result: Result) {
        if (command start "/sleep"){
            val args = command.split(" ")
            val time=when (args[1]){
                "sec"->args[2].toLong()*sec
                "min"->args[2].toLong()*min
                "hour"->args[2].toLong()*hour
                "day"->args[2].toLong()*day

                else ->args[1].toLong()
            }
            //休息线程
            Thread.sleep(time)
            //准备执行附加在指令后面的延迟指令
            if (args.size>3){
                var str="/sleep "
                if (args[1].toCharArray()[0].isDigit()){
                    str+=args[1]+" "
                }else str+=args[1]+" "+args[2]+" "

                val run=command.replaceFirst(str,"")
                Commander.executeCommand(run,result)
            }
        }
    }
}