1.  Driver
  1a.  http://localhost:8082/driver/register
    request :-
    {
    "firstName":"Bret",
    "lastName":"Pit",
    "dlNo":"DL00132",
    "vehicleType":"Essesntial",
    "registrationNo":"UP20BA007"
    }
 response:- CV3ozNiGET  (this is a customer id)

   1.b //To get all registered drivers 
   http://localhost:8082/driver/allDrivers
    request:-
    response:-  
          [
        {
            "id": 1,
            "firstName": "kushal",
            "lastName": "rastogi",
            "dlNo": "DL00132",
            "registrationNo": "UP21BA0743",
            "vehicleSegment": {
                "id": 1,
                "vehicleType": "Essesntial"
            }
        }
    ]
  
2. Customer

  2a.//To register a new customer
  request:-
      {
    "firstName":"James",
    "lastName":"Bond",
    "email":"james.bond@globallogic.com",
    "phoneNo":1007
    }
  response:- yTn1CXxov7 (this is a customer id)

  2b. //To fetch all the customers
  http://localhost:8081/customer/getAllCustomer

  request :-
  [
      {
          "id": 1,
          "cid": "IV6Jld4tsJ",
          "firstName": "James",
          "lastName": "Bond",
          "email": "james.bond@globallogic.com",
          "phoneNo": 007,
          "bookingEntityList": [
              {
                  "id": 1,
                  "booking_time": "2018-12-10T20:24:14",
                  "bid": "JtVgkYKoOW",
                  "status": "BOOKED"
              }
          ]
      }
  ]

  2c.//To fetch a customer by cid
  http://localhost:8081/customer/getCustomer/IV6Jld4tsJ
  response:-
  {
      "id": 1,
      "cid": "IV6Jld4tsJ",
      "firstName": "kushal",
      "lastName": "rastogi",
      "email": "test",
      "phoneNo": 4234,
      "bookingEntityList": [
          {
              "id": 1,
              "booking_time": "2018-12-10T20:24:14",
              "bid": "JtVgkYKoOW",
              "status": "BOOKED"
          },
          {
              "id": 2,
              "booking_time": "2018-12-10T20:36:45",
              "bid": "ezSkuefwA1",
              "status": "CANCELLED"
          },
          {
              "id": 3,
              "booking_time": "2018-12-10T20:36:48",
              "bid": "mXQ1TU7zTk",
              "status": "BOOKED"
          }
      ]
  }

  2d. //To cancel a booked ride 
  http://localhost:8081/customer/YDGh1xQUZB/cancel/CV3ozNiGET
  response:-



  2e.//To check the history of a customer's booking
  http://localhost:8081/customer/IV6Jld4tsJ/history
  response:-
  [
      {
          "id": 1,
          "booking_time": "2018-12-10T20:24:14",
          "bid": "JtVgkYKoOW",
          "status": "BOOKED"
      },
      {
          "id": 2,
          "booking_time": "2018-12-10T20:36:45",
          "bid": "ezSkuefwA1",
          "status": "CANCELLED"
      },
      {
          "id": 3,
          "booking_time": "2018-12-10T20:36:48",
          "bid": "mXQ1TU7zTk",
          "status": "BOOKED"
      }
  ]


3.Booking

  3a. //To book a ride
  http://localhost:8081/booking/yTn1CXxov7
  request:- need to pass booking customer unique id in url.
      {
        "cabType":"Essential",
        "startAddress":"Crossing",
        "destination":"Sector 135 Noida Global logic"
      }
  response:-
  Cab booked
