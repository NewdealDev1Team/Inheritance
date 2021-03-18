import java.util.*


open class Employee(open val employeeUUID: UUID,
                    open val employeeName: String,
                    open val department: Department) {
    open fun calculateTheSalary() = 0 //계산
}

//일반사원
class JuniorEmp(override val employeeUUID: UUID,
                override var employeeName: String,
                override var department: Department,
                var salary : Int)
    : Employee(employeeUUID,employeeName, department){
    override fun calculateTheSalary() = salary*12
}

//정규직
class PermanentEmp(override val employeeUUID: UUID,
                   override var employeeName: String,
                   override var department: Department,
                   var annualSalary:Int)
    : Employee(employeeUUID,employeeName, department){
    // div는 나누기
    override fun calculateTheSalary() = annualSalary.div(12) //연봉 계산
}
//파트타임
class PartTimeEmp(override val employeeUUID: UUID,
                  override val employeeName: String,
                  override val department: Department,
                  val workingHour: Int,   // 시간
                  val workingDate: Int,     //날짜
                  var timeSalary: Int)
    : Employee(employeeUUID,employeeName,department){

    override fun calculateTheSalary() = timeSalary.times(workingHour).times(workingDate).toInt()
}
//영업직
class SalesEmp(override val employeeUUID: UUID,
               override var employeeName: String, //이름
               override var department: Department, //부서
               var annualSalary:Int) //영업실적
    : Employee(employeeUUID,employeeName, department){

    override fun calculateTheSalary() = annualSalary.div(12) + (annualSalary.div(12) * 0.05).toInt()   //연봉 계산
}
