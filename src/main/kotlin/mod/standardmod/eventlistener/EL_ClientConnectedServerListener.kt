package mod.standardmod.eventlistener

import RCClient
import moder.events.EventCallback

class EL_ClientConnectedServerListener : EventCallback.ClientConnected {
    override fun connected(client: RCClient) {
        println("连接到服务器")
    }
}