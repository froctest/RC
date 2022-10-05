import moder.events.EventCallback
import moder.register.EventRegister

class RCClient internal constructor(private val rcsocket:RCSocket):RCConnected by RCHandle(rcsocket,""+System.nanoTime()){

    private var ip="127.0.0.1"
    private var port=18848

    init {
        EventRegister.notifyRegistration(EventCallback.ClientConnected::class.java){
            connected(this@RCClient)
        }
    }

    class Builder{
        var ip:String="127.0.0.1"
        var port:Int=18848
        fun build(msgListener: MsgListener,buildFun:Builder.() -> Unit):RCClient{
            this.buildFun()
            return building(msgListener)
        }

        private fun building(msgListener: MsgListener):RCClient{
            val rcsocket=RCSocket(ip,port,msgListener)
            val build=RCClient(rcsocket)
            build.ip=ip
            build.port=port
            return build
        }
    }

    //创建一个新的Handle来通信
    fun createHandle(handle:String):RCHandle=RCHandle(rcsocket, handle)
}