
class RCHandle internal constructor(private val socket: RCSocket, private val handle: String) : RCConnected by socket {
    override fun sendLine(line: String, flush: Boolean) {
        socket.sendLine("^$handle $line",flush)
    }
}