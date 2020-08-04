# Gas & Water Usage Monitoring Application

###### __Created by shorasul996@gmail.com__

## Installation

Use the **java 1.8** or **higher** [download](https://java.com/en/download/) to working environment 

Use the **POSTGRES 10** [download](https://www.postgresql.org/download/) to work with dataBase.

```
create database with name water_gas
change username & password from application.property files

run Rest_API
port:8090
```





## REST API
**User** Entity
``` javascript
localhost:8090/api/v1/users          // get all users
localhost:8090/api/v1/user/{id}      // get by id (get history)
localhost:8090/api/v1/user/          // POST new user (submit)
```

**Water** Entity
``` javascript
localhost:8090/api/v1/water           // get all waters
localhost:8090/api/v1/water/{id}      // get by user id (get history)
localhost:8090/api/v1/water/          // POST new water (submit)
```

**Gas** Entity
``` javascript
localhost:8090/api/v1/gas           // get all gas
localhost:8090/api/v1/gas/{id}      // get by user id (get history)
localhost:8090/api/v1/gas/          // POST new gas (submit)
```
