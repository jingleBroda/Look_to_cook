package com.jinglebroda.domain.model.translateWordModel

data class TranslateMatches(
    val id:String,
    val segment:String,
    val translation:String,
    val source:String,
    val target:String,
    val quality:String,
    val reference:String?, //тут непонятно пока какой тип
    val usage_count:Int,
    val subject:String,
    val created_by:String,
    val last_updated_by:String,
    val create_date:String,
    val last_update_date:String,
    val match:Float
)