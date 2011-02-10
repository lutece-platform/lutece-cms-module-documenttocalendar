--
-- Dumping data for table core_admin_right
--
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url) VALUES 
('DOCUMENTTOCALENDAR_MANAGEMENT','module.document.calendar.adminFeature.documenttocalendar_management.name',1,'jsp/admin/plugins/document/modules/calendar/ManageDocumentToCalendar.jsp','module.document.calendar.adminFeature.documenttocalendar_management.description',0,'documenttocalendar','APPLICATIONS','images/admin/skin/plugins/calendar/calendar.png',NULL);


--
-- Dumping data for table core_user_right
--
INSERT INTO core_user_right (id_right,id_user) VALUES ('DOCUMENTTOCALENDAR_MANAGEMENT',1);
INSERT INTO core_user_right (id_right,id_user) VALUES ('DOCUMENTTOCALENDAR_MANAGEMENT',2);
