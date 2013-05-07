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
package fr.paris.lutece.plugins.document.modules.calendar.service;

import fr.paris.lutece.plugins.calendar.business.CalendarHome;
import fr.paris.lutece.plugins.calendar.service.AgendaResource;
import fr.paris.lutece.plugins.calendar.service.CalendarPlugin;
import fr.paris.lutece.plugins.calendar.service.CalendarResourceIdService;
import fr.paris.lutece.plugins.calendar.web.CalendarJspBean;
import fr.paris.lutece.plugins.document.business.Document;
import fr.paris.lutece.plugins.document.business.workflow.DocumentAction;
import fr.paris.lutece.plugins.document.modules.calendar.business.MappingHome;
import fr.paris.lutece.plugins.document.modules.calendar.web.DocumentToCalendarJspBean;
import fr.paris.lutece.plugins.document.service.IDocumentActionsService;
import fr.paris.lutece.portal.business.rbac.RBAC;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.rbac.RBACService;
import fr.paris.lutece.portal.web.admin.PluginAdminPageJspBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * This Service manages mapping actions
 */
public class DocumentActionsService extends PluginAdminPageJspBean implements IDocumentActionsService
{
    private static final String PLUGIN_NAME = "documenttocalendar";
    private static final String PARAMETER_BUTTON_NAME = "module.document.calendar.workflow.action.addEvent.name";
    private static final String PARAMETER_BUTTON_DESCRIPTION = "module.document.calendar.workflow.action.addEvent.description";
    private static final String PARAMETER_BUTTON_ACTION_URL = "jsp/admin/plugins/document/modules/calendar/SelectCalendar.jsp?";
    private static final String PARAMETER_BUTTON_ICON_URL = "images/admin/skin/actions/calendar_add.png";

    /**
     * Return all actions to add to the document
     * @param document The document
     * @param locale The Locale
     * @param user The current user
     * @return document action list
     */
    public List<DocumentAction> getActions( Document document, Locale locale, AdminUser user )
    {
        List<DocumentAction> documentActionList = new ArrayList<DocumentAction>(  );

        Plugin plugin = PluginService.getPlugin( PLUGIN_NAME );

        //If the document has a mapping
        if ( MappingHome.findByCodeDocumentType( document.getCodeDocumentType(  ), plugin ) != null )
        {
            //If the user has permission
            if ( RBACService.isAuthorized( CalendarResourceIdService.RESOURCE_TYPE, RBAC.WILDCARD_RESOURCES_ID,
                        CalendarResourceIdService.PERMISSION_MANAGE, user ) &&
                    user.checkRight( DocumentToCalendarJspBean.RIGHT_MANAGE_DOCUMENTTOCALENDAR ) &&
                    user.checkRight( CalendarJspBean.RIGHT_MANAGE_CALENDAR ) )
            {
                Plugin calendarPlugin = PluginService.getPlugin( CalendarPlugin.PLUGIN_NAME );

                List<AgendaResource> calendarList = CalendarHome.findAgendaResourcesList( calendarPlugin );

                //If there is a calendar
                if ( calendarList.size(  ) != 0 )
                {
                    DocumentAction documentAction = new DocumentAction(  );

                    documentAction.setNameKey( PARAMETER_BUTTON_NAME );
                    documentAction.setDescriptionKey( PARAMETER_BUTTON_DESCRIPTION );
                    documentAction.setUrl( PARAMETER_BUTTON_ACTION_URL );
                    documentAction.setIconUrl( PARAMETER_BUTTON_ICON_URL );
                    documentAction.setLocale( locale );

                    documentActionList.add( documentAction );
                }
            }
        }

        return documentActionList;
    }
}
