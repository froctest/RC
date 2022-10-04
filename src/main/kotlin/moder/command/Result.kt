package moder.command

import java.io.BufferedWriter

class Result (internal val bufferedWriter: BufferedWriter) {
    private val data= Data()

    fun println(string: String): Result {
        bufferedWriter.write(string)
        bufferedWriter.newLine()
        bufferedWriter.flush()
        return this
    }

    fun putData(byteArray: ByteArray): Result {
        data.putData(byteArray)
        return this
    }
    fun apply(){
        bufferedWriter.write(data.getBase64())
        bufferedWriter.newLine()
    }
}