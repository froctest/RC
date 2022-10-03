package moder.register

import java.util.stream.Collectors

open class Register<T : Any> {
    private val registered=HashMap<Any,MutableList<Any>>()

    fun <E : T> register(registration: Class<E>, eventCallback: E){
        if (registered[registration]==null) registered[registration]=ArrayList()
        registered[registration]!!.add(eventCallback)
    }
    fun <E : T> unregister(registration: Class<E>, eventCallback: E){
        if (registered[registration]==null)return
        registered[registration]!!.remove(eventCallback)
    }

    fun <E : T> getRegistration(kClass: Class<E>):List<E>{
        if (registered[kClass] == null)return listOf()
        @Suppress("UNCHECKED_CAST")
        return registered[kClass] as List<E>
    }
    fun <E : T> notifyRegistration(registration: Class<E>, block:E.()->Unit){
        for(event in getRegistration(registration)){
            event.block()
        }
    }
    fun getAllRegistration():List<T>{
        return registered.values.stream().flatMap {
            it.stream()
        }.map {
            @Suppress("UNCHECKED_CAST")
            it as T
        }.collect(Collectors.toList())
    }
}