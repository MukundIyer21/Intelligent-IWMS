package com.example.intelligentiwms

data class WarehouseData(
    val warehouseName : String?,
    val ownerEmail :String?,
    val Password:String?,
    val warehouseLength:Int,
    val wareHouseWidth : Int,
    val wareHouseHeight:Int,
    val Entrance_x :Int,
    val Entrance_y : Int,
    val Entrance_z:Int,
    val Blocked_x :Int,
    val Blocked_y:Int,
    val Blocked_z : Int,
    val BlockedLength: Int,
    val BlockedWidth : Int,
    val BlockedHeight : Int,
    val PathStart_x :Int,
    val PathStart_y: Int,
    val PathEnd_x :Int,
    val PathEnd_y: Int
)
