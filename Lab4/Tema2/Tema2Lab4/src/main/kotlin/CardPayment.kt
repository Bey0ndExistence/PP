class CardPayment(private val bankAccount: BankAccount) : PaymentMethod {

    override fun pay(fee: Double): Boolean {
        if(bankAccount.updateAmount(fee)){
            println("s-a platit cu cardul")
            return true
        }
        else{
            println("saracie")
            return false
        }
    }
}