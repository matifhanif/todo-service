# todo-service
RESTful API to manage todo list.

## Tech Stack:
* SDK: JDK 18.0.1.1 
* Build tool: Maven 
* Framework: Spring Boot 
* Database: H2 (in-memory)
* Docker
 
Key Libraries: 
* Spring Boot Data JPA
* JUnit, Spring Boot Starter Test 
* Spring Boot Starter Validation, Apache Commons Lang


## Service Description:
1.Get All Items
> **GET** localhost:8080/api/v1/todo/items


2.Get All Items that are marked not done.
> **GET** localhost:8080/api/v1/todo/items/notdone

3.Get specific items with Item Id (if exist).
> **GET** localhost:8080/api/v1/todo/item/{id}

| **Path Variable** | **Type** | **Description**       |
|-------------------|----------|-----------------------|
| id                | long     | **Required**. Item Id |


4.Save items.
> **POST** localhost:8080/api/v1/todo/item/

| **Request Parameter** | **Type**    | **Description**                                                                                      |
|-----------------------|-------------|------------------------------------------------------------------------------------------------------|
| Item                  | json object | **Required**. Example:{    "desc": "My Todo Item",   "status": 1,   "dtDue": "2022-08-08T19:33:52" } |


5.Update Description.
> **PUT** localhost:8080/api/v1/todo/item/{id}/desc

| **Path Variable** | **Type** | **Description**       |
|-------------------|----------|-----------------------|
| id                | long     | **Required**. Item Id |

| **Request Parameter** | **Type** | **Description** |
|-----------------------|----------|-----------------|
| desc                  | String   | **Required**.   |

6.Update Status.
> **PUT** localhost:8080/api/v1/todo/item/item/{id}/status

| **Path Variable** | **Type** | **Description**       |
|-------------------|----------|-----------------------|
| id                | long     | **Required**. Item Id |

| **Request Parameter** | **Type** | **Description**                                         |
|-----------------------|----------|---------------------------------------------------------|
| statusCode            | int      | **Required**.  Valid values are 0 (Not Done) & 1 (Done) |

## Design Decisions:
[Architecture decision record (ADR)](design/adr/)



# How to:

### Build service

> docker build -t todoService:1.0 .


### Run service locally

> docker run -p 8080:8080 todoService:1.0
