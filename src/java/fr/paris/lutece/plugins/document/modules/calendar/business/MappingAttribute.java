/*
 * Copyright (c) 2002-2010, Mairie de Paris
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
 * class MappingAttribute
 *
 */
public class MappingAttribute
{
    private int _nIdMappingAttribute;
    private String _strCodeDocumentType;
    private int _nIdDocumentAttribute;
    private String _strDocumentAttributeDescription;
    private int _nIdCalendarAttribute;
    private String _strCalendarAttributeDescription;
    private String _strDocumentFeature;

    /**
    * Returns the IdMappingAttribute
    *
    * @return The IdMappingAttribute
    */
    public int getId(  )
    {
        return _nIdMappingAttribute;
    }

    /**
     * Sets the IdMappingAttribute
     *
     * @param nIdMappingAttribute IdMappingAttribute
     */
    public void setId( int nIdMappingAttribute )
    {
        _nIdMappingAttribute = nIdMappingAttribute;
    }

    /**
     * set the Code of the document type of the mapping
     * @param strCodeDocumentType the code of the document type
     */
    public void setCodeDocumentType( String strCodeDocumentType )
    {
        _strCodeDocumentType = strCodeDocumentType;
    }

    /**
    *
    * @return the Code of the document type
    */
    public String getCodeDocumentType(  )
    {
        return _strCodeDocumentType;
    }

    /**
    * set the Id of the document attribute
    * @param nIdDocumentAttribute the code of the document type
    */
    public void setIdDocumentAttribute( Integer nIdDocumentAttribute )
    {
        _nIdDocumentAttribute = nIdDocumentAttribute;
    }

    /**
    *
    * @return the Id of the document attribute
    */
    public int getIdDocumentAttribute(  )
    {
        return _nIdDocumentAttribute;
    }

    /**
    * set the Description of the document attribute
    * @param strDocumentAttributeDescription the description of the document attribute
    */
    public void setDocumentAttributeDescription( String strDocumentAttributeDescription )
    {
        _strDocumentAttributeDescription = strDocumentAttributeDescription;
    }

    /**
    *
    * @return the Description of the document attribute
    */
    public String getDocumentAttributeDescription(  )
    {
        return _strDocumentAttributeDescription;
    }

    /**
     * set the Id of the calendar attribute
     * @param nIdCalendarAttribute the id of the calendar attribute
     */
    public void setIdCalendarAttribute( Integer nIdCalendarAttribute )
    {
        _nIdCalendarAttribute = nIdCalendarAttribute;
    }

    /**
    *
    * @return the Id of the calendar attribute
    */
    public int getIdCalendarAttribute(  )
    {
        return _nIdCalendarAttribute;
    }

    /**
    * set the Description of the calendar attribute
    * @param strCalendarAttributeDescription the description of the calendar attribute
    */
    public void setCalendarAttributeDescription( String strCalendarAttributeDescription )
    {
        _strCalendarAttributeDescription = strCalendarAttributeDescription;
    }

    /**
    *
    * @return the Description of the calendar attribute
    */
    public String getCalendarAttributeDescription(  )
    {
        return _strCalendarAttributeDescription;
    }

    /**
     * set the title of the document feature
     * @param strDocumentFeature the title of the document feature
     */
    public void setDocumentFeature( String strDocumentFeature )
    {
        _strDocumentFeature = strDocumentFeature;
    }

    /**
    *
    * @return the title of the document feature
    */
    public String getDocumentFeature(  )
    {
        return _strDocumentFeature;
    }
}
