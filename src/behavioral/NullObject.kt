package behavioral

fun main() {
    val consoleLog = ConsoleLog()
    val account = BankAccount(consoleLog)
    account.deposit(100)

    val nullLog = NullLog()
    val account2 = BankAccount(nullLog)
    account2.deposit(100)
}

class BankAccount(l: Log) {
    private val log = l
    private var balance = 0
    fun deposit(amount: Int) {
        balance += 0
        log.info("Deposited: $amount")
    }
}

class NullLog: Log { // implement null object pattern
    override fun info(msg: String) { }
    override fun warn(msg: String) { }
}

class ConsoleLog: Log {
    override fun info(msg: String) {
        println("Info: $msg")
    }

    override fun warn(msg: String) {
        println("Warning: $msg")
    }
}

interface Log {
    fun info(msg: String)
    fun warn(msg: String)
}