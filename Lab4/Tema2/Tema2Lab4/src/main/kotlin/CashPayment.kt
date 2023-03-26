class CashPayment(private var availableAmount: Double) : PaymentMethod {
    override fun pay(fee: Double): Boolean {
        if(availableAmount-fee > 0){
            availableAmount -= fee
            println("s-a platit cash")
            return true
        }
        else{
            println("saracie")
            return false
        }
    }
}