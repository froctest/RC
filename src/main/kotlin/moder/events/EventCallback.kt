package moder.events

import RCClient
import RCServer
import RCSocket

interface EventCallback {
    /**
     * 服务器被创建
     *
     */
    interface ServerCreated : EventCallback {
        fun created(server:RCServer)
    }

    /**
     * 服务器连接到用户RCSocket
     *
     */
    interface ServerConnectedUser : EventCallback {
        fun connected(server:RCServer,socket: RCSocket)
    }

    /**
     * 客户端连接到服务器
     *
     */
    interface ClientConnected : EventCallback {
        fun connected(client:RCClient)
    }

    /**
     * RCSocket连接到
     *
     */
    interface RCSocketConnected : EventCallback {
        fun connect(rcSocket: RCSocket)
    }

    /**
     * RCSocket连接到客户端
     *
     */
    interface RCSocketConnectedClient : EventCallback {
        fun connect(rcSocket: RCSocket)
    }

    /**
     * RCSocket连接到服务器
     *
     */
    interface RCSocketConnectedServer : EventCallback {
        fun connect(rcSocket: RCSocket)
    }

    /**
     * RCSocket断开连接
     *
     */
    interface RCSocketCloseConnected : EventCallback {
        fun close(socket: RCSocket)
    }
//    interface ServerCreated : EventCallback{
//        fun created(server:RCServer)
//    }
//    interface ServerCreated : EventCallback{
//        fun created(server:RCServer)
//    }
//    interface ServerCreated : EventCallback{
//        fun created(server:RCServer)
//    }
//    interface ServerCreated : EventCallback{
//        fun created(server:RCServer)
//    }
//    interface ServerCreated : EventCallback{
//        fun created(server:RCServer)
//    }
//    interface ServerCreated : EventCallback{
//        fun created(server:RCServer)
//    }
//    interface ServerCreated : EventCallback{
//        fun created(server:RCServer)
//    }
//    interface ServerCreated : EventCallback{
//        fun created(server:RCServer)
//    }
//    interface ServerCreated : EventCallback{
//        fun created(server:RCServer)
//    }
//    interface ServerCreated : EventCallback{
//        fun created(server:RCServer)
//    }
//    interface ServerCreated : EventCallback{
//        fun created(server:RCServer)
//    }
//    interface ServerCreated : EventCallback{
//        fun created(server:RCServer)
//    }
//    interface ServerCreated : EventCallback{
//        fun created(server:RCServer)
//    }
//    interface ServerCreated : EventCallback{
//        fun created(server:RCServer)
//    }

}