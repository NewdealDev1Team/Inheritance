import java.util.UUID

open class Employee(val name: String, val id: UUID, val department: Department) {
    open fun calculateSalary() = 0

    companion object {
        const val BASIC_YEARLY_SALARY = 12000
        const val BASIC_DAILY_SALARY = 2.5
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
    override fun calculateSalary(): Int = BASIC_DAILY_SALARY.times(workingHour).times(workingDate).toInt()
}

class SalesEmployee(
    name: String,
    id: UUID,
    department: Department
) : Employee(name, id, department) {
    override fun calculateSalary(): Int = BASIC_YEARLY_SALARY.times(1.05).div(12).toInt()
}
