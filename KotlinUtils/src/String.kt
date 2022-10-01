import kotlin.String

infix fun String.start(str:String):Boolean{
    return this.startsWith(str)
}