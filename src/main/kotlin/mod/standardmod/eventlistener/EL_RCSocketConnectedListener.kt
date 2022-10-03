package mod.standardmod.eventlistener

import RCSocket
import moder.events.EventCallback

class EL_RCSocketConnectedListener : EventCallback.RCSocketConnected {
    override fun connect(rcSocket: RCSocket) {
        println("RCSocket连接到")
    }
}