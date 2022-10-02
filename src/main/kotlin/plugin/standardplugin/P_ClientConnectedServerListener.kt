package plugin.standardplugin

import RCClient
import pluginer.events.EventCallback

class P_ClientConnectedServerListener :EventCallback.ClientConnected {
    override fun connected(client: RCClient) {
        println("连接到服务器")
    }
}