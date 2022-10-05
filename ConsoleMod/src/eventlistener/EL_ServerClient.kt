package eventlistener

import MsgListener
import RCClient
import RCServer
import moder.events.EventCallback
import java.util.*
import kotlin.concurrent.thread

class EL_ServerClient : EventCallback.ServerCreated {
    override fun created(server: RCServer) {
        thread {
            //Tag Warm
            //Tip: Msg指的是非指令,数据的文本段落
            //Warm: 注意,build中填入的MsgListener绝对不能传入与对方相连的流,否则会导致无限重复发送指令
            //我方指令发送到对面时
            //对面会将Msg传入MsgListener
            //然后MsgListener又将指令完整传入通向对面的流(发送到对面)
            //对面获取发来的指令时
            //对面会将Msg传入MsgListener
            //................................................................
            //所以导致了无限循环
            val client=RCClient.Builder().build(MsgListener.getConsoleInstance()){
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