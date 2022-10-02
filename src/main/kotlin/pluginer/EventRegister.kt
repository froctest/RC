package pluginer

import pluginer.events.EventCallback

object EventRegister {
    private val registered=HashMap<Any,MutableList<Any>>()

    fun <E : EventCallback> register(events: Class<E>, eventCallback: E){
        if (registered[events]==null) registered[events]=ArrayList<Any>()
        registered[events]!!.add(eventCallback)
    }
    fun <E : EventCallback> unregister(events: Class<E>, eventCallback: E){
        if (registered[events]==null)return
        registered[events]!!.remove(eventCallback)
    }

    fun <E : EventCallback> getEvent(kClass: Class<E>):List<E>{
        if (registered[kClass] == null)return listOf()
        @Suppress("UNCHECKED_CAST")
        return registered[kClass] as List<E>
    }
    fun <E : EventCallback> notifyEvent(events: Class<E>, block:E.()->Unit){
        for(event in getEvent(events)){
            event.block()
        }
    }
}