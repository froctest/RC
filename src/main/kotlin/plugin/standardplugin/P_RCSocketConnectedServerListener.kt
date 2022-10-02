package plugin.standardplugin

import RCSocket
import pluginer.events.EventCallback

class P_RCSocketConnectedServerListener :EventCallback.RCSocketConnectedServer {
    override fun connect(rcSocket: RCSocket) {
        println("RCSocket连接到服务器")
    }
}