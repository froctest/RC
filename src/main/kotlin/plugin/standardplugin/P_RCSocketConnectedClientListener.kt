package plugin.standardplugin

import RCSocket
import pluginer.events.EventCallback

class P_RCSocketConnectedClientListener :EventCallback.RCSocketConnectedClient {
    override fun connect(rcSocket: RCSocket) {
        println("RCSocket连接到客户端")
    }
}