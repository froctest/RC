package mod.standardmod.eventlistener

import RCSocket
import moder.events.EventCallback

class EL_RCSocketConnectedClientListener : EventCallback.RCSocketConnectedClient {
    override fun connect(rcSocket: RCSocket) {
        println("RCSocket连接到客户端")
    }
}