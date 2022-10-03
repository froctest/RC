package mod.standardmod.eventlistener

import RCSocket
import moder.events.EventCallback

class EL_RCSocketCloseListener : EventCallback.RCSocketCloseConnected {
    override fun close(socket: RCSocket) {
        println("RCSocket断开连接")
    }
}