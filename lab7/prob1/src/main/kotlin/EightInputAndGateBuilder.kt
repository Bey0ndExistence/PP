class EightInputAndGateBuilder : AndGateBuilder() {
    private var input1: Boolean = false
    private var input2: Boolean = false
    private var input3: Boolean = false
    private var input4: Boolean = false
    private var input5: Boolean = false
    private var input6: Boolean = false
    private var input7: Boolean = false
    private var input8: Boolean = false

    override fun setInput(input: Boolean): AndGateBuilder {
        if (!input1) {
            input1 = input
        } else if (!input2) {
            input2 = input
        } else if (!input3) {
            input3 = input
        } else if (!input4) {
            input4 = input
        } else if (!input5) {
            input5 = input
        } else if (!input6) {
            input6 = input
        } else if (!input7) {
            input7 = input
        } else if (!input8) {
            input8 = input
        }
        return this
    }

    override fun build(): LogicGate {
        return AndGate(input1, input2 && input3 && input4 && input5 && input6 && input7 && input8)
    }
}
