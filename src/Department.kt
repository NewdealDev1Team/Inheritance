//부서객체
enum class Department(val department: Int, val departmentName: String){//val 선언하기
    DevelopmentDept(1,"개발부서"),
    SalesDept(2,"영업부서"),
    CSDept(3,"고객서비스부서"),
    Office(4,"사무직부서")
}