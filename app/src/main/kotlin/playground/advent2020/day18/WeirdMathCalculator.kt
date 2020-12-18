package playground.advent2020.day18

import playground.utilities.Logger

class WeirdMathCalculator(val l: Logger) {
    fun evaluate(inputExpression: String): Long {
        // Remove all spaces
        val expression = inputExpression.replace(" ", "")
        l.debug("Expression: $expression")

        // Determine the value for the first operand (can be a subexpression)
        val firstOperand = operandAt(0, expression)
        var valueSoFar = firstOperand.value

        // The next operator will be at position (0 + length of subexpression)
        var nextOperatorIndex = firstOperand.length
        do {
            // Determine the operator and the operand and apply the operation
            val operator = expression[nextOperatorIndex]
            val operand = operandAt(nextOperatorIndex + 1, expression)
            when (operator) {
                '+' -> valueSoFar += operand.value
                '*' -> valueSoFar *= operand.value
            }

            l.debug("New value after $operator $operand: $valueSoFar")

            // The next operator will be at position length + 1 (because the operator is always 1 character)
            nextOperatorIndex += operand.length + 1
        } while (nextOperatorIndex < expression.length)

        return valueSoFar
    }

    private fun operandAt(startingIndex: Int, expression: String): Operand {
        return when (expression[startingIndex]) {
            '(' -> operandForSubExpressionAt(startingIndex, expression)
            else -> operandForValueAt(startingIndex, expression)
        }
    }

    private fun operandForValueAt(startingIndex: Int, expression: String) =
        Operand(expression[startingIndex].toString().toLong(), 1)

    private fun operandForSubExpressionAt(startingIndex: Int, expression: String): Operand {
        // Find the closing parenthesis
        var braceLevel = 1
        var index = startingIndex
        do {
            index++
            when (expression[index]) {
                '(' -> braceLevel++
                ')' -> braceLevel--
            }
        } while(braceLevel > 0)

        // Determine the value by evaluating the subexpression
        val value = evaluate(expression.substring(startingIndex + 1, index))
        val length = index - startingIndex + 1
        return Operand(value, length)
    }
}

private class Operand(val value: Long, val length: Int) {
    override fun toString(): String {
        return value.toString()
    }
}
