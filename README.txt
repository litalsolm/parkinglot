This Spring Boot app is the backend to a parkinglot app.
To run the app open a cmd, navigate to the root of the project via command line and execute the command "mvn spring-boot:run".
To be honest, I build this app on an old computer and the cmd doesn't work here, so if the above line doesn't work, just open the project in your favourite java code editor and run MyAppApplication.java from there (you will need Spring Boot for that).



Once the app is running, you can:

- view the database on http://localhost:8080/h2 (assuming H2 is installed on your pc). 
username: sa
password: 1234

- use Postman for get, post and put requests on http://localhost:8080.

The Parkinglot has 300 parking spaces. Spaces 1-100 are on the first floor, ect.
When a new vehicle enters the parking lot, it is added to the database alongside the information of the parking space(s) it occupies. If it requests to occupie an already occupied parking space, it will result in an error. A bus can only park on the first floor (spaces 1-100), and it occupies 4 adjacent spaces. A car occupies one space. Two motorbikes can occupie one space.
The cost of the parkinglot: 1st hour 3$, 2nd and 3rd hours 2$, 4th hour and above 1$ per hour.



Entities:

-Vehicle(long id, Type type, LocalDateTime entryTime, LocalDateTime exitTime, double cost)
Type is an Enum that describes the type of vehicle: Car, Bus or Motorbike.

-ParkingSpace(long id, Status status)
Status is an Enum that describes the status of the parking space: Free, Occupied or PartlyOccupied.




API Documentation:

- @GetMapping("/vehicles")
Get a list of all the vehicles that have entered the parkinglot (also the ones that already left).

- @GetMapping("/vehicles/{id}")
Get Vehicle object by its id

 @GetMapping("/vehicles/cost/{id}")
 Get cost of parking by vehicle id (assuming the vehicle has already exited)
 
  @PostMapping("/vehicles/parking/{id_list}")
  Add a new vehicle to the parkinglot in parking space(s) id_list.
  Example 1: if a car just parked in parking space 10, we create a post request on http://localhost:8080/vehicles/parking/10, and we sent as an input body the Vehicle object:
  {
    "type": "Car"
    }
    
  Example 2: if a bus just parked in parking spaces 10-13, we create a post request on http://localhost:8080/vehicles/parking/10,11,12,13, and we sent as an input body the Vehicle object:
  {
    "type": "Bus"
    }
    
  - @PutMapping("/vehicles/{id}")
  Update vehicle information by id, giving the updated Vehicle object in the input body.
  
  - @PutMapping("/vehicles/exit/{id}")
  When a vehicle leaves the parkinglot and the parking space(s) it occupies are now free (id = vehicle id). In the output we can see the exit time and the cost of the parking.
  
  -  @GetMapping("/parkinglot")
  Get all parking spaces
  
  - @GetMapping("/parkinglot/{id}")
  Get parking space by id
  
  
  
  




