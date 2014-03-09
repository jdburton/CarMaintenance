<?php


/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// Testing with curl http://stackoverflow.com/questions/1087185/http-testing-tool-easily-send-post-get-put

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['ItemDescription']) && isset($_POST['ItemMileageInterval']) && isset($_POST['ItemTimeInterval'])) {

	$ItemDescription = $_POST['ItemDescription'];
	$ItemMileageInterval = $_POST['ItemMileageInterval'];
	$ItemTimeInterval = $_POST['ItemTimeInterval'];

	// include db connect class
	require_once __DIR__ . '/db_connect.php';

	// connecting to db
	$db = new DB_CONNECT();

	// mysql inserting a new row
	$insert_result = mysql_query("INSERT INTO Item (ItemDescription, ItemMileageInterval, ItemTimeInterval) VALUES('$ItemDescription', '$ItemMileageInterval', '$ItemTimeInterval')");

	// check if row inserted or not
	if ($insert_result) {
		
		// get a item from items table
		$result = mysql_query("select idItem, ItemDescription, ItemMileageInterval, ItemTimeInterval, create_time, update_time from Item where ItemDescription='$ItemDescription'");
		
		if (!empty($result)) {
			// check for empty result
			if (mysql_num_rows($result) > 0) {
		
				$result = mysql_fetch_array($result);
		
				$item = array();
				$item["idItem"] = $result["idItem"];
				$item["ItemDescription"] = $result["ItemDescription"];
				$item["ItemMileageInterval"] = $result["ItemMileageInterval"];
				$item["ItemTimeInterval"] = $result["ItemTimeInterval"];
				$item["create_time"] = $result["create_time"];
				$item["update_time"] = $result["update_time"];
				
				// user node
				$response["Item"] = array();
		
				array_push($response["Item"], $item);
		
				// successfully inserted into database
				$response["success"] = 1;
				$response["message"] = "Item successfully created.";
				
				
				// echoing JSON response
				echo json_encode($response);
			} else {
				// no item found
				$response["success"] = 0;
				$response["message"] = "Item created, but not found";
		
				// echo no users JSON
				echo json_encode($response);
			}
		} else {
			// no item found
			$response["success"] = 0;
			$response["message"] = "Oops! An error occurred.";
		
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
