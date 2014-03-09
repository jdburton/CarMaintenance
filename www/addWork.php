<?php


/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// Testing with curl http://stackoverflow.com/questions/1087185/http-testing-tool-easily-send-post-get-put

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['Vehicle_idVehicle']) && isset($_POST['Items_idItem']) && isset($_POST['Receipt_idReceipt']) && isset($_POST['WorkMileage']) && isset($_POST['WorkNotes'])) {

	$Vehicle_idVehicle = $_POST['Vehicle_idVehicle'];
	$Items_idItem = $_POST['Items_idItem'];
	$Receipt_idReceipt = $_POST['Receipt_idReceipt'];
	$WorkMileage = $_POST['WorkMileage'];
	$WorkNotes = $_POST['WorkNotes'];

	// include db connect class
	require_once __DIR__ . '/db_connect.php';

	// connecting to db
	$db = new DB_CONNECT();

	// mysql inserting a new row
	$insert_result = mysql_query("INSERT INTO Work (Vehicle_idVehicle, Items_idItem, Receipt_idReceipt, WorkMileage, WorkNotes) VALUES('$Vehicle_idVehicle', '$Items_idItem', '$Receipt_idReceipt', '$WorkMileage', '$WorkNotes')");

	// check if row inserted or not
	if ($insert_result) {
		
		
		// get a work from works table
		$result = mysql_query("select idWork, Vehicle_idVehicle, Items_idItem, Receipt_idReceipt, WorkMileage, WorkNotes, create_time, update_time from Work where Vehicle_idVehicle=$Vehicle_idVehicle and Items_idItem=$Items_idItem and Receipt_idReceipt=$Receipt_idReceipt");
		
		if (!empty($result)) {
			// check for empty result
			if (mysql_num_rows($result) > 0) {
		
				$result = mysql_fetch_array($result);
		
				$work = array();
				$work["idWork"] = $result["idWork"];
				$work["Vehicle_idVehicle"] = $result["Vehicle_idVehicle"];
				$work["Items_idItem"] = $result["Items_idItem"];
				$work["Receipt_idReceipt"] = $result["Receipt_idReceipt"];
				$work["WorkMileage"] = $result["WorkMileage"];
				$work["WorkNotes"] = $result["WorkNotes"];
				$work["create_time"] = $result["create_time"];
				$work["update_time"] = $result["update_time"];
				// success
				$response["success"] = 1;
				$response["message"] = "Work successfully created.";
		
				// user node
				$response["Work"] = array();
		
				array_push($response["Work"], $work);
		
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
