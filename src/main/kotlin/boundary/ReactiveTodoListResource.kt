package boundary

import entity.Todo
import entity.TodoList
import org.bson.types.ObjectId
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/v1/todos")
class ReactiveTodoListResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun todos() = TodoList.streamAll()

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun todos(@PathParam("id") id: ObjectId) = TodoList.findById(id)

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun create(todoList: TodoList) = todoList.persist<TodoList>()

    @PUT
    @Path("{id}")
    fun update(
        @PathParam("id") todoListId: ObjectId, todo: Todo
    ) = TodoList.addTodoToList(todoListId, todo)

    @PUT
    @Path("{id}/{todoId}/complete")
    @Produces(MediaType.APPLICATION_JSON)
    fun complete(
        @PathParam("id") listId: ObjectId, @PathParam("todoId") todoId: String,
    ) = TodoList.completeTodo(listId, todoId)

    @PUT
    @Path("{id}/{todoId}/incomplete")
    @Produces(MediaType.APPLICATION_JSON)
    fun incomplete(
        @PathParam("id") listId: ObjectId, @PathParam("todoId") todoId: String,
    ) = TodoList.incompleteTodo(listId, todoId)

    @DELETE
    @Path("{id}/{todoId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteTodo(
        @PathParam("id") listId: ObjectId, @PathParam("todoId") todoId: String,
    ) = TodoList.removeTodoToList(listId, todoId)

    @DELETE
    @Path("{id}")
    fun deleteTodoList(@PathParam("id") id: ObjectId) =
        TodoList.deleteById(id).map { t ->
            return@map if (t) Response.noContent().build() else
                Response.noContent().status(Response.Status.NOT_FOUND).build()
        }
}