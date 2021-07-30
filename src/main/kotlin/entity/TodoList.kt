package entity

import io.quarkus.mongodb.panache.kotlin.reactive.ReactivePanacheMongoCompanion
import io.quarkus.mongodb.panache.kotlin.reactive.ReactivePanacheMongoEntity
import org.bson.types.ObjectId
import java.time.LocalDate
import java.util.*
import java.util.stream.Collectors

class TodoList : ReactivePanacheMongoEntity() {

    companion object : ReactivePanacheMongoCompanion<TodoList> {
        fun findByName(name: String) = find("name", name).firstResult()
        fun addTodoToList(todoListId: ObjectId, todo: Todo) =
            findById(todoListId).onItem().transform { list ->
                todo.id = UUID.randomUUID().toString()
                todo.created = LocalDate.now()
                list!!.todos.add(todo)
                return@transform list
            }.call { list -> list.update<TodoList>() }

        fun removeTodoToList(todoListId: ObjectId, todoId: String) =
            findById(todoListId).onItem().transform { list ->
                list!!.todos = list.todos.stream().filter { todo -> !todo.id.equals(todoId) }
                    .collect(Collectors.toList()) as ArrayList<Todo>
                return@transform list
            }.call { list -> list.update<TodoList>() }

        fun completeTodo(todoListId: ObjectId, todoId: String) =
            findById(todoListId).onItem().transform { list ->
                list!!.todos.stream().filter { todo -> todo.id.equals(todoId) }
                    .findAny().ifPresent { t -> t.completed = true }
                return@transform list
            }.call { list -> list.update<TodoList>() }

        fun incompleteTodo(todoListId: ObjectId, todoId: String) =
            findById(todoListId).onItem().transform { list ->
                list!!.todos.stream().filter { todo -> todo.id.equals(todoId) }
                    .findAny().ifPresent { t -> t.completed = false }
                return@transform list
            }.call { list -> list.update<TodoList>() }
    }

    lateinit var name: String
    var todos: ArrayList<Todo> = ArrayList()
}
