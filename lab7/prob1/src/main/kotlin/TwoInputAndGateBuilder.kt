class TwoInputAndGateBuilder : AndGateBuilder() {
    private var input1: Boolean = false
    private var input2: Boolean = false

    override fun setInput(input: Boolean): AndGateBuilder {
        if (!input1) {
            input1 = input
        } else if (!input2) {
            input2 = input
        }
        return this
    }

    override fun build(): LogicGate {
        return AndGate(input1, input2)
    }
}
