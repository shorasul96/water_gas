# Water & Gas monitoring app

Created by shorasul996@gmail.com

## Installation

Use the **POSTGRES** [download](https://www.postgresql.org/download/) to work with dataBase.

```
create database with name water_gas
change username & password from application.property files
run app
```

Use the java 1.8 or higher [download](https://java.com/en/download/) to work with dataBase.




## REST API
**User** Entity
``` javascript
http://localhost:8090/api/v1/users          // get all users
http://localhost:8090/api/v1/user/{id}      // get by id
http://localhost:8090/api/v1/user/create    // POST new user
```

**Water** Entity
``` javascript
http://localhost:8090/api/v1/water           // get all waters
http://localhost:8090/api/v1/water/{id}      // get by user id
http://localhost:8090/api/v1/water/create    // POST new water
```

**Gas** Entity
``` javascript
http://localhost:8090/api/v1/gas           // get all gas
http://localhost:8090/api/v1/gas/{id}      // get by user id
http://localhost:8090/api/v1/gas/create    // POST new gas
```
