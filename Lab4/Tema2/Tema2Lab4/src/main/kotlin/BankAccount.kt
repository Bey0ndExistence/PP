import java.util.Date

class BankAccount(
    public var  availableAmount : Double,
    private val cardNumber: String,
    private val expirationDate: Date,
    private val cvvCode: Int,
    private val userName: String) {

    fun updateAmount(value: Double) : Boolean
    {
        if(availableAmount-value > 0) {
            this.availableAmount -= value
            return true
        }
        return false
    }
}