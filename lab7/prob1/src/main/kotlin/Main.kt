fun main() {
    // Utilizare TwoInputAndGateBuilder
    val twoInputGate = TwoInputAndGateBuilder()
    val ANDgate2Inputs = twoInputGate.setInput(true).setInput(true).build()
    
    println("AND cu 2 intrări: ${ANDgate2Inputs.calculateOutput()}")

    // Utilizare ThreeInputAndGateBuilder
    val threeInputGate = ThreeInputAndGateBuilder()
    val ANDGate3Inputs = threeInputGate.setInput(true).setInput(true).setInput(false).build()
    println("AND cu 3 intrări: ${threeInputGate.calculateOutput()}")

    // Utilizare FourInputAndGateBuilder
    val fourInputGate = FourInputAndGateBuilder()
    val ANDGate4Inputs = fourInputGate.setInput(true).setInput(true).setInput(true).setInput(false).build()
    println("AND cu 4 intrări: ${andGateWithFourInputs.calculateOutput()}")

    // Utilizare EightInputAndGateBuilder
    val eightInputGate = EightInputAndGateBuilder()
    val ANDGate8Inputs = eightInputGate.setInput(true).setInput(true).setInput(true)
        .setInput(true).setInput(true).setInput(true).setInput(true).setInput(false).build()
    println("AND cu 8 intrări: ${andGateWithEightInputs.calculateOutput()}")
}
