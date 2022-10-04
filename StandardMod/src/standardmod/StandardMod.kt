package standardmod

import moder.Mod
import moder.events.EventCallback
import moder.register.Registers
import standardmod.command.C_math
import standardmod.eventlistener.*

class StandardMod : Mod {

    override fun loaded(register: Registers) {
        val commandRegister=register.commandRegister
        commandRegister.register(C_math::class.java, C_math())

        val eventRegistry=register.eventRegister
        eventRegistry.register(EventCallback.ServerConnectedUser::class.java, EL_UserConnectListener())
        eventRegistry.register(EventCallback.ClientConnected::class.java, EL_ClientConnectedServerListener())
        eventRegistry.register(EventCallback.RCSocketConnected::class.java, EL_RCSocketConnectedListener())
        eventRegistry.register(EventCallback.RCSocketConnectedServer::class.java, EL_RCSocketConnectedServerListener())
        eventRegistry.register(EventCallback.RCSocketConnectedClient::class.java, EL_RCSocketConnectedClientListener())
        eventRegistry.register(EventCallback.RCSocketCloseConnected::class.java, EL_RCSocketCloseListener())
    }
}