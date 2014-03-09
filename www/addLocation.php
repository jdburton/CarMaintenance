<?php


/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// Testing with curl http://stackoverflow.com/questions/1087185/http-testing-tool-easily-send-post-get-put

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['LocationDescription'])) {

	$LocationDescription = $_POST['LocationDescription'];

	// include db connect class
	require_once __DIR__ . '/db_connect.php';

	// connecting to db
	$db = new DB_CONNECT();

	// mysql inserting a new row
	$insert_result = mysql_query("INSERT INTO Location (LocationDescription) VALUES('$LocationDescription')");

	// check if row inserted or not
	if ($insert_result) {
		// get a location from locations table
		$result = mysql_query("select idLocation, LocationDescription, create_time, update_time from Location where LocationDescription='$LocationDescription'");
		
		if (!empty($result)) {
			// check for empty result
			if (mysql_num_rows($result) > 0) {
		
				$result = mysql_fetch_array($result);
		
				$location = array();
				$location["idLocation"] = $result["idLocation"];
				$location["LocationDescription"] = $result["LocationDescription"];
				$location["create_time"] = $result["create_time"];
				$location["update_time"] = $result["update_time"];
				// success
				$response["success"] = 1;
				$response["message"] = "Location successfully created.";
		
				// user node
				$response["Location"] = array();
		
				array_push($response["Location"], $location);
		
				// echoing JSON response
				echo json_encode($response);
			} else {
				// no location found
				$response["success"] = 0;
				$response["message"] = "No location found";
		
				// echo no users JSON
				echo json_encode($response);
			}
		} else {
			// no location found
			$response["success"] = 0;
			$response["message"] = "No location found";
		
			// echo no users JSON
			echo json_encode($response);
		}

	} else {
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