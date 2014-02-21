-- Creator:       MySQL Workbench 6.0.9/ExportSQLite plugin 2009.12.02
-- Author:        James Burton
-- Caption:       New Model
-- Project:       Name of the project
-- Changed:       2014-02-20 13:54
-- Created:       2014-02-18 07:48
PRAGMA foreign_keys = OFF;

-- Schema: mydb

CREATE TABLE "Vehicle"(
  "idVehicle" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "VehicleDescription" VARCHAR(255) NOT NULL,
  CONSTRAINT "VehicleDescription_UNIQUE"
    UNIQUE("VehicleDescription")
);
CREATE TABLE "Location"(
  "idLocation" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "LocationDescription" VARCHAR(255) NOT NULL,
  CONSTRAINT "LocationDescription_UNIQUE"
    UNIQUE("LocationDescription")
);
CREATE TABLE "Item"(
  "idItem" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "ItemDescription" VARCHAR(255) NOT NULL,
  "ItemMileageInterval" INTEGER NOT NULL,
  "ItemTimeInterval" INTEGER NOT NULL,
  CONSTRAINT "ItemDescription_UNIQUE"
    UNIQUE("ItemDescription")
);
CREATE TABLE "Receipt"(
  "idReceipt" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "ReceiptFile" VARCHAR(255) NOT NULL,
  "Location_idLocation" INTEGER NOT NULL,
  "ReceiptDate" DATETIME,
  "ReceiptAmount" INTEGER,
  "ReceiptMileage" INTEGER NOT NULL,
  "ReceiptNotes" VARCHAR(255),
  CONSTRAINT "ReceiptFile_UNIQUE"
    UNIQUE("ReceiptFile"),
  CONSTRAINT "fk_Receipt_Location1"
    FOREIGN KEY("Location_idLocation")
    REFERENCES "Location"("idLocation")
);
CREATE INDEX "Receipt.fk_Receipt_Location1_idx" ON "Receipt"("Location_idLocation");
CREATE TABLE "Work"(
  "idWork" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "Vehicle_idVehicle" INTEGER NOT NULL,
  "Items_idItem" INTEGER NOT NULL,
  "Receipt_idReceipt" INTEGER NOT NULL,
  "WorkNotes" VARCHAR(255),
  CONSTRAINT "fk_Work_Vehicle1"
    FOREIGN KEY("Vehicle_idVehicle")
    REFERENCES "Vehicle"("idVehicle"),
  CONSTRAINT "fk_Work_Items1"
    FOREIGN KEY("Items_idItem")
    REFERENCES "Item"("idItem"),
  CONSTRAINT "fk_Work_Receipt1"
    FOREIGN KEY("Receipt_idReceipt")
    REFERENCES "Receipt"("idReceipt")
);
CREATE INDEX "Work.fk_Work_Vehicle1_idx" ON "Work"("Vehicle_idVehicle");
CREATE INDEX "Work.fk_Work_Items1_idx" ON "Work"("Items_idItem");
CREATE INDEX "Work.fk_Work_Receipt1_idx" ON "Work"("Receipt_idReceipt");

