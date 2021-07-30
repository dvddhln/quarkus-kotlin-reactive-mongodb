package entity

import java.time.LocalDate

class Todo() {
    lateinit var title: String
    lateinit var description: String
    lateinit var created: LocalDate
    lateinit var id: String
    var completed: Boolean = false

}
