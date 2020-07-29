# Water & Gas monitoring app

###### __Created by shorasul996@gmail.com__

## Installation

Use the **POSTGRES 10** [download](https://www.postgresql.org/download/) to work with dataBase.

```
create database with name water_gas
change username & password from application.property files
run Rest_API
port 8090
```

Use the **java 1.8** or **higher** [download](https://java.com/en/download/) to work with dataBase.




## REST API
**User** Entity
``` javascript
http://localhost:8090/api/v1/users          // GET all users
http://localhost:8090/api/v1/user/{id}      // GET by id
http://localhost:8090/api/v1/user/create    // POST new user
```

**Water** Entity
``` javascript
http://localhost:8090/api/v1/water           // GET all waters
http://localhost:8090/api/v1/water/{id}      // GET by user id
http://localhost:8090/api/v1/water/create    // POST new water
```

**Gas** Entity
``` javascript
http://localhost:8090/api/v1/gas           // GET all gases
http://localhost:8090/api/v1/gas/{id}      // GET by user id
http://localhost:8090/api/v1/gas/create    // POST new gas
```
