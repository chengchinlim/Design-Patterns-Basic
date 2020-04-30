package behavioral

fun main() {
    val bankAccount = MyBankAccount()
    println(bankAccount)
    val commands = listOf(
            MyBankAccountCommand(bankAccount, MyBankAccountCommand.Action.DEPOSIT, 500),
            MyBankAccountCommand(bankAccount, MyBankAccountCommand.Action.WITHDRAW, 300),
            MyBankAccountCommand(bankAccount, MyBankAccountCommand.Action.WITHDRAW, 5000),
            MyBankAccountCommand(bankAccount, MyBankAccountCommand.Action.DEPOSIT, 100)
    )
    for (c in commands) {
        c.call()
        println(bankAccount)
    }
    for (c in commands.reversed()) {
        c.undo()
        println(bankAccount)
    }

}

interface MyCommand {
    fun call()
    fun undo()
}

// implement command pattern thru constructor params
class MyBankAccountCommand(private val account: MyBankAccount,
                           var action: Action, var amount: Int): MyCommand {
    var isSuccessful = false
    enum class Action {
        DEPOSIT, WITHDRAW
    }
    override fun call() {
        isSuccessful = when (action) {
            Action.DEPOSIT -> {
                account.deposit(amount)
                true
            }
            Action.WITHDRAW -> {
                account.withdraw(amount)
            }
        }
    }

    override fun undo() {
        if (!isSuccessful) {
            return
        }
        when (action) {
            Action.DEPOSIT -> {
                account.withdraw(amount)
            }
            Action.WITHDRAW -> {
                account.deposit(amount)
            }
        }
    }
}

class MyBankAccount(var balance: Int = 0, private val overDraftLimit: Int = -500) {

    fun deposit(amount: Int) {
        balance += amount
        println("Deposited: $amount, Balance: $balance")
    }

    fun withdraw(amount: Int): Boolean {
        if (balance - amount >= overDraftLimit) {
            balance -= amount
            println("Withdrew: $amount, Balance: $balance")
            return true
        } else {
            println("$amount > overdraft limit")
            return false
        }
    }

    override fun toString(): String {
        return "Balance: $balance"
    }
}