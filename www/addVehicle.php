<?php


/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// Testing with curl http://stackoverflow.com/questions/1087185/http-testing-tool-easily-send-post-get-put

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['VehicleDescription'])) {

	$VehicleDescription = $_POST['VehicleDescription'];

	// include db connect class
	require_once __DIR__ . '/db_connect.php';

	// connecting to db
	$db = new DB_CONNECT();

	// mysql inserting a new row
	$insert_result = mysql_query("INSERT INTO Vehicle (VehicleDescription) VALUES('$VehicleDescription')");
	
	
	// check if row inserted or not
	if ($insert_result) 
	{


		// get a vehicle from vehicles table
		$result = mysql_query("select idVehicle, VehicleDescription, create_time, update_time from Vehicle where VehicleDescription='$VehicleDescription'");
		
		if (!empty($result)) 
		{
			// check for empty result
			if (mysql_num_rows($result) > 0) 
			{
		
				$result = mysql_fetch_array($result);
		
				$vehicle = array();
				$vehicle["idVehicle"] = $result["idVehicle"];
				$vehicle["VehicleDescription"] = $result["VehicleDescription"];
				$vehicle["create_time"] = $result["create_time"];
				$vehicle["update_time"] = $result["update_time"];
				// success
				$response["success"] = 1;
		
				// user node
				$response["Vehicle"] = array();
		
				array_push($response["Vehicle"], $vehicle);
				
				// successfully inserted into database
				$response["success"] = 1;
				$response["message"] = "Vehicle created successfully.";
				// echoing JSON response
				echo json_encode($response);
		
			}
			else
			{
				// failed to insert row
				$response["success"] = 0;
				$response["message"] = "Vehicle created, but cannot be found.";
			
			}
			
		}
		else 
		{
			// failed to insert row
			$response["success"] = 0;
			$response["message"] = "Vehicle created, but cannot be found.";
				
		}
		
	} else 
	{
		// failed to insert row
		$response["success"] = 0;
		$response["message"] = "Oops! An error occurred.";

		// echoing JSON response
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