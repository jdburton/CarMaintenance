<?php
 
/*
 * Following code will update a vehicle information
 * A vehicle is identified by vehicle id (pid)
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['idVehicle']) && isset($_POST['VehicleDescription'])) {
 
    $idVehicle = $_POST['idVehicle'];
    $VehicleDescription = $_POST['VehicleDescription'];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql update row with matched pid
    $result = mysql_query("update Vehicle set VehicleDescription='$VehicleDescription' where idVehicle=$idVehicle");
 
    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully updated.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
 
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>