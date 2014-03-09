<?php
 
/*
 * Following code will update a work information
 * A work is identified by work id (pid)
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['idWork']) && isset($_POST['Vehicle_idVehicle']) && isset($_POST['Items_idItem']) && isset($_POST['Receipt_idReceipt']) && isset($_POST['WorkMileage']) && isset($_POST['WorkNotes'])) {

 
    $idWork = $_POST['idWork'];
    $Vehicle_idVehicle = $_POST['Vehicle_idVehicle'];
    $Items_idItem = $_POST['Items_idItem'];
    $Receipt_idReceipt = $_POST['Receipt_idReceipt'];
    $WorkMileage = $_POST["WorkMileage"];
    $WorkNotes = $_POST["WorkNotes"];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql update row with matched pid
    $result = mysql_query("update Work set Vehicle_idVehicle=$Vehicle_idVehicle, Items_idItem=$Items_idItem, Receipt_idReceipt=$Receipt_idReceipt, WorkMileage=$WorkMileage, WorkNotes='$WorkNotes'  where idWork=$idWork");
 
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
