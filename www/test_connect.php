<?php
include 'db_connect.php';

$db = new DB_CONNECT() or die ('Could not connect to the database server' . mysqli_connect_error());

echo 'connected successfully!';
?>