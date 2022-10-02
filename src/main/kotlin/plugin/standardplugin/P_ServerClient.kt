package plugin.standardplugin

import RCServer
import pluginer.events.EventCallback
import java.util.*
import kotlin.concurrent.thread

class P_ServerClient : EventCallback.ServerCreated {
    override fun created(server: RCServer) {
        thread {
            val client=RCClient.Builder().build{
                port=server.getPort()
            }
            val sca= Scanner(System.`in`)
            while (true){
                val line=sca.nextLine()
                if (line == null || line == "")continue

                client.sendLine(line,flush = true)
            }
        }
    }
}