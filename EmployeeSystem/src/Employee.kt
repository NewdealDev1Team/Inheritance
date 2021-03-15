import java.util.UUID

/*
사원의 종류는 다음과 같다

- 일반 사원
- 정규직 사원(연봉 1억2천)
- 파트타임 사원(시간당 25,000)
- 영업직 사원 (기본연봉에 영업실적 5%)

사원 클래스는 부서 객체를 가지고 있고, 부서 종류는 다음과 같다 Department
 */

interface Employee {
    var employeeName : String
    val employeeUUID : UUID
    val department : Department
    var employeeSalary : Long
}

class NormalEmployee(
    override var employeeName: String,
    override val employeeUUID: UUID,
    override val department : Department,
    salary:Long) : Employee {
    // 일반 사원은 파라미터로 월급을 받는다
    override var employeeSalary: Long = salary
}

class FullTimeEmployee(
    override var employeeName: String,
    override val employeeUUID: UUID,
    override val department : Department,
    yearSalary:Long) : Employee {
    // 정규직 사원은 파라미터로 연봉을 받는다
    // 받은 연봉을 12로 나눠 월급으로 변경한다
    override var employeeSalary: Long = yearSalary/12
}

class PartTimeEmployee(
    override var employeeName: String,
    override val employeeUUID: UUID,
    override val department : Department,
    timeSalary:Long) : Employee {
    // 파트타임 사원은 파라미터로 시급을 받는다
    // 받은 시급을 곱해서 월급으로 변경한다
    // 8시간 월 21일근무
    override var employeeSalary: Long = ((timeSalary*8)*21)
}

class SalesEmployee(
    override var employeeName: String,
    override val employeeUUID: UUID,
    override val department : Department,
    salary: Long) : Employee {
    // 파트타임 사원은 파라미터로 기본급을 받는다(월급)
    // 기본급에 영업실적 5%를 더해서 월급으로 변경한다
    override var employeeSalary: Long = salary + (salary*0.5).toLong()
}
