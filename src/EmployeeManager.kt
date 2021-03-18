import java.text.NumberFormat
import java.util.*

class EmployeeManager {

    private val employees : MutableList<Employee> = mutableListOf()
    //유일한 값
    companion object uuid { //사번을 동적으로 동반 객체를 이용하여 생성
          fun UUID():UUID {
              return UUID.randomUUID()
          }
    }

    fun manager() {
        while(true) {
            println("1. 직원 추가")
            println("2. 직원 제거")
            println("3. 모든 직원 조회")
            println("4. 특정 직원 조회")
            println("5. 직원 총 월급 조회")
            println("6. 직원 평균 월급 조회")
            println("7. 부서별 월급 조회")
            println("8. 부서별 평균 월급 조회" )
            println("0. 종료")

            val input = readLine()?.toInt()
            when(input) {
                1->addEmployee()
                2->removeEmployee()
                3->selectAllEmployee()
                4->selectEmployee()
                5->selectAllEmployeeSalary()
                6->selectEmployeeAverageSalary()
                7->selectAllDepartmentSalary()
                8->selectAverageDepartmentSalary()
                0->break
                else-> {
                    println("잘못 입력하였습니다.")
                }
            }
        }
   }

    private fun addEmployee(){
        println("사원의 이름을 입력해주세요")
        val name : String = readLine().toString()
        val id : UUID = UUID()
        println("부서를 입력헤주세요 1.개발 2.영업, 3.고객대응, 4.사무직")
        var department : Department = Department.DevelopmentDept
        val input = readLine()?.toInt()
        when(input){
            1 -> department = Department.DevelopmentDept
            2 -> department = Department.SalesDept
            3 -> department = Department.CSDept
            4 -> department = Department.Office
            else -> { }
        }
        println("직원의 계약 유형을 입력헤주세요 1.일반사원 2.정규직 3.파트타임 4.영업직")
        var employee : Employee = Employee(employeeUUID = id,employeeName = name, department = department)
        val enter = readLine()?.toInt()
        when(enter){
            1->{
                println("월급을 입력헤주세요")
                var salary = readLine()?.toInt()!!
                employee = JuniorEmp(id,name,department,salary = salary)
            }
            2->{
                println("연봉을 입력헤주세요")
                var salary = readLine()?.toInt()!!
                employee = PermanentEmp(id,name,department,annualSalary = salary)
            }
            3->{
                println("시급을 입력헤주세요")
                var salary = readLine()?.toInt()!!
                println("직원의 일일 근무 시간을 입력세주세요")
                val workingHour = readLine()?.toInt()!!
                println("직원의 월별 근무 일을 입력세주세요")
                val workingDate = readLine()?.toInt()!!

                employee = PartTimeEmp(id,name,department, workingHour = workingHour, workingDate=workingDate, timeSalary = salary)
            }
            4->{
                println("월급을 입력헤주세요")
                var salary = readLine()?.toInt()!!
                employee = SalesEmp(id,name,department,annualSalary = salary)
            }
            else->{}
        }

        println("직원 정보가 입력되었습니다.")
        println("아이디: ${employee.employeeUUID} \n 이름: ${employee.employeeName} \n 부서: ${employee.department} \n 봉급: ${employee.calculateTheSalary()}원")
        employees.add(employee)

    }
    private fun removeEmployee(){
        println("제거할 직원의 이름을 입력헤주세요")
        var employeeName : String? = readLine()

        for(employee in employees){
            if(employee.employeeName == employeeName) {
                employees.remove(employee)
                println("제거 완료")
                break
            }
        }

    }
    private fun selectAllEmployee(){
        println("모든 사원 조회 목록")
        for(employee in employees){
            println("아이디: ${employee.employeeUUID} \n 이름: ${employee.employeeName} \n 부서: ${employee.department} \n 봉급: ${employee.calculateTheSalary()}원")
        }
    }
    private fun selectEmployee(){
        println("찾으시는 직원의 이름을 입력해주세요")
        val name = readLine().toString()

        for(employee in employees){
            if(employee.employeeName == name){
                println("아이디: ${employee.employeeUUID} \n 이름: ${employee.employeeName} \n" +
                        " 부서: ${employee.department} \n 봉급: ${employee.calculateTheSalary()}원")
                break
            }
        }
        println("해당 직원의 이름을 찾을 수 없습니다. 이름을 다시 입력해주세요!!")
    }
    private fun selectAllEmployeeSalary(){
        println("모든 직원의 봉급 조회")
        var allSalary : Int = 0
        for(employee in employees){
            allSalary += employee.calculateTheSalary()
        }
        println("합계 : $allSalary")
    }
    private fun selectEmployeeAverageSalary(){
        println("모든 직원의 평균 월급 조회")
        var allSalary : Int = 0
        var averageSalary : Int = 0
        for(employee in employees){
            allSalary += employee.calculateTheSalary()
        }
        averageSalary = allSalary/employees.size
        println("월급 평균 : $averageSalary")
    }
    private fun selectAllDepartmentSalary(){
        println("부서별 월급 조회")
        println("부서를 입력해주세요 1. 개발부서 2. 영업부서 3. 고객서비스부서 4. 사무직부서")

        val number = readLine()?.toInt()!!
        var sumDept : Int = 0
        var count = 0
        when(number){
            1->{
                for(employee in employees){
                    if(employee.department.departmentName == "개발부서"){
                        sumDept += employee.calculateTheSalary()
                        count++
                    }
                }
            }
            2->{
                for(employee in employees){
                    if(employee.department.departmentName == "영업부서"){
                        sumDept += employee.calculateTheSalary()
                        count++
                    }
                }
            }
            3->{
                for(employee in employees){
                    if(employee.department.departmentName == "고객서비스부서"){
                        sumDept += employee.calculateTheSalary()
                        count++
                    }
                }
            }
            4->{
                for(employee in employees){
                    if(employee.department.departmentName == "사무직부서"){
                        sumDept += employee.calculateTheSalary()
                        count++
                    }
                }
            }
        }
        println("총 월급 :$sumDept")
    }
    private fun selectAverageDepartmentSalary(){
        println("조회할 부서를 고르세요")
        println("부서를 입력해주세요 1. 개발부서 2. 영업부서 3. 고객서비스부서 4. 사무직부서")
        val number = readLine()?.toInt()!!
        var sumDept : Int = 0
        var avgDept : Int = 0
        var count = 0
        when(number){
            1->{
                for(employee in employees){
                    if(employee.department.departmentName == "개발부서"){
                        sumDept += employee.calculateTheSalary()
                        avgDept = sumDept/employees.size
                        count++

                    }
                }
            }
            2->{
                for(employee in employees){
                    if(employee.department.name == "영업부서"){
                        sumDept += employee.calculateTheSalary()
                        avgDept = sumDept/employees.size
                        count++
                    }
                }
            }
            3->{
                for(employee in employees){
                    if(employee.department.name == "고객서비스부서"){
                        sumDept += employee.calculateTheSalary()
                        avgDept = sumDept/employees.size
                        count++
                    }
                }
            }
            4->{
                for(employee in employees){
                    if(employee.department.name == "사무직부서"){
                        sumDept += employee.calculateTheSalary()
                        avgDept = sumDept/employees.size
                        count++
                    }
                }
            }
        }
        println("부서별 평균 월급 :$avgDept")
    }
}
fun main(){
    val employeeManager = EmployeeManager()
    employeeManager.manager()
}
