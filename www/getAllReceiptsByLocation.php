<?php
 
/*
 * Following code will list all the receipts
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 

// check for post data
if (isset($_GET["Location_idLocation"])) {

	$Location_idLocation = Location_idLocation;	
	// get all receipts from receipts table
	$result = mysql_query("select idReceipt, ReceiptFile, Location_idLocation, ReceiptDate, ReceiptAmount, ReceiptNotes, create_time, update_time from Receipt where Location_idLocation=$Location_idLocation") or die(mysql_error());
 
	// check for empty result
	if (mysql_num_rows($result) > 0) {
	    // looping through all results
	    // receipts node
	    $response["Receipt"] = array();
	 
	    while ($row = mysql_fetch_array($result)) {
	        // temp user array
	        $receipt = array();
			$receipt["idReceipt"] = $row["idReceipt"];
			$receipt["ReceiptFile"] = $row["ReceiptFile"];
			$receipt["Location_idLocation"] = $result["Location_idLocation"];
			$receipt["ReceiptDate"] = $result["ReceiptDate"];
			$receipt["ReceiptAmount"] = $result["ReceiptAmount"];
			$receipt["ReceiptNotes"] = $result["ReceiptNotes"];
	        $receipt["create_time"] = $row["create_time"];
	        $receipt["update_time"] = $row["update_time"];
	 
	        // push single receipt into final response array
	        array_push($response["receipts"], $receipt);
	    }
	    // success
	    $response["success"] = 1;
	 
	    // echoing JSON response
	    echo json_encode($response);
	} else {
	    // no receipts found
	    $response["success"] = 0;
	    $response["message"] = "No receipts found";
	 
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
