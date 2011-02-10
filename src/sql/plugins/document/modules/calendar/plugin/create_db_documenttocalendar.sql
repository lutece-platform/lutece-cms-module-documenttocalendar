DROP TABLE IF EXISTS documenttocalendar_mapping;
DROP TABLE IF EXISTS documenttocalendar_mapping_attr;
DROP TABLE IF EXISTS documenttocalendar_calendar_attr;

/*==============================================================*/
/* Table structure for table documenttocalendar_mapping			*/
/*==============================================================*/

CREATE TABLE  documenttocalendar_mapping (
	code_document_type varchar(30) default '' NOT NULL,
	description varchar(255) default NULL
);

/*==============================================================*/
/* Table structure for table documenttocalendar_mapping_attr	*/
/*==============================================================*/

CREATE TABLE  documenttocalendar_mapping_attr (
	id_mapping_attr int default 0 NOT NULL,
	code_document_type varchar(30) default '' NOT NULL,
	id_document_attr varchar(30) default NULL,
	id_calendar_attr int default NULL,
	document_features varchar(50) NOT NULL
);


/*==============================================================*/
/* Table structure for table documenttocalendar_calendar_attr	*/
/*==============================================================*/

CREATE TABLE  documenttocalendar_calendar_attr (
	id_calendar_attr int  default 0 NOT NULL,
	calendar_attr_label varchar(50) default '' NOT NULL,
	calendar_attr_bookmark varchar(50) default '' NOT NULL,
	calendar_attr_type varchar(30) default '' NOT NULL
);

