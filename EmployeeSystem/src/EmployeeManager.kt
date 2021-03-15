import java.text.NumberFormat
import java.util.*

/*
반드시 EmployeeManager 클래스를 main에서 호출하여 각각의 임금을 계산하도록 한다

구현해야 할 기능은 다음과 같다

- 사원들의 총 월급
- 사원의 평균 월급
- 부서별 총 월급, 평균 월급

 */

class EmployeeManager {
    private val employees : MutableList<Employee> = mutableListOf()

    companion object {
        fun getRandomUUID():UUID {
            return UUID.randomUUID()
        }
    }

    fun managerInit() {
        println("=====사원관리 시스템=====")
        println()
        while(true) {
            println("=====기능을 선택해주세요=====")
            println("1: 사원 추가")
            println("2: 사원 제거")
            println("3: 모든 사원 조회")
            println("4: 사원 총 월급 조회")
            println("5: 사원 평균 월급 조회")
            println("6: 부서 총 월급 조회")
            println("7: 부서 평균 월급 조회")
            println("8: 랜덤 데이터 만들기")
            println("9: 종료")
            when(readLine()!!.toInt()) {
                1->addEmployee()
                2->removeEmployee()
                3->referEmployees()
                4->referEmployeesSalaryTotal()
                5->referEmployeesSalaryAverage()
                6->referDepartmentSalaryTotal()
                7->referDepartmentSalaryAverage()
                8->makeRandomData()
                9->break
                else->{}
            }
        }
    }

    //사원 추가
    private fun addEmployee() {
        //사원 이름
        println("추가할 사원의 이름을 입력해주세요")
        val name: String = readLine()!!
        //사원 UUID
        val uuid = getRandomUUID()
        //사원 부서
        println("추가할 사원의 부서를 입력해주세요")
        println("1: 개발팀")
        println("2: 영업팀")
        println("3: 고객대응팀")
        println("4: 사무직")

        lateinit var department : Department
        when(readLine()) {
            "1" -> department = Department.Dev
            "2" -> department = Department.Sales
            "3" -> department = Department.Cs
            "4" -> department = Department.Office
            else -> {}
        }

        //사원 계약형태
        println("추가할 사원의 계약형태를 입력해주세요")
        println("1: 일반직")
        println("2: 정규직")
        println("3: 파트타임")
        println("4: 영업직")
        /*
        월급 (일반직)
        연봉 (정규직)
        시급 (파트타임)
        월급 (영업직)
        */
        lateinit var employee : Employee
        when(readLine()) {
            "1" -> {
                //월급
                println("해당 사원의 월급을 입력해주세요")
                val salary:Long = readLine()!!.toLong()
                employee = NormalEmployee(name,uuid,department, salary)
            }
            "2" -> {
                //연봉
                println("해당 사원의 연봉을 입력해주세요")
                val salary:Long = readLine()!!.toLong()
                employee = FullTimeEmployee(name,uuid,department, salary)
            }
            "3" -> {
                //시급
                println("해당 사원의 시급을 입력해주세요")
                val salary:Long = readLine()!!.toLong()
                employee = PartTimeEmployee(name,uuid,department, salary)
            }
            "4" -> {
                //월급
                println("해당 사원의 월급을 입력해주세요")
                val salary:Long = readLine()!!.toLong()
                employee = SalesEmployee(name,uuid,department, salary)
            }
            else -> {}
        }
        println("사원 정보가 입력되었습니다")
        println("사원명 : ${employee.employeeName}")
        println("식별번호 : ${employee.employeeUUID}")
        println("부서 : ${employee.department.description}")
        println("월급 : ${employee.employeeSalary}")
        employees.add(employee)
    }

    //사원 제거
    private fun removeEmployee() {
        println("=====제거할 사원의 UUID를 입력해주세요=====")
        val uuid : UUID = UUID.fromString(readLine()!!)
        employees.removeAt(searchEmployee(uuid))
    }

    //사원 찾기
    //INDEX를 반환함
    private fun searchEmployee(uuid:UUID) : Int{
        var index = 0
        for(i in employees) {
            if(i.employeeUUID==uuid) {
                break
            }
            index++
        }
        return index
    }

    //사원 목록 조회
    private fun referEmployees() {
        println("=====모든 사원의 정보를 출력합니다=====")
        for (i in employees) {
            println("사원명 : ${i.employeeName}")
            println("식별번호 : ${i.employeeUUID}")
            println("부서 : ${i.department.description}")
            val formatted = NumberFormat.getCurrencyInstance().format(i.employeeSalary)
            println("월급 : $formatted")
            println("=====================")
        }
    }

    //사원 총 월급 조회
    private fun referEmployeesSalaryTotal() {
        println("=====사원 총 월급을 출력합니다=====")
        var salary : Long = 0
        for (i in employees) {
            salary += i.employeeSalary
        }
        val formatted = NumberFormat.getCurrencyInstance().format(salary)
        println("사원 총 월급 : $formatted")
    }

    //사원 평균 월급 조회
    private fun referEmployeesSalaryAverage() {
        println("=====사원 평균 월급을 출력합니다=====")
        var salary : Long = 0
        for (i in employees) {
            salary += i.employeeSalary
        }
        salary/=employees.size
        val formatted = NumberFormat.getCurrencyInstance().format(salary)
        println("사원 평균 월급 : $formatted")
    }

    //부서 총 월급 조회
    private fun referDepartmentSalaryTotal() {
        println("조회할 부서를 지정해주세요")
        println("1: 개발팀")
        println("2: 영업팀")
        println("3: 고객대응팀")
        println("4: 사무직")

        var salary : Long = 0
        when(readLine()) {
            "1" -> {
                for (i in employees) {
                    if(i.department == Department.Dev) {
                        salary+=i.employeeSalary
                    }
                }
            }
            "2" -> {
                for (i in employees) {
                    if(i.department == Department.Sales) {
                        salary+=i.employeeSalary
                    }
                }
            }
            "3" -> {
                for (i in employees) {
                    if(i.department == Department.Cs) {
                        salary+=i.employeeSalary
                    }
                }
            }
            "4" -> {
                for (i in employees) {
                    if(i.department == Department.Office) {
                        salary+=i.employeeSalary
                    }
                }
            }
            else -> {}
        }
        val formatted = NumberFormat.getCurrencyInstance().format(salary)
        println("부서 총 월급 : $formatted")
    }

    //부서 평균 월급 조회
    private fun referDepartmentSalaryAverage() {
        println("조회할 부서를 지정해주세요")
        println("1: 개발팀")
        println("2: 영업팀")
        println("3: 고객대응팀")
        println("4: 사무직")

        var salary : Long = 0
        var departmentEmployeeCount = 0
        when(readLine()!!) {
            "1" -> {
                for (i in employees) {
                    if(i.department == Department.Dev) {
                        salary+=i.employeeSalary
                        departmentEmployeeCount++
                    }
                }
            }
            "2" -> {
                for (i in employees) {
                    if(i.department == Department.Sales) {
                        salary+=i.employeeSalary
                        departmentEmployeeCount++
                    }
                }
            }
            "3" -> {
                for (i in employees) {
                    if(i.department == Department.Cs) {
                        salary+=i.employeeSalary
                        departmentEmployeeCount++
                    }
                }
            }
            "4" -> {
                for (i in employees) {
                    if(i.department == Department.Office) {
                        salary+=i.employeeSalary
                        departmentEmployeeCount++
                    }
                }
            }
            else -> {}
        }
        salary/=departmentEmployeeCount
        val formatted = NumberFormat.getCurrencyInstance().format(salary)
        println("부서 평균 월급 : ${formatted}")
    }

    /*
    관리 시스템 테스트용
    랜덤 사원 데이터 생성 기능
    */
    private fun makeRandomData() {
        println("생성할 랜덤 데이터 갯수를 입력해주세요")
        val dataCount = readLine()!!.toInt()
        for(i in 1..dataCount) {
            val name = DataPile().randomName()
            var department : Department = Department.Dev
            when((1..4).random()) {
                1 -> department = Department.Dev
                2 -> department = Department.Sales
                3 -> department = Department.Cs
                4 -> department = Department.Office
                else -> {}
            }
            val uuid : UUID = getRandomUUID()
            //10,000,000 ~ 100,000,000
            var employee : Employee = NormalEmployee(name, uuid, department,0)
            when((1..4).random()) {
                1 -> {
                    //월급
                    val salary : Long = (10000000..100000000).random().toLong()
                    employee = NormalEmployee(name,uuid,department,salary)
                }
                2 -> {
                    //연봉
                    val salary : Long = (10000000..100000000).random().toLong()
                    employee = FullTimeEmployee(name,uuid,department,salary)
                }
                3 -> {
                    //시급
                    val salary : Long = (10000..100000).random().toLong()
                    employee = PartTimeEmployee(name,uuid,department,salary)
                }
                4 -> {
                    //월급
                    val salary : Long = (10000000..100000000).random().toLong()
                    employee = SalesEmployee(name,uuid,department,salary)
                }
                else -> {}
            }
            employees.add(employee)
        }
    }
}