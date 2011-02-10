<%@ page import="fr.paris.lutece.portal.service.util.AppLogService"%>
<jsp:useBean id="documentToCalendar" scope="session" class="fr.paris.lutece.plugins.document.modules.calendar.web.DocumentToCalendarJspBean" />
<%
	try {
		documentToCalendar.getFile(request,	response);
	} catch (Exception e) {
		AppLogService.error(e.getMessage(), e);
	} finally {
		response.getOutputStream().flush();
	}
%>
