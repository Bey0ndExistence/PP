abstract class AndGateBuilder {
    abstract fun setInput(input: Boolean): AndGateBuilder
    abstract fun build(): LogicGate
}
