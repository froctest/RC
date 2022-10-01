package moder

interface Mod {

    /**
     * 返回支持的指令
     *
     * @return
     */
    fun getCommands():List<Command>
}