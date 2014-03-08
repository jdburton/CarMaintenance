<?php


/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// Testing with curl http://stackoverflow.com/questions/1087185/http-testing-tool-easily-send-post-get-put

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['ReceiptFile']) && isset($_POST['Location_idLocation']) && isset($_POST['ReceiptDate']) && isset($_POST['ReceiptAmount']) && isset($_POST['ReceiptNotes'])) {

	$ReceiptFile = $_POST['ReceiptFile'];
	$Location_idLocation = $_POST['Location_idLocation'];
	$ReceiptDate = $_POST['ReceiptDate'];
	$ReceiptAmount = $_POST['ReceiptAmount'];
	$ReceiptNotes = $_POST['ReceiptNotes'];

	// include db connect class
	require_once __DIR__ . '/db_connect.php';

	// connecting to db
	$db = new DB_CONNECT();

	// mysql inserting a new row
	$result = mysql_query("INSERT INTO Receipt (ReceiptFile, Location_idLocation, ReceiptDate, ReceiptAmount, ReceiptNotes) VALUES('$ReceiptFile', '$Location_idLocation', '$ReceiptDate', '$ReceiptAmount', '$ReceiptNotes')");

	// check if row inserted or not
	if ($result) {
		// successfully inserted into database
		$response["success"] = 1;
		$response["message"] = "Receipt successfully created.";

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