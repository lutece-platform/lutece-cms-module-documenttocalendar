<%@ page errorPage="../../../../ErrorPage.jsp"%>

<jsp:useBean id="documentToCalendar" scope="session" class="fr.paris.lutece.plugins.document.modules.calendar.web.DocumentToCalendarJspBean" />
<jsp:useBean id="calendar" scope="session" class="fr.paris.lutece.plugins.calendar.web.CalendarJspBean" />

<%
	documentToCalendar.init( request, documentToCalendar.RIGHT_MANAGE_DOCUMENTTOCALENDAR);
	calendar.init( request, fr.paris.lutece.plugins.calendar.web.CalendarJspBean.RIGHT_MANAGE_CALENDAR );
	documentToCalendar.doManageCreateEvent( request );
	response.sendRedirect( calendar.doCreateEvent( request ) );
%>

