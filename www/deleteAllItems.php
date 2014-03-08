<?php

/*
 * Following code will delete a item from table
* A item is identified by item id (pid)
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
		$result = mysql_query("truncate table Item");
	
		// check if row deleted or not
		if (mysql_affected_rows() > 0) {
			// successfully updated
			$response["success"] = 1;
			$response["message"] = "Item successfully deleted";
	
			// echoing JSON response
			echo json_encode($response);
		} else {
			// no item found
			$response["success"] = 0;
			$response["message"] = "No item found";
	
			// echo no users JSON
			echo json_encode($response);
		}
	}
	else {
		// required field is missing
		$response["success"] = 0;
		$response["message"] = "Data in table Item not deleted. You weren't sure.";
	
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
