package plugin.standardplugin

import pluginer.EventRegister
import pluginer.Plugin
import pluginer.events.EventCallback

class StandardPlugin : Plugin {
    override fun loaded(eventRegistry: EventRegister) {
        eventRegistry.register(EventCallback.ServerCreated::class.java,P_ServerClient())
        eventRegistry.register(EventCallback.ServerConnectedUser::class.java, P_UserConnectListener())
        eventRegistry.register(EventCallback.ClientConnected::class.java, P_ClientConnectedServerListener())
        eventRegistry.register(EventCallback.RCSocketConnected::class.java, P_RCSocketConnectedListener())
        eventRegistry.register(EventCallback.RCSocketConnectedServer::class.java, P_RCSocketConnectedServerListener())
        eventRegistry.register(EventCallback.RCSocketConnectedClient::class.java, P_RCSocketConnectedClientListener())
        eventRegistry.register(EventCallback.RCSocketCloseConnected::class.java, P_RCSocketCloseListener())
    }
}