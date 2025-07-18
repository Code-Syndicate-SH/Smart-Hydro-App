package org.smartroots.data.model

enum class ArduinoAccess(val ip:String) {
    LOCAL_IP("http://192.168.8.14"),
    LOCAL_IP_MATCH("http://192.168.8"),

    REMOTE_IP("http://192.168.1.102"),
    REMOTE_IP_MATCH("http://192.168.1")
}