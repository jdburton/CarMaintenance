<?php
 
/*
 * Following code will get single work details
 * A work is identified by work id (pid)
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// check for post data
if (isset( $_GET["Vehicle_idVehicle"]) && isset( $_GET["Items_idItem"]) && isset($_GET["Receipt_idReceipt"])) {
	$Vehicle_idVehicle = $_GET['Vehicle_idVehicle'];
    $Items_idItem = $_GET['Items_idItem'];
    $Receipt_idReceipt = $_GET['Receipt_idReceipt'];
 
    // get a work from works table
    $result = mysql_query("select idWork, Vehicle_idVehicle, Items_idItem, Receipt_idReceipt, WorkMileage, WorkNotes, create_time, update_time from Work where Items_idItem=$Items_idItem and Receipt_idReceipt=$Receipt_idReceipt");
 
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
 
            $result = mysql_fetch_array($result);
 
            $work = array();
            $work["idWork"] = $result["idWork"];
            $work["Vehicle_idVehicle"] = $result["Vehicle_idVehicle"];
            $work["Items_idItem"] = $result["Items_idItem"];
            $work["Receipt_idReceipt"] = $result["Receipt_idReceipt"];
            $work["WorkMileage"] = $result["WorkMileage"];
            $work["WorkNotes"] = $result["WorkNotes"];
            $work["create_time"] = $result["create_time"];
            $work["update_time"] = $result["update_time"];
            // success
            $response["success"] = 1;
 
            // user node
            $response["work"] = array();
 
            array_push($response["work"], $work);
 
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no work found
            $response["success"] = 0;
            $response["message"] = "No work found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no work found
        $response["success"] = 0;
        $response["message"] = "No work found";
 
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
