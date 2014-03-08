<?php

/*
 * Following code will get single vehicle details
* A vehicle is identified by vehicle id (pid)
*/

// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET["VehicleDescription"])) {
	$VehicleDescription = $_GET['VehicleDescription'];

	// get a vehicle from vehicles table
	$result = mysql_query("select idVehicle, VehicleDescription, create_time, update_time from Vehicle where VehicleDescription=$VehicleDescription");

	if (!empty($result)) {
		// check for empty result
		if (mysql_num_rows($result) > 0) {

			$result = mysql_fetch_array($result);

			$vehicle = array();
			$vehicle["idVehicle"] = $result["idVehicle"];
			$vehicle["VehicleDescription"] = $result["VehicleDescription"];
			$vehicle["create_time"] = $result["create_time"];
			$vehicle["update_time"] = $result["update_time"];
			// success
			$response["success"] = 1;

			// user node
			$response["vehicle"] = array();

			array_push($response["vehicle"], $vehicle);

			// echoing JSON response
			echo json_encode($response);
		} else {
			// no vehicle found
			$response["success"] = 0;
			$response["message"] = "No vehicle found";

			// echo no users JSON
			echo json_encode($response);
		}
	} else {
		// no vehicle found
		$response["success"] = 0;
		$response["message"] = "No vehicle found";

		// echo no users JSON
		echo json_encode($response);
	}
} else {
	// required field is missing
	$response["success"] = 0;
	$response["message"] = "Required field(s) is missing";

	// echoing JSON response
	echo json_encode($response);
}
?>