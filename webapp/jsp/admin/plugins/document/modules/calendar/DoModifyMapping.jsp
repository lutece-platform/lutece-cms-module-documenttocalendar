<%@ page errorPage="../../../../ErrorPage.jsp"%>

<jsp:useBean id="documentToCalendar" scope="session" class="fr.paris.lutece.plugins.document.modules.calendar.web.DocumentToCalendarJspBean" />

<%
	documentToCalendar.init( request, documentToCalendar.RIGHT_MANAGE_DOCUMENTTOCALENDAR);
	response.sendRedirect(documentToCalendar.doModifyMapping(request));
%>
