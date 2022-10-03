package standardmod.command

import moder.command.Command
import moder.command.Invoker
import moder.command.Result
import start
import java.math.BigDecimal
import java.math.MathContext

class C_math : Command {
    override fun canCommand(command: String): Int {
        return when{
            command start "/math" -> 1

            else -> -1
        }
    }

    override fun help()="一种能够进行大数字运算的指令"

    override fun executeCommand(command: String, invoker: Invoker, result: Result) {
        if (command start "/math"){
            val args = command.split(" ")
            when(args[1]){
                //加法
                "add"->{
                    var dec=BigDecimal(args[2])
                    var index=3
                    while (index<args.size){
                        dec=dec.add(BigDecimal(args[index]))
                        index++
                    }
                    result.println(dec.toString())
                }

                //减法
                "subtract" -> {
                    var dec=BigDecimal(args[2])
                    var index=3
                    while (index<args.size){
                        dec=dec.subtract(BigDecimal(args[index]))
                        index++
                    }
                    result.println(dec.toString())
                }

                //乘法
                "multiply" -> {
                    var dec=BigDecimal(args[2])
                    var index=3
                    while (index<args.size){
                        dec=dec.multiply(BigDecimal(args[index]))
                        index++
                    }
                    result.println(dec.toString())
                }

                //除法
                "divide" -> {
                    var dec=BigDecimal(args[2])
                    var index=3
                    while (index<args.size){
                        dec=dec.divide(BigDecimal(args[index]))
                        index++
                    }
                    result.println(dec.toString())
                }

                //取绝对值
                "abs" -> {
                    val dec=BigDecimal(args[2]).abs()
                    result.println(dec.toString())
                }

                //比较
                "compareTo" -> {
                    val dec1=BigDecimal(args[2])
                    val dec2=BigDecimal(args[3])
                    val compare=dec1.compareTo(dec2)
                    result.println(compare.toString())
                }

                //取余数
                "remainder" -> {
                    val dec1=BigDecimal(args[2])
                    val dec2=BigDecimal(args[3])
                    val dec=dec1.remainder(dec2)
                    result.println(dec.toString())
                }

                //开平方根
                "sqrt" -> {
                    val dec=BigDecimal(args[2]).sqrt(MathContext.DECIMAL128)
                    result.println(dec.toString())
                }

                //平方
                "square" -> {
                    var dec=BigDecimal(args[2])
                    dec=dec.multiply(dec)
                    result.println(dec.toString())
                }

                //次方
                "pow" -> {
                    var dec=BigDecimal(args[2])
                    val index=args[3].toInt()
                    dec = dec.pow(index)
                    result.println(dec.toString())
                }

                //相反数
                "negate" -> {
                    val dec=BigDecimal(args[2]).negate()
                    result.println(dec.toString())
                }

                //小数位数
                "scale" -> {
                    val scale=BigDecimal(args[2]).scale()
                    result.println(scale.toString())
                }

                //返回精度
                "precision" -> {
                    val precision=BigDecimal(args[2]).precision()
                    result.println(precision.toString())
                }

                //判断两数是否相同
                "equals" -> {
                    val dec1=BigDecimal(args[2])
                    val dec2=BigDecimal(args[3])
                    val equals= dec1 == dec2
                    result.println(equals.toString())
                }

                //在一些数字之间取最大值
                "max" -> {
                    var max=BigDecimal(args[2])
                    var index=3
                    while (index<args.size){
                        val dec=BigDecimal(args[index])
                        max = max.max(dec)
                        index++
                    }
                    result.println(max.toString())
                }

                //在一些数字之间取最小值
                "min" -> {
                    var max=BigDecimal(args[2])
                    var index=3
                    while (index<args.size){
                        val dec=BigDecimal(args[index])
                        max = max.min(dec)
                        index++
                    }
                    result.println(max.toString())
                }

                else -> {
                    throw UnsupportedOperationException("不支持该运算")
                }
            }
        }
    }

}