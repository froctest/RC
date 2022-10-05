import java.io.BufferedWriter

class MsgListener(private val target:(msg:String)-> Unit) {
    companion object {
        fun getInstance(): MsgListener {
            return MsgListener(){
                println(it)
            }
        }
    }

    constructor(bufferedWriter: BufferedWriter):this({
        bufferedWriter.write(it)
        bufferedWriter.newLine()
        bufferedWriter.flush()
    })

    fun sendLine(msg:String){
        target.invoke(msg)
    }

}