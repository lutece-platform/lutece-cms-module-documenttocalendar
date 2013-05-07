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
package fr.paris.lutece.plugins.document.modules.calendar.business;


/**
 *
 * class CalendarAttribute
 *
 */
public class CalendarAttribute
{
    private int _nIdCalendarAttribute;
    private String _strCalendarAttributeLabel;
    private String _strCalendarAttributeBookMark;
    private String _strCalendarAttributeType;

    /**
    * set the Id of the calendar attribute
    * @param nIdCalendarAttribute the id of the calendar attribute
    */
    public void setId( int nIdCalendarAttribute )
    {
        _nIdCalendarAttribute = nIdCalendarAttribute;
    }

    /**
    *
    * @return the Id of the calendar attribute
    */
    public int getId(  )
    {
        return _nIdCalendarAttribute;
    }

    /**
     * set the Label of the calendar attribute
     * @param strCalendarAttributeLabel the Label of the calendar attribute
     */
    public void setCalendarAttributeLabel( String strCalendarAttributeLabel )
    {
        _strCalendarAttributeLabel = strCalendarAttributeLabel;
    }

    /**
    *
    * @return the Label of the calendar attribute
    */
    public String getCalendarAttributeLabel(  )
    {
        return _strCalendarAttributeLabel;
    }

    /**
     * set the BookMark of the calendar attribute
     * @param strCalendarAttributeBookMark the BookMark of the calendar attribute
     */
    public void setCalendarAttributeBookMark( String strCalendarAttributeBookMark )
    {
        _strCalendarAttributeBookMark = strCalendarAttributeBookMark;
    }

    /**
    *
    * @return the BookMark of the calendar attribute
    */
    public String getCalendarAttributeBookMark(  )
    {
        return _strCalendarAttributeBookMark;
    }

    /**
     * set the Type of the calendar attribute
     * @param strCalendarAttributeType the type of the calendar attribute
     */
    public void setCalendarAttributeType( String strCalendarAttributeType )
    {
        _strCalendarAttributeType = strCalendarAttributeType;
    }

    /**
    *
    * @return the Type of the calendar attribute
    */
    public String getCalendarAttributeType(  )
    {
        return _strCalendarAttributeType;
    }
}
