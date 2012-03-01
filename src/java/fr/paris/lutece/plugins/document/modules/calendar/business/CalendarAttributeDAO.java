/*
 * Copyright (c) 2002-2012, Mairie de Paris
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
package fr.paris.lutece.plugins.document.modules.calendar.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 *
 *class  CalendarAttributeDAO
 *
 */
public class CalendarAttributeDAO implements ICalendarAttributeDAO
{
    private static final String SQL_QUERY_SELECT = "SELECT id_calendar_attr, calendar_attr_label, calendar_attr_bookmark, calendar_attr_type FROM documenttocalendar_calendar_attr WHERE id_calendar_attr=?";
    private static final String SQL_QUERY_FIND_BY_CALENDAR_ATTR_TYPE = "SELECT id_calendar_attr, calendar_attr_label, calendar_attr_bookmark, calendar_attr_type FROM documenttocalendar_calendar_attr WHERE calendar_attr_type=?";

    /**
     * Returns an instance of a CalendarAttribute whose identifier is specified in parameter
     *
     * @param idKey The calendar attribute id
     * @param plugin the Plugin
     * @return an instance of MappingAttribute
     */
    public CalendarAttribute findByKey( int idKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, idKey );
        daoUtil.executeQuery(  );

        CalendarAttribute calendarAttribute = null;

        if ( daoUtil.next(  ) )
        {
            calendarAttribute = new CalendarAttribute(  );
            calendarAttribute.setId( daoUtil.getInt( 1 ) );
            calendarAttribute.setCalendarAttributeLabel( daoUtil.getString( 2 ) );
            calendarAttribute.setCalendarAttributeBookMark( daoUtil.getString( 3 ) );
            calendarAttribute.setCalendarAttributeType( daoUtil.getString( 4 ) );
        }

        daoUtil.free(  );

        return calendarAttribute;
    }

    /**
     * Load the data of calendarAttribute corresponding to a special type returns them in a list
     * @param strCalendarType the calendar type
     * @param plugin the plugin
     * @return the list of calendarAttribute
     */
    public List<CalendarAttribute> findByType( String strCalendarType, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_FIND_BY_CALENDAR_ATTR_TYPE, plugin );
        daoUtil.setString( 1, strCalendarType );
        daoUtil.executeQuery(  );

        CalendarAttribute calendarAttribute = null;
        List<CalendarAttribute> listcalendarAttribute = new ArrayList<CalendarAttribute>(  );

        while ( daoUtil.next(  ) )
        {
            calendarAttribute = new CalendarAttribute(  );
            calendarAttribute.setId( daoUtil.getInt( 1 ) );
            calendarAttribute.setCalendarAttributeLabel( daoUtil.getString( 2 ) );
            calendarAttribute.setCalendarAttributeBookMark( daoUtil.getString( 3 ) );
            calendarAttribute.setCalendarAttributeType( daoUtil.getString( 4 ) );
            listcalendarAttribute.add( calendarAttribute );
        }

        daoUtil.free(  );

        return listcalendarAttribute;
    }
}
