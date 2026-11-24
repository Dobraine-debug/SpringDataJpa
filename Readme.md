**INSTALLATION AND STARTING**

The Project comes pre-packaged, so no installation is necessary. 

Simply run *Start\_linux* or *Start\_windows* depending on the operative system.



**LIST OF ENDPOINTS**



**GET /vehicles**

Lists all vehicles.



**GET /vehicles-brand-info**
Lists all registration numbers and the brand of their associated vehicle.



**GET /vehicles-by-brand**

**Parameters:** brand(String)

Lists all vehicles with the associated brand matching the parameter.



**POST /vehicles**

**Parameters:** registrationNumber(String), brand(String), model(String), productionYear(int)

Creates a new vehicle based on the parameters.



**PUT /vehicles/{id}/brand**

**Variable:** id(Long)

**Parameter:** brand(String)

Updates the brand of the car with the id matching the variable to the parameter.



**GET /customers**

Lists all customers and their associated vehicles.



**GET /customer-id**

**Parameter:** name(String)

Returns the customer ID of the customer with the name matching the parameter.



**POST /customers**

**Parameters:** name(String), phone(String)

Creates a new customer based on the parameters.



**POST /addvehicletocustomer**

**Parameters:** customerId(Long), vehicleId(Long)

Associates the vehicle matching the vehicle ID with the customer matching the customer ID.

