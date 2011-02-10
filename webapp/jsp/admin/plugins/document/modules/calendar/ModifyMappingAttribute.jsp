<%@ page errorPage="../../../../ErrorPage.jsp" %>
<jsp:include page="../../../../AdminHeader.jsp" />
<jsp:useBean id="documentToCalendar" scope="session" class="fr.paris.lutece.plugins.document.modules.calendar.web.DocumentToCalendarJspBean" />
<%
	documentToCalendar.init( request, documentToCalendar.RIGHT_MANAGE_DOCUMENTTOCALENDAR);
%>
<%= documentToCalendar.getModifyMappingAttribute( request) %>

<%@ include file="../../../../AdminFooter.jsp" %>