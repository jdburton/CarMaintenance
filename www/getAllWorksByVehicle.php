<?php
 
/*
 * Following code will list all the works
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 

// check for post data
if (isset($_GET["Vehicle_idVehicle"])) {

	$Vehicle_idVehicle = 	$_GET["Vehicle_idVehicle"];
	// get all works from works table
	$result = mysql_query("select idWork, Vehicle_idVehicle, Items_idItem, Receipt_idReceipt, WorkMileage, WorkNotes, create_time, update_time from Work where Vehicle_idVehicle=$Vehicle_idVehicle") or die(mysql_error());
 
	// check for empty result
	if (mysql_num_rows($result) > 0) {
	    // looping through all results
	    // works node
	    $response["Work"] = array();
	 
	    while ($row = mysql_fetch_array($result)) {
	        // temp user array
	        $work = array();
			$work["idWork"] = $row["idWork"];
			$work["Vehicle_idVehicle"] = $row["Vehicle_idVehicle"];
			$work["Items_idItem"] = $result["Items_idItem"];
			$work["Receipt_idReceipt"] = $result["Receipt_idReceipt"];
			$work["WorkMileage"] = $result["WorkMileage"];
			$work["WorkNotes"] = $result["WorkNotes"];
	        $work["create_time"] = $row["create_time"];
	        $work["update_time"] = $row["update_time"];
	 
	        // push single work into final response array
	        array_push($response["works"], $work);
	    }
	    // success
	    $response["success"] = 1;
	 
	    // echoing JSON response
	    echo json_encode($response);
	} else {
	    // no works found
	    $response["success"] = 0;
	    $response["message"] = "No works found";
	 
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
