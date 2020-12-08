package playground.advent2020.day8

class Instruction(val operation : String, val argument : Int) {
    override fun toString(): String {
        return "Instruction(operation='$operation', argument=$argument)"
    }

}
