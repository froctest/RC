import eventlistener.EL_ServerClient
import moder.Mod
import moder.events.EventCallback
import moder.register.Registers

class StandardMod : Mod {

    override fun loaded(register: Registers) {
        val eventRegistry=register.eventRegister
        eventRegistry.register(EventCallback.ServerCreated::class.java, EL_ServerClient())
    }
}