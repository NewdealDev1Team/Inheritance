import java.text.NumberFormat
import java.util.*
import kotlin.system.exitProcess

class EmployeeManager {
    private val employees : MutableList<Employee> = mutableListOf()

    companion object {
        fun createUUID() : UUID {
            return UUID.randomUUID()
        }
    }

    private fun printSelector() {
        println("\n1 > View All Employees")
        println("2 > Add Employee")
        println("3 > Search Employee By Name")
        println("4 > Remove Employee")
        println("5 > Sum of All Employees' Salary")
        println("6 > Average of Employees' Salary")
        println("7 > Sum of Department Salary")
        println("8 > Average of Department Salary")
        println("9 > Exit\n")
        print("Select Menu >>>>>>>>>>>>>  ")
    }

    fun start() {
        println("< Employee Manager >\n")
        println("====================\n")

        var inputNumber = 0
        while (inputNumber != 9) {
            printSelector()
            inputNumber = readLine()?.toInt()!!
            when(inputNumber) {
                1 -> showAllEmployee()
                2 -> addEmployee()
                3 -> searchEmployeeByName()
                4 -> removeEmployeeByNameAndId()
                5 -> sumOfEmployeesSalary()
                6 -> averageOfEmployeesSalary()
                7 -> sumOfDepartmentSalary()
                8 -> averageOfDepartmentSalary()
                9 -> exitProcess(0)
                else -> {
                    println("Wrong Number!!")
                }
            }
        }
    }

    // Show All Employees
    private fun showAllEmployee() {

        // employees 배열이 비어있을떄와 아닐때로 분기처리
        if (employees.size < 1) {
            println("===== List is Emplty ======")
        } else {
            println("This is a list of all employees. >>>>>> ")
            var index = 1
            for(employee in employees) {
                println("====== NO.${index} =======")
                println("Name : ${employee.name}")
                println("ID : ${employee.id}")
                println("Department : ${employee.department.departmentName}")
                println("Salary : ${employee.calculateSalary()}\n")
                index++
            }
        }

    }

    // Add Employee
    private fun addEmployee() {
        print("Enter Employee Name >> ")
        val name: String = readLine()!!
        val id : UUID = createUUID()

        print("Enter Employee's Department Number. (1. Development Team / 2. Sales Team / 3. Customer Service Team / 4. Office Team) >> ")

        val inputDept = readLine()?.toInt()
        var department : Department = Department.DEFAULT
        when (inputDept) {
            1 -> department = Department.DEV
            2 -> department = Department.SALES
            3 -> department = Department.CS
            4 -> department = Department.OFFICE
            else -> { }
        }

        print("Enter Employee's Contract Type. (1. Permanent / 2. Part Time / 3. Sales) >> ")

        var employee = Employee(name, id, department)
        when(readLine()?.toInt()) {
            1 -> {
                employee = PermanentEmployee(name, id, department)
            }
            2 -> {
                print("Enter Employee's Daily Working Hour >> ")
                val workingHour = readLine()?.toInt()!!

                print("Enter Employee's Working Date per Month >> ")
                val workingDate = readLine()?.toInt()!!

                employee = PartTimeEmployee(name, id, department, workingHour, workingDate)
            }
            3 -> {
                employee = SalesEmployee(name, id, department)
            }
            else -> {  }
        }

        println("\nPlease check the input information. >> ")
        println("Name : ${employee.name} \nID : ${employee.id} \nDepartment : ${employee.department.departmentName} \n" +
                "Monthly Salary : ${NumberFormat.getCurrencyInstance(Locale.KOREA).format(employee.calculateSalary())}")

        employees.add(employee)
    }

    private fun printEmployeeInfo(employee: Employee) {
        println("====== RESULT =======")
        println("Name : ${employee.name}")
        println("ID : ${employee.id}")
        println("Department : ${employee.department.departmentName}")
        println("Salary : ${employee.calculateSalary()}\n\n")
    }

    private fun searchEmployeeByName() {
        if (employees.isEmpty()) {
            println("There is nothing to Search !!! ")
        } else {
            print("Enter the name of the employee you want to find. >>  ")
            val inputName = readLine()

            for (employee in employees) {
                if (employee.name == inputName) {
                    printEmployeeInfo(employee)
                }
            }
        }
    }

    // Remove Employee
    private fun removeEmployeeByNameAndId() {

        if (employees.isEmpty()) {
            println("There is nothing to Remove !!! ")
        } else {
            print("Enter the name of the employee you want to remove. >> ")
            val inputName = readLine()
            print("Enter the id of the employee you want to remove. >> ")
            val inputID = readLine()
            for (employee in employees) {
                if (employee.name == inputName && employee.id.toString() == inputID) {
                    employees.remove(employee)
                    println("====== Successfully Removed =======\n")
                    break
                }
            }

        }

    }

    // Sum of Employees Salary
    private fun sumOfEmployeesSalary() {
        println("===== Sum Of Employees Salary =====\n")
        var sum = 0
        for (employee in employees) {
            sum += employee.calculateSalary()
        }
        println("Total : ${NumberFormat.getCurrencyInstance(Locale.KOREA).format(sum)}")
    }

    // Average of Employees Salary
    private fun averageOfEmployeesSalary() {
        println("===== Average Of Employees Salary =====\n")
        var sum = 0
        for (employee in employees) {
            sum += employee.calculateSalary()
        }
        val average = sum.div(employees.size)
        println("Total : ${NumberFormat.getCurrencyInstance(Locale.KOREA).format(average)}")
    }

    // Sum of Department Salary
    private fun sumOfDepartmentSalary() {
        println("===== Sum Of Department Salary =====")
        print("Select Department Number. (1. Development Team / 2. Sales Team / 3. Customer Service Team / 4. Office Team) >>> ")

        var sum = 0
        val departmentNumber = readLine()?.toInt()
        for (employee in employees) {
            if (employee.department.departmentNumber == departmentNumber) {
                sum += employee.calculateSalary()
            }
        }

        println("TOTAl : ${NumberFormat.getCurrencyInstance(Locale.KOREA).format(sum)}")
    }

    // Average of Department Salary
    private fun averageOfDepartmentSalary() {
        println("===== Average Of Department Salary =====")
        print("Select Department Number. (1. Development Team / 2. Sales Team / 3. Customer Service Team / 4. Office Team) >>> ")

        var sum = 0
        var count = 0
        val departmentNumber = readLine()?.toInt()
        for (employee in employees) {
            if (employee.department.departmentNumber == departmentNumber) {
                sum += employee.calculateSalary()
                count++
            }
        }

        val average = sum / count
        println("TOTAl : ${NumberFormat.getCurrencyInstance(Locale.KOREA).format(average)}")
    }

}

fun main() {
    val employeeManager = EmployeeManager()
    employeeManager.start()
}
