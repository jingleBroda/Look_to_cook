package com.jinglebroda.domain.model.searchRecipeModel

data class Hits(
    val q:String,
    val from:Int,
    val to:Int,
    val count:Int,
    val more:Boolean,
    val hits:List<Hit>
){
    companion object{
        fun generateEmptyHits():Hits = Hits(
            "Empty Hits",
            0,
            0,
            0,
            false,
            emptyList()
        )
    }
}