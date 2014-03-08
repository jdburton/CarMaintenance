<?php
 
/*
 * Following code will list all the vehicles
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// get all vehicles from vehicles table
$result = mysql_query("select idVehicle, VehicleDescription, create_time, update_time from Vehicle") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // vehicles node
    $response["Vehicle"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $vehicle = array();
		$vehicle["idVehicle"] = $row["idVehicle"];
		$vehicle["VehicleDescription"] = $row["VehicleDescription"];
        $vehicle["create_time"] = $row["create_time"];
        $vehicle["update_time"] = $row["update_time"];
 
        // push single vehicle into final response array
        array_push($response["vehicles"], $vehicle);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no vehicles found
    $response["success"] = 0;
    $response["message"] = "No vehicles found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>
