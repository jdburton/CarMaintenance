<?php
 
/*
 * Following code will get single location details
 * A location is identified by location id (pid)
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// check for post data
if (isset($_GET["idLocation"])) {
    $idLocation = $_GET['idLocation'];
 
    // get a location from locations table
    $result = mysql_query("select idLocation, LocationDescription, create_time, update_time from Location where idLocation=$idLocation");
 
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
 
            $result = mysql_fetch_array($result);
 
            $location = array();
            $location["idLocation"] = $result["idLocation"];
            $location["LocationDescription"] = $result["LocationDescription"];
            $location["create_time"] = $result["create_time"];
            $location["update_time"] = $result["update_time"];
            // success
            $response["success"] = 1;
 
            // user node
            $response["location"] = array();
 
            array_push($response["location"], $location);
 
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no location found
            $response["success"] = 0;
            $response["message"] = "No location found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no location found
        $response["success"] = 0;
        $response["message"] = "No location found";
 
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