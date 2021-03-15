
//데이터 무더기
class DataPile {
    fun randomName() : String {
        val nameArray : Array<String> = arrayOf(
            "민준",
            "지원",
            "준영",
            "태인",
            "현수",
            "승훈",
            "지우",
            "승희",
            "영환",
            "세진",
            "호진",
            "규혁",
            "성원",
            "재혁",
            "도겸",
            "루이",
            "민수",
            "찬우",
            "민재",
            "도근",
            "창범",
            "상화",
            "한서",
            "승민",
            "상현",
            "문혁",
            "지호",
            )
        return nameArray[(nameArray.indices).random()]
    }
}