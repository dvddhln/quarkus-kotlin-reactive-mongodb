# quarkus-kotlin-reactive-mongodb Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

This project demonstrates the usage of the Quarkus Kotlin MongoDB with Panache extension on top with reactive endpoints.

This project is presented in the following article.

[Creating a Reactive CRUD todo app with Kotlin, MongoDB, Panache and Quarkus](https://dvddhln.medium.com/building-a-reactive-crud-todo-app-with-kotlin-mongodb-panache-and-quarkus-1bcac2335e23)


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

### Example Response

    [
    {
        "id": "61054ea1c547c3533bc06a24",
        "name": "Groceries",
        "todos": [
            {
                "title": "Avocado",
                "description": "Something Interesting",
                "created": "2021-07-31",
                "id": "3acf9399-55cc-4418-af07-4b194ef37b8b",
                "completed": false
            },
            {
                "title": "Bananas",
                "description": "Something nice",
                "created": "2021-07-31",
                "id": "ea1ebf70-62df-47a6-b5e4-f4a4c73c9eb1",
                "completed": false
            }
        ]
    },
    {
        "id": "61054ea7c547c3533bc06a25",
        "name": "Travel",
        "todos": [
            {
                "title": "Hotelbooking",
                "description": "Somewhere to stay",
                "created": "2021-07-31",
                "id": "3be2f3cc-32ea-4553-b099-45f6a3a440e0",
                "completed": false
            }
        ]
    }

## Create Todo List

    $ curl -X POST "localhost:8080/v1/todos"
    
    {"name":"Travel"}

## GET All Todo Lists

    $ curl "localhost:8080/v1/todos"

## GET Todo List by id

    $ curl "localhost:8080/v1/todos/61054ea1c547c3533bc06a24"

## Add Todo to Todo List

    $ curl -X PUT "localhost:8080/v1/todos/61054ea7c547c3533bc06a25"
    
    {
        {"title":"Hotelbooking","description":"Somewhere to stay"}
    }

## Delete Todo List by id

    $ curl -X DELETE "localhost:8080/v1/todos/60db4b085d2d613300cc136b"

## Delete Todo by list id and todo id

    $ curl -X DELETE "localhost:8080/v1/todos/60db4b085d2d613300cc136b/3b955596-442d-481a-947b-396d06bdd7b4"
