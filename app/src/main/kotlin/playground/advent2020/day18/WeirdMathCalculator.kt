package playground.advent2020.day18

import playground.utilities.Logger

class WeirdMathCalculator(val l: Logger) {
    fun evaluate(inputExpression: String, withPrecedence: Boolean): Long {
        // Remove all spaces
        val expression = inputExpression.replace(" ", "")
        l.debug("Expression: $expression")

        // Determine the value for the first operand (can be a subexpression)
        val firstOperand = operandAt(0, expression, withPrecedence)
        var valueSoFar = firstOperand.value

        // The next operator will be at position (0 + length of subexpression)
        var nextOperatorIndex = firstOperand.length
        while (nextOperatorIndex < expression.length) {
            val oldValue = valueSoFar

            // Determine the operator and the operand and apply the operation
            val operator = expression[nextOperatorIndex]
            val operand = operandAt(nextOperatorIndex + 1, expression, withPrecedence)
            when (operator) {
                '+' -> valueSoFar += operand.value
                '*' -> valueSoFar *= operand.value
            }

            l.debug("New value after $oldValue$operator$operand: $valueSoFar")

            // The next operator will be at position length + 1 (because the operator is always 1 character)
            nextOperatorIndex += operand.length + 1
        }

        return valueSoFar
    }

    private fun operandAt(startingIndex: Int, expression: String, withPrecedence: Boolean): Operand {
        // Start with either the value or the subexpression at the starting index.
        val startingOperand = when (expression[startingIndex]) {
            '(' -> operandForSubExpressionAt(startingIndex, expression, withPrecedence)
            else -> operandForValueAt(startingIndex, expression)
        }

        // Depending on the precedence rules we might also need to evaluate the next operation.
        val nextOperationIndex = startingIndex + startingOperand.length
        val evaluateNextOperationWithPrecedence =
            withPrecedence
                && nextOperationIndex < expression.length
                && expression[nextOperationIndex] == '+'

        // If not, then we are done.
        if (!evaluateNextOperationWithPrecedence) {
            return startingOperand
        }

        // Otherwise, determine the operand on the right side of the + operator
        val operand = operandAt(nextOperationIndex + 1, expression, withPrecedence)

        // And return the result of applying the operation
        l.debug("Evaluating ${startingOperand.value}+${operand.value} with precedence")
        val sumValue = startingOperand.value + operand.value
        val length = startingOperand.length + 1 + operand.length
        return Operand(sumValue, length)
    }

    private fun operandForValueAt(startingIndex: Int, expression: String) =
        Operand(expression[startingIndex].toString().toLong(), 1)

    private fun operandForSubExpressionAt(startingIndex: Int, expression: String, withPrecedence: Boolean): Operand {
        // Find the closing parenthesis
        var braceLevel = 1
        var index = startingIndex
        do {
            index++
            when (expression[index]) {
                '(' -> braceLevel++
                ')' -> braceLevel--
            }
        } while (braceLevel > 0)

        // Determine the value by evaluating the subexpression
        val value = evaluate(expression.substring(startingIndex + 1, index), withPrecedence)
        val length = index - startingIndex + 1
        return Operand(value, length)
    }
}

private class Operand(val value: Long, val length: Int) {
    override fun toString(): String {
        return value.toString()
    }
}
