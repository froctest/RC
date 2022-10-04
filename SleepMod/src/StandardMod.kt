import command.C_sleep
import moder.Mod
import moder.register.Registers

class StandardMod : Mod {

    override fun loaded(register: Registers) {
        val commandRegister=register.commandRegister
        commandRegister.register(C_sleep::class.java,C_sleep())
    }
}