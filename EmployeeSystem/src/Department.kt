
/*
사원 클래스는 부서 객체를 가지고 있고, 부서 종류는 다음과 같다 Department
- 개발팀
- 영업팀
- 고객대응팀
- 사무직
 */


enum class Department(val code:String, val description:String) {
    Dev("DEV", "개발팀"),
    Sales("SALES", "영업팀"),
    Cs("CS", "고객대응팀"),
    Office("OFFICE", "사무직")
}
