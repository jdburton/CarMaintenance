<?php
 
/*
 * Following code will update a location information
 * A location is identified by location id (pid)
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['idLocation']) && isset($_POST['LocationDescription'])) {
 
    $idLocation = $_POST['idLocation'];
    $LocationDescription = $_POST['LocationDescription'];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql update row with matched pid
    $result = mysql_query("update Location set LocationDescription='$LocationDescription' where idLocation=$idLocation");
 
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