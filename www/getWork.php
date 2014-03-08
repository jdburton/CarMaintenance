<?php

/*
 * Following code will get single work details
* A work is identified by work id (pid)
*/

// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET["Vehicle_idVehicle"])) {
	$Vehicle_idVehicle = $_GET['Vehicle_idVehicle'];

	// get a work from works table
	$result = mysql_query("select idWork, Vehicle_idVehicle, Items_idItem, Receipt_idReceipt, WorkMileage, WorkNotes, create_time, update_time from Work where Vehicle_idVehicle=$Vehicle_idVehicle");

	if (!empty($result)) {
		// check for empty result
		if (mysql_num_rows($result) > 0) {

			$result = mysql_fetch_array($result);

			$work = array();
			$work["idWork"] = $result["idWork"];
			$work["Vehicle_idVehicle"] = $result["Vehicle_idVehicle"];
			$work["Items_idItem"] = $result["Receipt_idReceipt"];
			$work["Receipt_idReceipt"] = $result["Receipt_idReceipt"];
			$work["WorkMileage"] = $result["WorkMileage"];
			$work["WorkNotes"] = $result["WorkNotes"];
			$work["create_time"] = $result["create_time"];
			$work["update_time"] = $result["update_time"];
			// success
			$response["success"] = 1;

			// user node
			$response["work"] = array();

			array_push($response["work"], $work);

			// echoing JSON response
			echo json_encode($response);
		} else {
			// no work found
			$response["success"] = 0;
			$response["message"] = "No work found";

			// echo no users JSON
			echo json_encode($response);
		}
	} else {
		// no work found
		$response["success"] = 0;
		$response["message"] = "No work found";

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
