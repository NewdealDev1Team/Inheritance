package employee

import java.util.UUID

//어떤 클래스의 상속을 허용하려면 해당 클래스 앞에 open
open class Employee(val employeeName : String, val employeeUUID : UUID,
                    val department : Department, open var employeecontract : String, open var employeeSalary : Long)

class NormalEmployee(
    employeeName: String, employeeUUID: UUID,
    department : Department, contract:String, salary:Long) :
    Employee(employeeName, employeeUUID, department, contract, salary)

class FullTimeEmployee(
    employeeName: String, employeeUUID: UUID,
    department : Department, contract:String, yearSalary:Long) :
    Employee(employeeName, employeeUUID, department, contract, yearSalary/12)

class PartTimeEmployee(
    employeeName: String, employeeUUID: UUID,
    department : Department, contract:String, timeSalary:Long) :
    Employee(employeeName, employeeUUID, department, contract, (timeSalary*25000))

class SalesEmployee(
    employeeName: String, employeeUUID: UUID,
    department : Department, contract:String, salary: Long) :
    Employee(employeeName, employeeUUID, department, contract, (salary.times(1.05).toLong())/12)