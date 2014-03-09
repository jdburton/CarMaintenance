<?php

/*
 * Following code will delete a item from table
* A item is identified by item id (pid)
*/

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['table_name'])) {
	$table_name = $_POST['table_name'];


	// include db connect class
	require_once __DIR__ . '/db_connect.php';

	// connecting to db
	$db = new DB_CONNECT();

	// truncate the table
	// foreign key workaround http://stackoverflow.com/questions/5452760/truncate-foreign-key-constrained-table
	mysql_query("SET FOREIGN_KEY_CHECKS=0");
	$result = mysql_query("truncate table $table_name");
	mysql_query("SET FOREIGN_KEY_CHECKS=1");
	
	// check if row deleted or not
	if ($result) {
		// successfully updated
		$response["success"] = 1;
		$response["message"] = "Table $table_name successfully deleted";

		// echoing JSON response
		echo json_encode($response);
	} else {
		// no item found
		$response["success"] = 0;
		$response["message"] = "Table $table_name not deleted";

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
