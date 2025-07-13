package org.smartroots.data.service

enum class SensorComponent( val componentEndpoint:String) {

    FAN("fan"), // this relates to humidity
    EC("ec"),
    EC_UP("ecUp"),
    EC_DOWN("ecDown"),
    PH("pH"),
    PH_UP("pHUp"),
    PH_DOWN("pHDown"),
    LIGHT("light"),
    PUMP("pump")

}