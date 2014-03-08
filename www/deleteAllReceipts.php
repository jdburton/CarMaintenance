<?php

/*
 * Following code will delete a receipt from table
* A receipt is identified by receipt id (pid)
*/

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['sure'])) {
	$sure = $_POST['sure'];

	// safety on. 
	if (strcasecmp($sure, "YES") == 0)
	{
		// include db connect class
		require_once __DIR__ . '/db_connect.php';
	
		// connecting to db
		$db = new DB_CONNECT();
	
		// mysql update row with matched pid
		$result = mysql_query("truncate table Receipt");
	
		// check if row deleted or not
		if (mysql_affected_rows() > 0) {
			// successfully updated
			$response["success"] = 1;
			$response["message"] = "Receipt successfully deleted";
	
			// echoing JSON response
			echo json_encode($response);
		} else {
			// no receipt found
			$response["success"] = 0;
			$response["message"] = "No receipt found";
	
			// echo no users JSON
			echo json_encode($response);
		}
	}
	else {
		// required field is missing
		$response["success"] = 0;
		$response["message"] = "Data in table Receipt not deleted. You weren't sure.";
	
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
