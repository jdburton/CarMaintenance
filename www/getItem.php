<?php

/*
 * Following code will get single item details
* A item is identified by item id (pid)
*/

// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET["ItemDescription"])) {
	$ItemDescription = $_GET['ItemDescription'];

	// get a item from items table
	$result = mysql_query("select idItem, ItemDescription, ItemMileageInterval, ItemTimeInterval, create_time, update_time from Item where ItemDescription=$ItemDescription");

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
			// success
			$response["success"] = 1;

			// user node
			$response["item"] = array();

			array_push($response["item"], $item);

			// echoing JSON response
			echo json_encode($response);
		} else {
			// no item found
			$response["success"] = 0;
			$response["message"] = "No item found";

			// echo no users JSON
			echo json_encode($response);
		}
	} else {
		// no item found
		$response["success"] = 0;
		$response["message"] = "No item found";

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
