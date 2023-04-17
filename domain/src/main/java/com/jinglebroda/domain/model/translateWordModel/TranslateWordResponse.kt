package com.jinglebroda.domain.model.translateWordModel

data class TranslateWordResponse(
    val responseData:TranslateResponseData,
    val quotaFinished:Boolean,
    val mtLangSupported:Int?, //тут непонятно пока какой тип
    val responseDetails:String,
    val responseStatus:Int,
    val responderId:Int? , //тут непонятно пока какой тип
    val exception_code:Int?, //тут непонятно пока какой тип
    val matches:List<TranslateMatches>
){
    companion object{
        fun createEmptyResponse():TranslateWordResponse =
            TranslateWordResponse(
                TranslateResponseData(
                    "",
                    0F
                ),
                false,
                0,
                "",
                0,
                0,
                0,
                emptyList()
            )
    }
}