<?php
 
/*
 * Following code will update a item information
 * A item is identified by item id (pid)
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['idItem']) && isset($_POST['ItemDescription']) && isset($_POST['ItemMileageInterval']) && isset($_POST['ItemTimeInterval'])) {

 
    $idItem = $_POST['idItem'];
    $ItemDescription = $_POST['ItemDescription'];
    $ItemMileageInterval = $_POST['ItemMileageInterval'];
    $ItemTimeInterval = $_POST['ItemTimeInterval'];
    
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql update row with matched pid
    $result = mysql_query("update Item set ItemDescription=$ItemDescription, ItemMileageInterval=$ItemMileageInterval, ItemTimeInterval=$ItemTimeInterval  where idItem=$idItem");
 
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
