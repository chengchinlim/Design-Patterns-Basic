package behavioral

fun main() {
    val stateContext = AlertStateContext()
    stateContext.currentState = Vibration()
    stateContext.alert()

    stateContext.currentState = Silent()
    stateContext.alert()
}

class AlertStateContext {
    var currentState: MobileAlertState? = null
        set(value) {
            if (value != null) {
                field = value
            }
        }
    fun alert() {
        currentState!!.alert(this)
    }
}

class Vibration: MobileAlertState {
    override fun alert(context: AlertStateContext) {
        println("Vibrating...")
    }
}

class Silent: MobileAlertState {
    override fun alert(context: AlertStateContext) {
        println("...")
    }
}

interface MobileAlertState {
    fun alert(context: AlertStateContext)
}