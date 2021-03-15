
//데이터 무더기
class DataPile {
    fun randomName() : String {
        val nameArray : Array<String> = arrayOf("민준","서준","예준","도윤","도윤","주원","하준","지호","지후","준서","준우","현우","도현","지훈","건우","우진","선우","서진","민재","현준","연우")
        return nameArray[(0..20).random()]
    }
}