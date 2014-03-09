<?php
 
/*
 * Following code will list all the items
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// get all items from items table
$result = mysql_query("select idItem, ItemDescription, ItemMileageInterval, ItemTimeInterval, create_time, update_time from Item") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // items node
    $response["Item"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $item = array();
		$item["idItem"] = $row["idItem"];
		$item["ItemDescription"] = $row["ItemDescription"];
		$item["ItemMileageInterval"] = $row["ItemMileageInterval"];
		$item["ItemTimeInterval"] = $row["ItemTimeInterval"];
        $item["create_time"] = $row["create_time"];
        $item["update_time"] = $row["update_time"];
 
        // push single item into final response array
        array_push($response['Item'],$item);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no items found
    $response["success"] = 0;
    $response["message"] = "No items found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>
