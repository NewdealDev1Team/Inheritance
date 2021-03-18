package employee

/* Enum class를 활용하면 상수(변하지 않는 값)들을 관리할 수 있고,
 각 상수들을 마치 클래스 객체처럼 사용할 수도 있는 등 유용한 기능을 제공한다.*/
enum class Department(val departmentName: String) {
    Dev( "개발팀"),
    Sales( "영업팀"),
    Cs( "고객대응팀"),
    Office( "사무직")
}