<?php
 
/*
 * Following code will list all the locations
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// get all locations from locations table
$result = mysql_query("select idLocation, LocationDescription, create_time, update_time from Location") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // locations node
    $response["Location"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $location = array();
		$location["idLocation"] = $row["idLocation"];
		$location["LocationDescription"] = $row["LocationDescription"];
        $location["create_time"] = $row["create_time"];
        $location["update_time"] = $row["update_time"];
 
        // push single location into final response array
        array_push($response['Location'],$location);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no locations found
    $response["success"] = 0;
    $response["message"] = "No vehicles found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>
