package mod.standardmod.eventlistener

import RCSocket
import moder.events.EventCallback

class EL_RCSocketConnectedServerListener : EventCallback.RCSocketConnectedServer {
    override fun connect(rcSocket: RCSocket) {
        println("RCSocket连接到服务器")
    }
}