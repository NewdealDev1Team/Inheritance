
/*
사원 클래스는 부서 객체를 가지고 있고, 부서 종류는 다음과 같다 Department
- 개발팀
- 영업팀
- 고객대응팀
- 사무직
 */


interface Department {
    val departmentID : String
}

class DevDepartment : Department {
    override val departmentID: String = "DEV"
}

class SalesDepartment : Department {
    override val departmentID: String = "SALES"
}

class CsDepartment : Department {
    override val departmentID: String = "CS"
}

class OfficeDepartment : Department {
    override val departmentID: String = "OFFICE"
}