package plugin.standardplugin

import RCSocket
import pluginer.events.EventCallback

class P_RCSocketConnectedListener :EventCallback.RCSocketConnected {
    override fun connect(rcSocket: RCSocket) {
        println("RCSocket连接到")
    }
}