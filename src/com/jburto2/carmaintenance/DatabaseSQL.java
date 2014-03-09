/**
 * 
 */
package com.jburto2.carmaintenance;

/**
 * @author jburton
 *
 */
public class DatabaseSQL {


	public static final String CREATE_VEHICLE = "CREATE TABLE \"Vehicle\"( \n" +
	"  \"idVehicle\" INTEGER PRIMARY KEY NOT NULL,\n" +
	"  \"VehicleDescription\" VARCHAR(255) NOT NULL,\n" +
	"  \"create_time\" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n"+
	"  \"update_time\" TIMESTAMP,\n"+
	"  CONSTRAINT \"VehicleDescription_UNIQUE\"\n" +
	"    UNIQUE(\"VehicleDescription\")\n" +
	");\n";
	
	public static final String CREATE_LOCATION = "CREATE TABLE \"Location\"(\n" +
	"  \"idLocation\" INTEGER PRIMARY KEY NOT NULL,\n" +
	"  \"LocationDescription\" VARCHAR(255) NOT NULL,\n" +
	"  \"create_time\" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n"+
	"  \"update_time\" TIMESTAMP,\n"+
	"  CONSTRAINT \"LocationDescription_UNIQUE\"\n" +
	"    UNIQUE(\"LocationDescription\")\n" +
	");\n";
	
	public static final String CREATE_ITEM = 	"CREATE TABLE \"Item\"(\n" +
	"  \"idItem\" INTEGER PRIMARY KEY NOT NULL,\n" +
	"  \"ItemDescription\" VARCHAR(255) NOT NULL,\n" +
	"  \"ItemMileageInterval\" INTEGER NOT NULL,\n" +
	"  \"ItemTimeInterval\" INTEGER NOT NULL,\n" +
	"  \"create_time\" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n"+
	"  \"update_time\" TIMESTAMP,\n"+
	"  CONSTRAINT \"ItemDescription_UNIQUE\"\n" +
	"    UNIQUE(\"ItemDescription\")\n" +
	");\n";
	
	public static final String CREATE_RECEIPT = "CREATE TABLE \"Receipt\"(\n" +
	"  \"idReceipt\" INTEGER PRIMARY KEY NOT NULL,\n" +
	"  \"ReceiptFile\" VARCHAR(255) NOT NULL,\n" +
	"  \"Location_idLocation\" INTEGER NOT NULL,\n" +
	"  \"ReceiptDate\" DATE,\n" +
	"  \"ReceiptAmount\" INTEGER,\n" +
	"  \"ReceiptNotes\" VARCHAR(255),\n" +
	"  \"create_time\" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n"+
	"  \"update_time\" TIMESTAMP,\n"+
	"  CONSTRAINT \"Secondary\"\n" +
	"    UNIQUE(\"Location_idLocation\",\"ReceiptDate\"),\n"+
	"  CONSTRAINT \"ReceiptFile_UNIQUE\"\n" +
	"    UNIQUE(\"ReceiptFile\"),\n" +
	"  CONSTRAINT \"fk_Receipt_Location1\"\n" +
	"    FOREIGN KEY(\"Location_idLocation\")\n" +
	"    REFERENCES \"Location\"(\"idLocation\")\n" +
	");\n";
	
	public static final String RECIEPT_IDX = "CREATE INDEX \"Receipt.fk_Receipt_Location1_idx\" ON \"Receipt\"(\"Location_idLocation\");\n";
	
	
	public static final String CREATE_WORK = "CREATE TABLE \"Work\"(\n" +
	"  \"idWork\" INTEGER PRIMARY KEY NOT NULL,\n" +
	"  \"Vehicle_idVehicle\" INTEGER NOT NULL,\n" +
	"  \"Items_idItem\" INTEGER NOT NULL,\n" +
	"  \"Receipt_idReceipt\" INTEGER NOT NULL,\n" +
	"  \"WorkMileage\" INTEGER NOT NULL,\n" +
	"  \"WorkNotes\" VARCHAR(255),\n" +
	"  \"create_time\" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n"+
	"  \"update_time\" TIMESTAMP,\n"+
	"  CONSTRAINT \"Secondary\"\n" +
	"    UNIQUE(\"Vehicle_idVehicle\",\"Items_idItem\",\"Receipt_idReceipt\"),\n"+
	"  CONSTRAINT \"fk_Work_Vehicle1\"\n" +
	"    FOREIGN KEY(\"Vehicle_idVehicle\")\n" +
	"    REFERENCES \"Vehicle\"(\"idVehicle\"),\n" +
	"  CONSTRAINT \"fk_Work_Items1\"\n" +
	"    FOREIGN KEY(\"Items_idItem\")\n" +
	"    REFERENCES \"Item\"(\"idItem\"),\n" +
	"  CONSTRAINT \"fk_Work_Receipt1\"\n" +
	"    FOREIGN KEY(\"Receipt_idReceipt\")\n" +
	"    REFERENCES \"Receipt\"(\"idReceipt\")\n" +
	");\n";
	
	public static final String Work_IDX_VEHICLE = "CREATE INDEX \"Work.fk_Work_Vehicle1_idx\" ON \"Work\"(\"Vehicle_idVehicle\");\n";
	public static final String Work_IDX_ITEMS = 	"CREATE INDEX \"Work.fk_Work_Items1_idx\" ON \"Work\"(\"Items_idItem\");\n";
	public static final String Work_IDX_RECIEPT = "CREATE INDEX \"Work.fk_Work_Receipt1_idx\" ON \"Work\"(\"Receipt_idReceipt\");\n";
	

	

}
