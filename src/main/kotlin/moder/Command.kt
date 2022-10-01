package moder

interface Command {
    /**
     * 该指令是否符合执行条件,并返回执行优先级(返回-1代表不符合,会优先选择优先级高的Command来执行)
     *
     * @param command
     * @return
     */
    fun canCommand(command: String):Int

    /**
     * 返回该指令的使用帮助
     *
     * @return
     */
    fun help():String

    /**
     * 执行指令
     *
     * @param command
     * @param invoker
     * @param result
     */
    fun executeCommand(command: String, invoker: Invoker, result: Result)
}