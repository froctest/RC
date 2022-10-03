package moder.command

import java.io.*
import java.util.*
import kotlin.io.path.inputStream
import kotlin.io.path.outputStream
import kotlin.io.path.readBytes

class Data : Closeable,Flushable{
    private val filePath=kotlin.io.path.createTempFile("dataCache")
    private val output=filePath.outputStream()
    fun putBase64Line(line:String): Data {
        //放置数据
        putData(Base64.getDecoder().decode(line))
        return this
    }

    fun putData(byteArray: ByteArray){
        output.write(byteArray)
    }

    fun getInputStream():InputStream{
        return filePath.inputStream()
    }

    fun getBase64():String{
        return Base64.getEncoder().encodeToString(filePath.readBytes())
    }

    fun transferTo(outputStream: OutputStream){
        val inputStream=getInputStream()
        val buff=1024
        val bytes=ByteArray(buff)
        var len=0
        while (len != -1){
            while (inputStream.read(bytes, 0, buff).also { len = it } >= 0) {
                outputStream.write(bytes, 0, len)
            }
        }
    }

    override fun flush(){
        output.flush()
    }

    override fun close() {
        output.close()
    }
}