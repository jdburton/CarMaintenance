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
	$result = mysql_query("INSERT INTO Work (Vehicle_idVehicle, Items_idItem, Receipt_idReceipt, WorkMileage, WorkNotes) VALUES('$Vehicle_idVehicle', '$Items_idItem', '$Receipt_idReceipt', '$WorkMileage', '$WorkNotes')");

	// check if row inserted or not
	if ($result) {
		// successfully inserted into database
		$response["success"] = 1;
		$response["message"] = "Work successfully created.";

		// echoing JSON response
		echo json_encode($response);
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
