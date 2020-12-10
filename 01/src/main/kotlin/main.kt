import java.io.File

fun main(args: Array<String>) {
    val inputNumbersFilePath: String = getInputNumberFilePath(args)

    var numbers = insertNumbersFromFileToArray(inputNumbersFilePath)

    numbers = orderArrayOfNumbers(numbers)
    
    val pairOfNumbers = findPairOfValuesIfTheyExist(numbers)

    multiplyTheTwoValues(pairOfNumbers)
}

fun multiplyTheTwoValues(pairOfNumbers: Array<Int>) {
    println("The multiplication of the final values is: " +
            "${pairOfNumbers[0]} * ${pairOfNumbers[1]} = ${pairOfNumbers[0] * pairOfNumbers[1]}")
}

fun findPairOfValuesIfTheyExist(numbers: Array<Int>): Array<Int> {
    var smallIndex: Int = 0
    var bigIndex: Int = smallIndex + 1
    var searching: Boolean = true
    val sumToAchieve: Int = 2020

    while (searching) {
//        println("Numbers to compare: ${numbers[smallIndex]} and ${numbers[bigIndex]}")

        if (noNumbersToCompareLeft(numbers.size, smallIndex, bigIndex)
            || pairOfNumbersFound(numbers[smallIndex], numbers[bigIndex], sumToAchieve)) {
            println("Search is stopped.")
            searching = false
        } else if (sumIsGreaterThanNumberToAchieve(numbers[smallIndex], numbers[bigIndex], sumToAchieve)) {
            smallIndex++
            bigIndex = smallIndex+1
        } else {
            bigIndex++
        }
    }

    val pairOfValues: IntArray = intArrayOf(numbers[smallIndex], numbers[bigIndex])

    println("The final values are ${pairOfValues[0]} and ${pairOfValues[1]}, " +
            "and their sum is: ${pairOfValues[0] + pairOfValues[1]}")

    return pairOfValues.toTypedArray()
}

fun compareNumbers(smallNumber: Int, bigNumber: Int): Int {
    return bigNumber - smallNumber
}

fun sumIsGreaterThanNumberToAchieve(smallNumber: Int, bigNumber: Int, sumToAchieve: Int): Boolean {
    return compareNumbers(smallNumber+bigNumber, sumToAchieve) < 0
}

fun pairOfNumbersFound(smallNumber: Int, bigNumber: Int, sumToAchieve: Int): Boolean {
    return smallNumber + bigNumber == sumToAchieve
}

fun noNumbersToCompareLeft(numbersSize: Int, smallIndex: Int, bigIndex: Int): Boolean {
    return (bigIndex > numbersSize && smallIndex+1 == bigIndex)
}

fun orderArrayOfNumbers(numbers: Array<Int>): Array<Int> {

    println("The list of numbers is going to be ordered.")
    println("Originally, they were: ${numbers.contentToString()}")

    numbers.sort()

    println("The numbers in the list have been ordered.")
    println("Now, they are: ${numbers.contentToString()}")

    return numbers
}

fun insertNumbersFromFileToArray(inputNumbersFilePath: String): Array<Int> {
    val mutableNumbers: MutableList<Int> = mutableListOf()

    File(inputNumbersFilePath)
        .forEachLine { line -> addNumberToList(mutableNumbers, line) }

    println("The numbers have been added to the array.")
    println("The array is: $mutableNumbers")

    return mutableNumbers.toTypedArray()
}

fun addNumberToList(mutableNumbers: MutableList<Int>, line: String) {
    val convertedNumber = line.toInt()
    mutableNumbers.add(convertedNumber)

//    println("Adding number: $convertedNumber")
}

fun getInputNumberFilePath(inputFilePath: Array<String>): String {
    var finalFilePath: String? = (if (inputFilePath.isEmpty() || inputFilePath[0].isEmpty) "" else inputFilePath[0])

    while (finalFilePath!!.isEmpty) {
        println("Type the path to the list of numbers:")
        finalFilePath = readLine()
    }

    println("The file path is: $finalFilePath")

    return finalFilePath
}
