package plugin.standardplugin

import RCServer
import RCSocket
import pluginer.events.EventCallback

class P_UserConnectListener : EventCallback.ServerConnectedUser {
    override fun connected(server: RCServer, socket: RCSocket) {
        println("用户连接到服务器: ${socket.getInetAddress().hostAddress}")
    }
}