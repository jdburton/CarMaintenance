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
	$insert_result = mysql_query("INSERT INTO Receipt (ReceiptFile, Location_idLocation, ReceiptDate, ReceiptAmount, ReceiptNotes) VALUES('$ReceiptFile', '$Location_idLocation', '$ReceiptDate', '$ReceiptAmount', '$ReceiptNotes')");

	// check if row inserted or not
	if ($insert_result) {
		
		// get a receipt from receipts table
		$result = mysql_query("select idReceipt, ReceiptFile, Location_idLocation, ReceiptDate, ReceiptAmount, ReceiptNotes, create_time, update_time from Receipt where ReceiptFile='$ReceiptFile'");
		
		if (!empty($result)) {
			// check for empty result
			if (mysql_num_rows($result) > 0) {
		
				$result = mysql_fetch_array($result);
		
				$receipt = array();
				$receipt["idReceipt"] = $result["idReceipt"];
				$receipt["ReceiptFile"] = $result["ReceiptFile"];
				$receipt["Location_idLocation"] = $result["Location_idLocation"];
				$receipt["ReceiptDate"] = $result["ReceiptDate"];
				$receipt["ReceiptAmount"] = $result["ReceiptAmount"];
				$receipt["ReceiptNotes"] = $result["ReceiptNotes"];
				$receipt["create_time"] = $result["create_time"];
				$receipt["update_time"] = $result["update_time"];
				// success
				$response["success"] = 1;
				$response["message"] = "Receipt successfully created.";
				
		
				// user node
				$response["Receipt"] = array();
		
				array_push($response["Receipt"], $receipt);
		
				// echoing JSON response
				echo json_encode($response);
			} else {
				// no receipt found
				$response["success"] = 0;
				$response["message"] = "No receipt found";
		
				// echo no users JSON
				echo json_encode($response);
			}
		} else {
			// no receipt found
			$response["success"] = 0;
			$response["message"] = "No receipt found";
		
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
