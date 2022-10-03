package mod.standardmod.eventlistener

import RCServer
import RCSocket
import moder.events.EventCallback

class EL_UserConnectListener : EventCallback.ServerConnectedUser {
    override fun connected(server: RCServer, socket: RCSocket) {
        println("用户连接到服务器: ${socket.getInetAddress().hostAddress}")
    }
}