package moder

import moder.register.Registers

interface Mod {

    fun loaded(register: Registers){}
}