<?php

/*
 * Following code will get single receipt details
* A receipt is identified by receipt id (pid)
*/

// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET["ReceiptFile"])) {
	$ReceiptFile = $_GET['ReceiptFile'];

	// get a receipt from receipts table
	$result = mysql_query("select idReceipt, ReceiptFile, Location_idLocation, ReceiptDate, ReceiptAmount, ReceiptNotes, create_time, update_time from Receipt where ReceiptFile=$ReceiptFile");

	if (!empty($result)) {
		// check for empty result
		if (mysql_num_rows($result) > 0) {

			$result = mysql_fetch_array($result);

			$receipt = array();
			$receipt["idReceipt"] = $result["idReceipt"];
			$receipt["ReceiptFile"] = $result["ReceiptFile"];
			$receipt["Location_idLocation"] = $result["ReceiptDate"];
			$receipt["ReceiptDate"] = $result["ReceiptDate"];
			$receipt["ReceiptAmount"] = $result["ReceiptAmount"];
			$receipt["ReceiptNotes"] = $result["ReceiptNotes"];
			$receipt["create_time"] = $result["create_time"];
			$receipt["update_time"] = $result["update_time"];
			// success
			$response["success"] = 1;

			// user node
			$response["receipt"] = array();

			array_push($response["receipt"], $receipt);

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
	// required field is missing
	$response["success"] = 0;
	$response["message"] = "Required field(s) is missing";

	// echoing JSON response
	echo json_encode($response);
}
?>
