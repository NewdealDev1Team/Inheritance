import java.util.UUID

open class Employee(val name: String, val id: UUID, val department: Department) {
    open fun calculateSalary() = 0

    // 연봉 / 시급 정보 상수처리
    companion object {
        const val BASIC_YEARLY_SALARY = 120000000
        const val BASIC_HOURLY_SALARY = 25000
    }

}

class PermanentEmployee(name: String, id: UUID, department: Department) :
    Employee(name, id, department) {
    override fun calculateSalary(): Int = BASIC_YEARLY_SALARY.div(12)
}

class PartTimeEmployee(
    name: String,
    id: UUID,
    department: Department,
    private val workingHour: Int,
    private val workingDate: Int
) : Employee(name, id, department) {
    override fun calculateSalary(): Int = BASIC_HOURLY_SALARY.times(workingHour).times(workingDate)
}


class SalesEmployee(
    name: String,
    id: UUID,
    department: Department
) : Employee(name, id, department) {
    override fun calculateSalary(): Int = BASIC_YEARLY_SALARY.times(1.05).div(12).toInt()
}
