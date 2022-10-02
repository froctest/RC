package plugin.standardplugin

import RCSocket
import pluginer.events.EventCallback

class P_RCSocketCloseListener :EventCallback.RCSocketCloseConnected {
    override fun close(socket: RCSocket) {
        println("RCSocket断开连接")
    }
}