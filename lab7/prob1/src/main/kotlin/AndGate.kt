class AndGate(private val input1: Boolean, private val input2: Boolean) : LogicGate {
    override fun calculateOutput(): Boolean {
        return input1 && input2
    }
}
