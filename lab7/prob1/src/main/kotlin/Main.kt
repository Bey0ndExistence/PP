fun main() {
    // Utilizare TwoInputAndGateBuilder
    val twoInputAndGateBuilder = TwoInputAndGateBuilder()
    val andGateWithTwoInputs = twoInputAndGateBuilder.setInput(true).setInput(true).build()
    println("AND cu 2 intrări: ${andGateWithTwoInputs.calculateOutput()}")

    // Utilizare ThreeInputAndGateBuilder
    val threeInputAndGateBuilder = ThreeInputAndGateBuilder()
    val andGateWithThreeInputs = threeInputAndGateBuilder.setInput(true).setInput(true).setInput(false).build()
    println("AND cu 3 intrări: ${andGateWithThreeInputs.calculateOutput()}")

    // Utilizare FourInputAndGateBuilder
    val fourInputAndGateBuilder = FourInputAndGateBuilder()
    val andGateWithFourInputs = fourInputAndGateBuilder.setInput(true).setInput(true).setInput(true).setInput(false).build()
    println("AND cu 4 intrări: ${andGateWithFourInputs.calculateOutput()}")

    // Utilizare EightInputAndGateBuilder
    val eightInputAndGateBuilder = EightInputAndGateBuilder()
    val andGateWithEightInputs = eightInputAndGateBuilder.setInput(true).setInput(true).setInput(true)
        .setInput(true).setInput(true).setInput(true).setInput(true).setInput(false).build()
    println("AND cu 8 intrări: ${andGateWithEightInputs.calculateOutput()}")
}
