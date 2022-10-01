package mod

import moder.Command
import moder.Mod
import mod.standardmod.M_math

class StandardMod : Mod {
    override fun getCommands(): List<Command> {
        return listOf(M_math())
    }
}