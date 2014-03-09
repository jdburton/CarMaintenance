<?php
 
/*
 * Following code will update a receipt information
 * A receipt is identified by receipt id (pid)
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['idReceipt']) && isset($_POST['ReceiptFile']) && isset($_POST['Location_idLocation']) && isset($_POST['ReceiptDate']) && isset($_POST['ReceiptAmount']) && isset($_POST['ReceiptNotes'])) {

 
    $idReceipt = $_POST['idReceipt'];
    $ReceiptFile = $_POST['ReceiptFile'];
    $Location_idLocation = $_POST['Location_idLocation'];
    $ReceiptDate = $_POST['ReceiptDate'];
    $ReceiptAmount = $_POST["ReceiptAmount"];
    $ReceiptNotes = $_POST["ReceiptNotes"];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql update row with matched pid
    $result = mysql_query("update Receipt set ReceiptFile='$ReceiptFile', Location_idLocation=$Location_idLocation, ReceiptDate='$ReceiptDate', ReceiptAmount='$ReceiptAmount', ReceiptNotes='$ReceiptNotes'  where idReceipt=$idReceipt");
 
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
