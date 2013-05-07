/*
 * Copyright (c) 2002-2013, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.document.modules.calendar.web;

import fr.paris.lutece.plugins.calendar.business.CalendarHome;
import fr.paris.lutece.plugins.calendar.business.category.Category;
import fr.paris.lutece.plugins.calendar.business.category.CategoryHome;
import fr.paris.lutece.plugins.calendar.service.AgendaResource;
import fr.paris.lutece.plugins.calendar.service.CalendarPlugin;
import fr.paris.lutece.plugins.calendar.web.CalendarJspBean;
import fr.paris.lutece.plugins.document.business.Document;
import fr.paris.lutece.plugins.document.business.DocumentHome;
import fr.paris.lutece.plugins.document.business.attributes.DocumentAttribute;
import fr.paris.lutece.plugins.document.business.attributes.DocumentAttributeHome;
import fr.paris.lutece.plugins.document.modules.calendar.business.CalendarAttribute;
import fr.paris.lutece.plugins.document.modules.calendar.business.CalendarAttributeHome;
import fr.paris.lutece.plugins.document.modules.calendar.business.Mapping;
import fr.paris.lutece.plugins.document.modules.calendar.business.MappingAttribute;
import fr.paris.lutece.plugins.document.modules.calendar.business.MappingAttributeHome;
import fr.paris.lutece.plugins.document.modules.calendar.business.MappingHome;
import fr.paris.lutece.plugins.document.modules.calendar.service.MappingService;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.web.admin.PluginAdminPageJspBean;
import fr.paris.lutece.portal.web.upload.MultipartHttpServletRequest;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.url.UrlItem;

import org.apache.commons.fileupload.FileItem;

import java.io.IOException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *  This class provides the user interface to manage DocumentToCalendarJspBean
 */
public class DocumentToCalendarJspBean extends PluginAdminPageJspBean
{
    public static final String RIGHT_MANAGE_DOCUMENTTOCALENDAR = "DOCUMENTTOCALENDAR_MANAGEMENT";

    // Templates
    private static final String TEMPLATE_MANAGE_DOCUMENTTOCALENDAR = "/admin/plugins/document/modules/calendar/manage_documenttocalendar.html";
    private static final String TEMPLATE_MODIFY_MAPPING = "/admin/plugins/document/modules/calendar/modify_mapping.html";
    private static final String TEMPLATE_CREATE_MAPPING = "/admin/plugins/document/modules/calendar/create_mapping.html";
    private static final String TEMPLATE_MODIFY_ATTRIBUTE_MAPPING = "/admin/plugins/document/modules/calendar/modify_mapping_attribute.html";
    private static final String TEMPLATE_SELECT_CALENDAR = "/admin/plugins/document/modules/calendar/select_calendar.html";
    private static final String TEMPLATE_CREATE_EVENT = "admin/plugins/document/modules/calendar/create_event.html";
    private static final String MARK_MAPPING_ATTR = "mapping_attribute";
    private static final String MARK_CALENDAR_ATTR_LIST = "calendar_attribute_list";
    private static final String MARK_CALENDAR_LIST = "calendar_list";
    private static final String MARK_MAPPING = "mapping";
    private static final String MARK_MAPPING_ATTR_LIST = "mapping_attribute_list";
    private static final String MARK_CODE_DOCUMENT_TYPE_LIST = "code_document_type_list";
    private static final String MARK_ID_DOCUMENT = "id_document";
    private static final String MARK_ID_CALENDAR = "calendar_id";
    private static final String MARK_INTERVAL_LIST = "interval_list";
    private static final String MARK_INTERVAL_TIME_SPAN = "time_span";
    private static final String MARK_NUMBER_DAYS = "number_days";
    private static final String MARK_TOP_EVENT_LIST = "top_event_list";
    private static final String MARK_TOP_EVENT_DEFAULT = "top_event_default";
    private static final String MARK_EMAIL_NOTIFY = "notify";
    private static final String MARK_INSERT_SERVICE_PAGE = "insert_service_page";
    private static final String MARK_INSERT_SERVICE_LINK_PAGE = "insert_service_link_page";
    private static final String MARK_WEBAPP_URL = "webapp_url";
    private static final String MARK_CALENDAR_ID = "calendar_id";
    private static final String MARK_LOCALE = "locale";
    private static final String MARK_CATEGORY_LIST = "category_list";
    private static final String MARK_CATEGORY_DEFAULT_LIST = "category_default_list";

    // Parameters
    private static final String PARAMETER_DOCUMENT_FEATURE_TITLE = "document.create_document.labelDocumentTitle";
    private static final String PARAMETER_DOCUMENT_FEATURE_SUMARY = "document.create_document.labelDocumentSummary";
    private static final String PARAMETER_MAPPING_CODE_DOCUMENT_TYPE = "mapping_code_document_type";
    private static final String PARAMETER_MAPPING_LIST = "mapping_list";
    private static final String PARAMETER_CANCEL = "cancel";
    private static final String PARAMETER_MAPPING_DESCRIPTION = "mapping_description";
    private static final String PARAMETER_ID_MAPPING_ATTR = "mapping_attribute_id";
    private static final String PARAMETER_ID_CALENDAR_ATTR = "calendar_attr_id";
    private static final String PARAMETER_ID_DOCUMENT = "id_document";
    private static final String PARAMETER_ID_CALENDAR = "calendar_id";
    private static final String PARAMETER_EVENT_IMAGE = "event_image";
    private static final String PARAMETER_TYPE_TEXT = "text";
    private static final String PARAMETER_TYPE_NUMERICTEXT = "numerictext";
    private static final String PARAMETER_TYPE_FILE = "file";
    private static final String PARAMETER_TYPE_DATE = "date";

    // JSP
    private static final String JSP_URL_REMOVE_MAPPING = "jsp/admin/plugins/document/modules/calendar/DoRemoveMapping.jsp";
    private static final String JSP_URL_MANAGE_MAPPING = "jsp/admin/plugins/document/modules/calendar/ManageDocumentToCalendar.jsp";
    private static final String JSP_URL_MODIFY_MAPPING = "jsp/admin/plugins/document/modules/calendar/ModifyMapping.jsp";
    private static final String JSP_URL_CREATE_EVENT = "jsp/admin/plugins/document/modules/calendar/CreateEvent.jsp";
    private static final String JSP_GET_INSERT_SERVICE = "jsp/admin/plugins/calendar/GetInsertService.jsp";
    private static final String JSP_GET_INSERT_LINK_SERVICE = "jsp/admin/plugins/calendar/GetInsertLinkService.jsp";
    private static final String JSP_GET_DOCUMENT_FILE = "jsp/admin/plugins/document/modules/calendar/GetFile.jsp";

    // Message
    private static final String MESSAGE_MAPPING_ERROR = "module.document.calendar.message.mappingError";
    private static final String MESSAGE_CONFIRM_REMOVE_MAPPING = "module.document.calendar.message.confirmRemoveMapping";
    private static final String MESSAGE_MAPPING_DESCRIPTION_ERROR = "module.document.calendar.message.mappingDescriptionError";
    private static final String MESSAGE_MAPPING_CODE_DOCUMENT_ERROR = "module.document.calendar.message.mappingCodeDocumentError";
    private static final String MESSAGE_MAPPING_ATTRIBUT_ERROR = "module.document.calendar.message.mappingAttributeError";
    private static final String MESSAGE_MAPPING_CALENDAR_ERROR = "module.document.calendar.message.mappingCalendarError";
    private static final String MESSAGE_DOCUMENT_ERROR = "module.document.calendar.message.documentError";
    private static final String MESSAGE_CALENDAR_ERROR = "module.document.calendar.message.calendarError";

    // Properties
    private static final String PROPERTY_MANAGE_MAPPING_TITLE = "module.document.calendar.manageMapping.title";
    private static final String PROPERTY_MODIFY_MAPPING_TITLE = "module.document.calendar.modifyMapping.title";
    private static final String PROPERTY_CREATE_MAPPING_TITLE = "module.document.calendar.createMapping.title";
    private static final String PROPERTY_MODIFY_MAPPING_ATTRIBUTE_TITLE = "module.document.calendar.modifyMappingAtribute.title";
    private static final String PROPERTY_SELECT_CALENDAR_TITLE = "module.document.calendar.selectCalendar.title";
    private static final String PROPERTY_CREATE_EVENT_TITLE = "module.document.calendar.createEvent.title";
    private static final String PROPERTY_TOP_EVENT_DEFAULT = "calendar.topevent.default";
    private static final String PROPERTY_EMAIL_NOTIFY = "calendar.email.notify";
    private static final String PROPERTY_TIME_INTERVAL_LIST = "calendar.interval.time";
    private static final String PROPERTY_TOP_EVENT_LIST = "calendar.topevent.values";

    /**
     * Gets the main mapping management page
     * @param request The HTTP request
     * @return The  mapping creation page
     */
    public String getManageDocumentToCalendar( HttpServletRequest request )
    {
        Plugin plugin = getPlugin(  );

        setPageTitleProperty( PROPERTY_MANAGE_MAPPING_TITLE );

        Map<String, Object> model = new HashMap<String, Object>(  );

        List<Mapping> listMapping = MappingHome.getList( plugin );

        model.put( PARAMETER_MAPPING_LIST, listMapping );

        HtmlTemplate templateList = AppTemplateService.getTemplate( TEMPLATE_MANAGE_DOCUMENTTOCALENDAR, getLocale(  ),
                model );

        return getAdminPage( templateList.getHtml(  ) );
    }

    /**
     * Returns the page of confirmation for deleting a mapping
     *
     * @param request The Http Request
     * @return the confirmation url
     */
    public String getConfirmRemoveMapping( HttpServletRequest request )
    {
        String strCodeDocumentType = request.getParameter( PARAMETER_MAPPING_CODE_DOCUMENT_TYPE );

        UrlItem url = new UrlItem( JSP_URL_REMOVE_MAPPING );
        url.addParameter( PARAMETER_MAPPING_CODE_DOCUMENT_TYPE, strCodeDocumentType );

        return AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_MAPPING, url.getUrl(  ),
            AdminMessage.TYPE_CONFIRMATION );
    }

    /**
     * Perform the deletion
     * @param request The HTTP request
     * @return The URL to go after performing the action
     */
    public String doRemoveMapping( HttpServletRequest request )
    {
        String strCodeDocumentType = request.getParameter( PARAMETER_MAPPING_CODE_DOCUMENT_TYPE );
        Plugin plugin = getPlugin(  );

        if ( ( strCodeDocumentType == null ) )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_MAPPING_ERROR, AdminMessage.TYPE_ERROR );
        }

        //Remove the mapping
        MappingHome.remove( strCodeDocumentType, plugin );
        //Remove the mapping attributes
        MappingAttributeHome.remove( strCodeDocumentType, plugin );

        return AppPathService.getBaseUrl( request ) + JSP_URL_MANAGE_MAPPING;
    }

    /**
     * Gets the mapping creation page
     * @param request The HTTP request
     * @return The  mapping creation page
     */
    public String getCreateMapping( HttpServletRequest request )
    {
        List<Mapping> documentTypeWithoutMappingList = MappingService.getCodeDocumentTypeWithoutMapping(  );

        Map<String, Object> model = new HashMap<String, Object>(  );

        model.put( MARK_CODE_DOCUMENT_TYPE_LIST, documentTypeWithoutMappingList );
        setPageTitleProperty( PROPERTY_CREATE_MAPPING_TITLE );

        HtmlTemplate templateList = AppTemplateService.getTemplate( TEMPLATE_CREATE_MAPPING, getLocale(  ), model );

        return getAdminPage( templateList.getHtml(  ) );
    }

    /**
     * Perform the mapping creation
     * @param request The HTTP request
     * @return The URL to go after performing the action
     */
    public String doCreateMapping( HttpServletRequest request )
    {
        if ( request.getParameter( PARAMETER_CANCEL ) == null )
        {
            String strCodeDocumentType = request.getParameter( PARAMETER_MAPPING_CODE_DOCUMENT_TYPE );
            String strMappingDescription = request.getParameter( PARAMETER_MAPPING_DESCRIPTION );

            if ( ( strMappingDescription == null ) || ( strMappingDescription.equals( "" ) ) )
            {
                return AdminMessageService.getMessageUrl( request, MESSAGE_MAPPING_DESCRIPTION_ERROR,
                    AdminMessage.TYPE_STOP );
            }

            if ( strCodeDocumentType == null )
            {
                return AdminMessageService.getMessageUrl( request, MESSAGE_MAPPING_CODE_DOCUMENT_ERROR,
                    AdminMessage.TYPE_STOP );
            }

            Plugin plugin = getPlugin(  );

            Mapping mapping = new Mapping(  );
            mapping.setCodeDocumentType( strCodeDocumentType );
            mapping.setDescription( strMappingDescription );

            MappingHome.create( mapping, plugin );

            List<DocumentAttribute> documentAttributeList = MappingService.getDocumentAttributesByCodeDocument( strCodeDocumentType );

            for ( DocumentAttribute documentAttribute : documentAttributeList )
            {
                if ( documentAttribute.isRequired(  ) )
                {
                    MappingAttribute mappingAttribute = new MappingAttribute(  );
                    mappingAttribute.setCodeDocumentType( strCodeDocumentType );
                    mappingAttribute.setIdDocumentAttribute( documentAttribute.getId(  ) );
                    mappingAttribute.setIdCalendarAttribute( -1 );
                    mappingAttribute.setDocumentFeature( "" );
                    MappingAttributeHome.create( mappingAttribute, plugin );
                }
            }

            //Add document feature title like as a mapping attribute
            MappingAttribute mappingAttribute = new MappingAttribute(  );
            mappingAttribute.setCodeDocumentType( strCodeDocumentType );
            mappingAttribute.setIdDocumentAttribute( -1 );
            mappingAttribute.setIdCalendarAttribute( -1 );
            mappingAttribute.setDocumentFeature( PARAMETER_DOCUMENT_FEATURE_TITLE );
            MappingAttributeHome.create( mappingAttribute, plugin );

            //Add document feature sumary like as a mapping attribute
            mappingAttribute = new MappingAttribute(  );
            mappingAttribute.setCodeDocumentType( strCodeDocumentType );
            mappingAttribute.setIdDocumentAttribute( -1 );
            mappingAttribute.setIdCalendarAttribute( -1 );
            mappingAttribute.setDocumentFeature( PARAMETER_DOCUMENT_FEATURE_SUMARY );
            MappingAttributeHome.create( mappingAttribute, plugin );

            return AppPathService.getBaseUrl( request ) + JSP_URL_MODIFY_MAPPING + "?" +
            PARAMETER_MAPPING_CODE_DOCUMENT_TYPE + "=" + strCodeDocumentType;
        }

        return AppPathService.getBaseUrl( request ) + JSP_URL_MANAGE_MAPPING;
    }

    /**
     * Gets the mapping modification page
     * @param request The HTTP request
     * @return The mapping modification page
     */
    public String getModifyMapping( HttpServletRequest request )
    {
        String strCodeDocumentType = request.getParameter( PARAMETER_MAPPING_CODE_DOCUMENT_TYPE );
        Plugin plugin = getPlugin(  );

        Map<String, Object> model = new HashMap<String, Object>(  );

        Mapping mapping = MappingHome.findByCodeDocumentType( strCodeDocumentType, plugin );

        List<MappingAttribute> mappingAttributeList = MappingService.getDocumentMappingAttributeList( strCodeDocumentType );

        model.put( MARK_MAPPING, mapping );
        model.put( MARK_MAPPING_ATTR_LIST, mappingAttributeList );

        setPageTitleProperty( PROPERTY_MODIFY_MAPPING_TITLE );

        HtmlTemplate templateList = AppTemplateService.getTemplate( TEMPLATE_MODIFY_MAPPING, getLocale(  ), model );

        return getAdminPage( templateList.getHtml(  ) );
    }

    /**
     * Perform the mapping modification
     * @param request The HTTP request
     * @return The URL to go after performing the action
     */
    public String doModifyMapping( HttpServletRequest request )
    {
        if ( request.getParameter( PARAMETER_CANCEL ) == null )
        {
            String strCodeDocumentType = request.getParameter( PARAMETER_MAPPING_CODE_DOCUMENT_TYPE );
            String strMappingDescription = request.getParameter( PARAMETER_MAPPING_DESCRIPTION );

            if ( ( strMappingDescription == null ) || strMappingDescription.equals( "" ) )
            {
                return AdminMessageService.getMessageUrl( request, MESSAGE_MAPPING_DESCRIPTION_ERROR,
                    AdminMessage.TYPE_STOP );
            }

            Plugin plugin = getPlugin(  );
            Mapping mapping = MappingHome.findByCodeDocumentType( strCodeDocumentType, plugin );
            mapping.setDescription( strMappingDescription );
            MappingHome.update( mapping, plugin );
        }

        return AppPathService.getBaseUrl( request ) + JSP_URL_MANAGE_MAPPING;
    }

    /**
     * Gets the mapping attribute modification page
     * @param request The HTTP request
     * @return The  mapping creation page
     */
    public String getModifyMappingAttribute( HttpServletRequest request )
    {
        String strIdMappingAttribute = request.getParameter( PARAMETER_ID_MAPPING_ATTR );
        Plugin plugin = getPlugin(  );

        MappingAttribute mappingAttribute = MappingService.getMappingAttribute( Integer.parseInt( strIdMappingAttribute ) );

        Mapping mapping = MappingHome.findByCodeDocumentType( mappingAttribute.getCodeDocumentType(  ), plugin );

        Map<String, Object> model = new HashMap<String, Object>(  );

        List<CalendarAttribute> calendarAttributeList = MappingService.getCalendarAttributeListByIdMappingAttribute( Integer.parseInt( 
                    strIdMappingAttribute ) );

        model.put( MARK_MAPPING, mapping );
        model.put( MARK_MAPPING_ATTR, mappingAttribute );
        model.put( MARK_CALENDAR_ATTR_LIST, calendarAttributeList );

        setPageTitleProperty( PROPERTY_MODIFY_MAPPING_ATTRIBUTE_TITLE );

        HtmlTemplate templateList = AppTemplateService.getTemplate( TEMPLATE_MODIFY_ATTRIBUTE_MAPPING, getLocale(  ),
                model );

        return getAdminPage( templateList.getHtml(  ) );
    }

    /**
     * Perform the mapping attribute modification
     * @param request The HTTP request
     * @return The URL to go after performing the action
     */
    public String doModifyMappingAttribute( HttpServletRequest request )
    {
        String strIdMappingAttribute = request.getParameter( PARAMETER_ID_MAPPING_ATTR );
        Plugin plugin = getPlugin(  );

        if ( ( strIdMappingAttribute == null ) || strIdMappingAttribute.equals( "" ) )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_MAPPING_ATTRIBUT_ERROR, AdminMessage.TYPE_ERROR );
        }

        MappingAttribute mappingAttribute = MappingAttributeHome.findByPrimaryKey( Integer.parseInt( 
                    strIdMappingAttribute ), plugin );

        if ( mappingAttribute == null )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_MAPPING_ERROR, AdminMessage.TYPE_ERROR );
        }

        if ( request.getParameter( PARAMETER_CANCEL ) == null )
        {
            String strIdCalendarAttribute = request.getParameter( PARAMETER_ID_CALENDAR_ATTR );

            if ( ( strIdCalendarAttribute == null ) || strIdCalendarAttribute.equals( "" ) )
            {
                return AdminMessageService.getMessageUrl( request, MESSAGE_MAPPING_CALENDAR_ERROR,
                    AdminMessage.TYPE_ERROR );
            }

            mappingAttribute.setIdCalendarAttribute( Integer.parseInt( strIdCalendarAttribute ) );
            MappingAttributeHome.update( mappingAttribute, plugin );
        }

        UrlItem url = new UrlItem( AppPathService.getBaseUrl( request ) + JSP_URL_MODIFY_MAPPING );
        url.addParameter( PARAMETER_MAPPING_CODE_DOCUMENT_TYPE, mappingAttribute.getCodeDocumentType(  ) );

        return url.getUrl(  );
    }

    /**
     * Gets the cfalendar selection page
     * @param request The HTTP request
     * @return The  mapping creation page
     */
    public String getSelectCalendar( HttpServletRequest request )
    {
        String strIdDocument = request.getParameter( PARAMETER_ID_DOCUMENT );

        Plugin calendarPlugin = PluginService.getPlugin( CalendarPlugin.PLUGIN_NAME );

        List<AgendaResource> calendarList = CalendarHome.findAgendaResourcesList( calendarPlugin );

        //If there are many calendars, use the select calendar template
        if ( calendarList.size(  ) > 1 )
        {
            Map<String, Object> model = new HashMap<String, Object>(  );

            model.put( MARK_CALENDAR_LIST, calendarList );
            model.put( MARK_ID_DOCUMENT, strIdDocument );
            setPageTitleProperty( PROPERTY_SELECT_CALENDAR_TITLE );

            HtmlTemplate templateList = AppTemplateService.getTemplate( TEMPLATE_SELECT_CALENDAR, getLocale(  ), model );

            return getAdminPage( templateList.getHtml(  ) );
        }

        //else go to create event 
        else
        {
            return getCreateEvent( request );
        }
    }

    /**
     * Perform the calendar selection
     * @param request The HTTP request
     * @return The URL to go after performing the action
     */
    public String doSelectCalendar( HttpServletRequest request )
    {
        String strIdCalendar = request.getParameter( PARAMETER_ID_CALENDAR );
        String strIdDocument = request.getParameter( PARAMETER_ID_DOCUMENT );

        Plugin calendarPlugin = PluginService.getPlugin( CalendarPlugin.PLUGIN_NAME );

        if ( ( strIdDocument == null ) || strIdDocument.equals( "" ) )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_DOCUMENT_ERROR, AdminMessage.TYPE_ERROR );
        }

        if ( strIdCalendar == null )
        {
            List<AgendaResource> calendarList = CalendarHome.findAgendaResourcesList( calendarPlugin );

            if ( calendarList.size(  ) == 1 )
            {
                strIdCalendar = calendarList.get( 0 ).getId(  );
            }
            else
            {
                return AdminMessageService.getMessageUrl( request, MESSAGE_CALENDAR_ERROR, AdminMessage.TYPE_ERROR );
            }
        }

        if ( strIdCalendar.equals( "" ) )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_CALENDAR_ERROR, AdminMessage.TYPE_ERROR );
        }

        //Verify if the calendar exist
        AgendaResource calendar = CalendarHome.findAgendaResource( Integer.parseInt( strIdCalendar ), calendarPlugin );

        if ( calendar == null )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_CALENDAR_ERROR, AdminMessage.TYPE_ERROR );
        }
        else
        {
            UrlItem url = new UrlItem( AppPathService.getBaseUrl( request ) + JSP_URL_CREATE_EVENT );
            url.addParameter( MARK_ID_DOCUMENT, strIdDocument );
            url.addParameter( MARK_ID_CALENDAR, strIdCalendar );

            return url.getUrl(  );
        }
    }

    /**
     * Returns the Event creation form
     *
     * @param request The Http request
     * @return Html creation form
     */
    public String getCreateEvent( HttpServletRequest request )
    {
        String strIdDocument = request.getParameter( PARAMETER_ID_DOCUMENT );
        String strIdCalendar = request.getParameter( PARAMETER_ID_CALENDAR );

        //If the calendar Id is not specified, it means that there is a single calendar
        if ( strIdCalendar == null )
        {
            Plugin calendarPlugin = PluginService.getPlugin( CalendarPlugin.PLUGIN_NAME );

            List<AgendaResource> calendarList = CalendarHome.findAgendaResourcesList( calendarPlugin );

            strIdCalendar = calendarList.get( 0 ).getId(  ).toString(  );
        }

        Plugin calendarPlugin = PluginService.getPlugin( CalendarPlugin.PLUGIN_NAME );
        Plugin plugin = getPlugin(  );

        //The defaut number of day for the list
        int nDays = 1;

        //Retrieve category list
        Collection<Category> categoryList = CategoryHome.findAll( calendarPlugin );

        Map<String, Object> model = new HashMap<String, Object>(  );

        String strBooleanTimeSpan = "TRUE";
        model.put( MARK_INTERVAL_TIME_SPAN, strBooleanTimeSpan );
        model.put( MARK_CALENDAR_ID, Integer.parseInt( strIdCalendar ) );
        model.put( MARK_LOCALE, getLocale(  ).getLanguage(  ) );
        model.put( MARK_INTERVAL_LIST, getIntervalList( request ) );
        model.put( MARK_NUMBER_DAYS, nDays );
        model.put( MARK_INTERVAL_LIST, getIntervalList( request ) );
        model.put( MARK_TOP_EVENT_LIST, getTopEventList(  ) );
        model.put( MARK_EMAIL_NOTIFY, AppPropertiesService.getProperty( PROPERTY_EMAIL_NOTIFY ) );
        model.put( MARK_TOP_EVENT_DEFAULT, AppPropertiesService.getProperty( PROPERTY_TOP_EVENT_DEFAULT ) );
        model.put( MARK_INSERT_SERVICE_PAGE, JSP_GET_INSERT_SERVICE );
        model.put( MARK_INSERT_SERVICE_LINK_PAGE, JSP_GET_INSERT_LINK_SERVICE );
        model.put( MARK_WEBAPP_URL, AppPathService.getBaseUrl( request ) );
        model.put( MARK_CATEGORY_LIST, new HashMap<String, Object>(  ) );
        model.put( MARK_CATEGORY_DEFAULT_LIST, categoryList );

        model.put( MARK_ID_DOCUMENT, Integer.parseInt( strIdDocument ) );

        Document document = DocumentHome.findByPrimaryKey( Integer.parseInt( strIdDocument ) );

        List<MappingAttribute> mappingAttributeList = MappingService.getDocumentMappingAttributeList( document.getCodeDocumentType(  ) );

        for ( MappingAttribute mappingAttribute : mappingAttributeList )
        {
            if ( mappingAttribute.getIdCalendarAttribute(  ) != -1 )
            {
                CalendarAttribute calendarAttribute = CalendarAttributeHome.findByKey( mappingAttribute.getIdCalendarAttribute(  ),
                        plugin );
                DocumentAttribute documentAttribute = DocumentAttributeHome.findByPrimaryKey( mappingAttribute.getIdDocumentAttribute(  ) );

                if ( mappingAttribute.getIdDocumentAttribute(  ) != -1 )
                {
                    //Document Attribute
                    if ( calendarAttribute.getCalendarAttributeType(  ).equals( PARAMETER_TYPE_TEXT ) ||
                            calendarAttribute.getCalendarAttributeType(  ).equals( PARAMETER_TYPE_NUMERICTEXT ) ||
                            calendarAttribute.getCalendarAttributeType(  ).equals( PARAMETER_TYPE_DATE ) )
                    {
                        model.put( calendarAttribute.getCalendarAttributeBookMark(  ),
                            document.getAttribute( documentAttribute.getCode(  ) ).getTextValue(  ) );
                    }

                    if ( calendarAttribute.getCalendarAttributeType(  ).equals( PARAMETER_TYPE_FILE ) )
                    {
                        UrlItem url = new UrlItem( AppPathService.getBaseUrl( request ) + JSP_GET_DOCUMENT_FILE );
                        url.addParameter( MARK_ID_DOCUMENT, strIdDocument );
                        model.put( calendarAttribute.getCalendarAttributeBookMark(  ), url.getUrl(  ) );
                    }
                }
                else
                {
                    //Document feature
                    if ( mappingAttribute.getDocumentFeature(  ).equals( PARAMETER_DOCUMENT_FEATURE_TITLE ) )
                    {
                        //Title
                        model.put( calendarAttribute.getCalendarAttributeBookMark(  ), document.getTitle(  ) );
                    }

                    if ( mappingAttribute.getDocumentFeature(  ).equals( PARAMETER_DOCUMENT_FEATURE_SUMARY ) )
                    {
                        //Summary
                        model.put( calendarAttribute.getCalendarAttributeBookMark(  ), document.getSummary(  ) );
                    }
                }
            }
        }

        setPageTitleProperty( PROPERTY_CREATE_EVENT_TITLE );

        HtmlTemplate templateList = AppTemplateService.getTemplate( TEMPLATE_CREATE_EVENT, getLocale(  ), model );

        return getAdminPage( templateList.getHtml(  ) );
    }

    /**
     * Manage the files document before create event
     * @param request The HTTP request
     */
    public void doManageCreateEvent( HttpServletRequest request )
    {
        String strIdDocument = request.getParameter( PARAMETER_ID_DOCUMENT );

        //If there is a document file
        if ( ( strIdDocument != null ) && !strIdDocument.equals( "" ) )
        {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
            FileItem item = mRequest.getFile( PARAMETER_EVENT_IMAGE );

            //If this document file is not replace by another file
            if ( item.getSize(  ) < 1 )
            {
                Document document = DocumentHome.findByPrimaryKey( Integer.parseInt( strIdDocument ) );
                DocumentAttribute documentAttribute = document.getAttribute( PARAMETER_TYPE_FILE );

                byte[] fileContent = documentAttribute.getBinaryValue(  );
                String strMimeType = documentAttribute.getValueContentType(  );

                HttpSession session = request.getSession( true );
                session.setAttribute( CalendarJspBean.ATTRIBUTE_MODULE_DOCUMENT_TO_CALENDAR_CONTENT_FILE, fileContent );
                session.setAttribute( CalendarJspBean.ATTRIBUTE_MODULE_DOCUMENT_TO_CALENDAR_MIME_TYPE_FILE, strMimeType );
            }
        }
    }

    /**
     * Return the list of time intervals declared in properties file
     * @return A ReferenceList of time interval
     * @param request The HttpRequest
     */
    public ReferenceList getIntervalList( HttpServletRequest request )
    {
        StringTokenizer st = new StringTokenizer( AppPropertiesService.getProperty( PROPERTY_TIME_INTERVAL_LIST ), "," );
        ReferenceList timeIntervalList = new ReferenceList(  );

        while ( st.hasMoreElements(  ) )
        {
            String strIntervalName = st.nextToken(  ).trim(  );
            String strDescription = I18nService.getLocalizedString( "calendar.interval.periodicity." + strIntervalName +
                    ".description", getLocale(  ) );
            int nDays = AppPropertiesService.getPropertyInt( "calendar.interval." + strIntervalName + ".value", 7 );
            timeIntervalList.addItem( nDays, strDescription );
        }

        return timeIntervalList;
    }

    /**
     * Return the list [(0, no),(2,yes)]
     *
     * @return a refenceList
     */
    private ReferenceList getTopEventList(  )
    {
        ReferenceList list = new ReferenceList(  );
        StringTokenizer st = new StringTokenizer( I18nService.getLocalizedString( PROPERTY_TOP_EVENT_LIST, getLocale(  ) ),
                "," );
        int i = 0;

        while ( st.hasMoreElements(  ) )
        {
            list.addItem( i++, st.nextToken(  ).trim(  ) );
        }

        return list;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * Load the document file whose identifier is specified in request
     * @param request servlet request
     * @param response servlet response*
     * @throws IOException the io exception
     */
    public void getFile( HttpServletRequest request, HttpServletResponse response )
        throws IOException
    {
        String strIdDocument = request.getParameter( PARAMETER_ID_DOCUMENT );

        if ( ( strIdDocument != null ) && !strIdDocument.equals( "" ) )
        {
            Document document = DocumentHome.findByPrimaryKey( Integer.parseInt( strIdDocument ) );
            DocumentAttribute documentAttribute = document.getAttribute( PARAMETER_TYPE_FILE );

            byte[] fileContent = documentAttribute.getBinaryValue(  );
            String strMimeType = documentAttribute.getValueContentType(  );
            String strname = documentAttribute.getTextValue(  );

            response.setHeader( "Content-Disposition", "attachment;filename=\"" + strname + "\"" );
            response.setContentType( strMimeType );
            response.setHeader( "Cache-Control", "must-revalidate" );
            response.getOutputStream(  ).write( fileContent );
        }
    }
}
