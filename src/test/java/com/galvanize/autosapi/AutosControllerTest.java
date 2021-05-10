package com.galvanize.autosapi;

public class AutosControllerTest {
  //GETALL
// GET: /api/autos returns list of all autos in database
// GET: /api/autos no autos in database returns 204 no content
// GET: /api/autos?color=BLUE returns blue cars
// GET: /api/autos?make=Honda returns all Honda cars
// GET: /api/autos?make=Honda&color=SILVER returns all silver Honda cars

  // GET
// GET: /api/autos/{vin} returns specific car with the VIN number specified
// GET: /api/autos/{vin} returns NoContent 204 error

  //POST
// POST: /api/autos returns a newly created car
// POST: /api/autos?make=Ford returns a newly created Ford car
// POST: /api/autos?color=BLUE returns a newly created blue car
// POST: /api/autos?make=Ford&color=GOLD returns a newly created gold Ford car
// POST: /api/autos returns a 400 error if there is a bad request

  //PATCH
// PATCH: /api/autos/{vin} updates and returns the specified vehicle
// PATCH: /api/autos/{vin} returns a 204 error if vehicle is not found
// PATCH: /api/autos/{vin} returns a 400 error if there is a bad request


  //DELETE
// DELETE: /api/autos/{vin} Returns 202 response if delete request accepted
// DELETE: /api/autos/{vin} Returns 204 error if vehicle is not found
// DELETE: /api/autos/{vin} Returns 400 error if there is a bad request



}
