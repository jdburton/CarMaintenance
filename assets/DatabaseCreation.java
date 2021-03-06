

"CREATE TABLE \"Vehicle\"( \n" +
"  \"idVehicle\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
"  \"VehicleDescription\" VARCHAR(255) NOT NULL,\n" +
"  CONSTRAINT \"VehicleDescription_UNIQUE\"\n" +
"    UNIQUE(\"VehicleDescription\")\n" +
");\n"
" +CREATE TABLE \"Location\"(\n" +
"  \"idLocation\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
"  \"LocationDescription\" VARCHAR(255) NOT NULL,\n" +
"  CONSTRAINT \"LocationDescription_UNIQUE\"\n" +
"    UNIQUE(\"LocationDescription\")\n" +
");\n" +
"CREATE TABLE \"Item\"(\n" +
"  \"idItem\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
"  \"ItemDescription\" VARCHAR(255) NOT NULL,\n" +
"  \"ItemMileageInterval\" INTEGER NOT NULL,\n" +
"  \"ItemTimeInterval\" INTEGER NOT NULL,\n" +
"  CONSTRAINT \"ItemDescription_UNIQUE\"\n" +
"    UNIQUE(\"ItemDescription\")\n" +
");\n" +
"CREATE TABLE \"Receipt\"(\n" +
"  \"idReceipt\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
"  \"ReceiptFile\" VARCHAR(255) NOT NULL,\n" +
"  \"Location_idLocation\" INTEGER NOT NULL,\n" +
"  \"ReceiptDate\" DATETIME,\n" +
"  \"ReceiptAmount\" INTEGER,\n" +
"  \"ReceiptMileage\" INTEGER NOT NULL,\n" +
"  \"ReceiptNotes\" VARCHAR(255),\n" +
"  CONSTRAINT \"ReceiptFile_UNIQUE\"\n" +
"    UNIQUE(\"ReceiptFile\"),\n" +
"  CONSTRAINT \"fk_Receipt_Location1\"\n" +
"    FOREIGN KEY(\"Location_idLocation\")\n" +
"    REFERENCES \"Location\"(\"idLocation\")\n" +
");\n" +
"CREATE INDEX \"Receipt.fk_Receipt_Location1_idx\" ON \"Receipt\"(\"Location_idLocation\");\n" +
"CREATE TABLE \"Work\"(\n" +
"  \"idWork\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
"  \"Vehicle_idVehicle\" INTEGER NOT NULL,\n" +
"  \"Items_idItem\" INTEGER NOT NULL,\n" +
"  \"Receipt_idReceipt\" INTEGER NOT NULL,\n" +
"  \"WorkNotes\" VARCHAR(255),\n" +
"  CONSTRAINT \"fk_Work_Vehicle1\"\n" +
"    FOREIGN KEY(\"Vehicle_idVehicle\")\n" +
"    REFERENCES \"Vehicle\"(\"idVehicle\"),\n" +
"  CONSTRAINT \"fk_Work_Items1\"\n" +
"    FOREIGN KEY(\"Items_idItem\")\n" +
"    REFERENCES \"Item\"(\"idItem\"),\n" +
"  CONSTRAINT \"fk_Work_Receipt1\"\n" +
"    FOREIGN KEY(\"Receipt_idReceipt\")\n" +
"    REFERENCES \"Receipt\"(\"idReceipt\")\n" +
");\n" +
"CREATE INDEX \"Work.fk_Work_Vehicle1_idx\" ON \"Work\"(\"Vehicle_idVehicle\");\n" +
"CREATE INDEX \"Work.fk_Work_Items1_idx\" ON \"Work\"(\"Items_idItem\");\n" +
"CREATE INDEX \"Work.fk_Work_Receipt1_idx\" ON \"Work\"(\"Receipt_idReceipt\");\n" +

