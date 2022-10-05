import java.io.BufferedWriter
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.io.PrintStream

class MsgListener(private val target:(msg:String)-> Unit) {
    companion object {
        fun getConsoleInstance()=MsgListener(System.out)
    }

    constructor(bufferedWriter: BufferedWriter):this({
        bufferedWriter.write(it)
        bufferedWriter.newLine()
        bufferedWriter.flush()
    })

    constructor(printStream: PrintStream):this({
        printStream.println(it)
        printStream.flush()
    })
    constructor(outputStream: OutputStream):this(BufferedWriter(OutputStreamWriter(outputStream)))

    fun sendLine(msg:String){
        target.invoke(msg)
    }

}