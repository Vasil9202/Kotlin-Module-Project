import java.util.*

class Util {
    companion object {
        fun inputText(): String {
            return Scanner(System.`in`).nextLine()
        }

        fun inputDigitChoice(): Int{
            val number = inputText().toIntOrNull()
            if(number == null) return -1
            else return number
        }    }

}