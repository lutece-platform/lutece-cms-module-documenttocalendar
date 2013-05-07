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

import fr.paris.lutece.plugins.document.business.DocumentType;
import fr.paris.lutece.plugins.document.business.DocumentTypeHome;
import fr.paris.lutece.plugins.document.business.attributes.DocumentAttribute;
import fr.paris.lutece.plugins.document.business.attributes.DocumentAttributeHome;
import fr.paris.lutece.plugins.document.modules.calendar.business.CalendarAttribute;
import fr.paris.lutece.plugins.document.modules.calendar.business.CalendarAttributeHome;
import fr.paris.lutece.plugins.document.modules.calendar.business.Mapping;
import fr.paris.lutece.plugins.document.modules.calendar.business.MappingAttribute;
import fr.paris.lutece.plugins.document.modules.calendar.business.MappingAttributeHome;
import fr.paris.lutece.plugins.document.modules.calendar.business.MappingHome;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
*
* MappingService
*
*/
public final class MappingService
{
    private static final String PARAMETER_TYPE_FILE = "file";
    private static final String PARAMETER_TYPE_DATE = "date";
    private static final String PARAMETER_TYPE_URL = "url";
    private static final String PARAMETER_TYPE_NUMERICTEXT = "numerictext";
    private static final String PARAMETER_TYPE_TEXT = "text";
    private static final String PARAMETER_TYPE_INTERNALLINK = "internallink";
    private static final String PARAMETER_TYPE_IMAGE = "image";
    private static final String PLUGIN_NAME = "documenttocalendar";

    /** Constructor */
    private MappingService(  )
    {
    }

    /**
     * Return all document types from document_type which are not present in documenttocalendar_mapping
     *
     * @return the list of mapping
     */
    public static List<Mapping> getCodeDocumentTypeWithoutMapping(  )
    {
        Plugin plugin = PluginService.getPlugin( PLUGIN_NAME );

        List<Mapping> mappingList = MappingHome.getList( plugin );

        Collection<DocumentType> documentTypeList = DocumentTypeHome.findAll(  );

        List<Mapping> documentTypeWithoutMappingList = new ArrayList<Mapping>(  );

        boolean bisMappingWithoutDescription;

        for ( DocumentType documentType : documentTypeList )
        {
            bisMappingWithoutDescription = true;

            for ( Mapping mapping : mappingList )
            {
                if ( mapping.getCodeDocumentType(  ).equals( documentType.getCode(  ) ) )
                {
                    bisMappingWithoutDescription = false;

                    break;
                }
            }

            if ( bisMappingWithoutDescription )
            {
                Mapping mappingWithoutDescription = new Mapping(  );
                mappingWithoutDescription.setCodeDocumentType( documentType.getCode(  ) );
                mappingWithoutDescription.setDocumentTypeName( DocumentTypeHome.findByPrimaryKey( 
                        documentType.getCode(  ) ).getName(  ) );
                documentTypeWithoutMappingList.add( mappingWithoutDescription );
            }
        }

        return documentTypeWithoutMappingList;
    }

    /**
     * Return all attributes of a document wich are required
     *
     * @param strCodeDocumentType document type code
     * @return the list of DocumentAttribute
     */
    public static List<DocumentAttribute> getDocumentAttributesByCodeDocument( String strCodeDocumentType )
    {
        DocumentType documentType = new DocumentType(  );
        documentType.setCode( strCodeDocumentType );
        DocumentAttributeHome.setDocumentTypeAttributes( documentType );

        return documentType.getAttributes(  );
    }

    /**
     * Return all attributes of a mapping
     *
     * @param strCodeDocumentType document type code
     * @return the list of MappingAttribute
     */
    public static List<MappingAttribute> getDocumentMappingAttributeList( String strCodeDocumentType )
    {
        Plugin plugin = PluginService.getPlugin( PLUGIN_NAME );
        List<MappingAttribute> mappingAttributeList = MappingAttributeHome.findDocumentMappingAttributeList( strCodeDocumentType,
                plugin );

        for ( MappingAttribute mappingAttribute : mappingAttributeList )
        {
            getAttributeDescription( mappingAttribute );
        }

        return mappingAttributeList;
    }

    /**
     * Return all datas of a mapping document attribute
     *
     * @param nIdMappingAttribute mapping attribute id
     * @return the mappin gAttribute
     */
    public static MappingAttribute getMappingAttribute( int nIdMappingAttribute )
    {
        Plugin plugin = PluginService.getPlugin( PLUGIN_NAME );
        MappingAttribute mappingAttribute = MappingAttributeHome.findByPrimaryKey( nIdMappingAttribute, plugin );

        getAttributeDescription( mappingAttribute );

        return mappingAttribute;
    }

    /**
     * complete the mapping attribute with descriptions for user
     *
     * @param mappingAttribute the mapping attribute
     */
    public static void getAttributeDescription( MappingAttribute mappingAttribute )
    {
        Plugin plugin = PluginService.getPlugin( PLUGIN_NAME );

        //If a attribute has been associated to the document attribute
        if ( mappingAttribute.getIdCalendarAttribute(  ) != -1 )
        {
            CalendarAttribute calendarAttribute = CalendarAttributeHome.findByKey( mappingAttribute.getIdCalendarAttribute(  ),
                    plugin );
            mappingAttribute.setCalendarAttributeDescription( calendarAttribute.getCalendarAttributeLabel(  ) );
        }

        //If it is a document attribute, not a document feature
        if ( mappingAttribute.getIdDocumentAttribute(  ) != -1 )
        {
            DocumentAttribute documentAttribute = DocumentAttributeHome.findByPrimaryKey( mappingAttribute.getIdDocumentAttribute(  ) );
            mappingAttribute.setDocumentAttributeDescription( documentAttribute.getDescription(  ) );
        }
    }

    /**
     * Return all CalendarAttribute which can be potentially mapped to the document attribute
     *
     * @param nIdMappingAttribute mapping attribute id
     * @return the calendar attribute list
     */
    public static List<CalendarAttribute> getCalendarAttributeListByIdMappingAttribute( int nIdMappingAttribute )
    {
        Plugin plugin = PluginService.getPlugin( PLUGIN_NAME );
        MappingAttribute mappingAttribute = MappingAttributeHome.findByPrimaryKey( nIdMappingAttribute, plugin );
        List<CalendarAttribute> calendarAttributeList = new ArrayList<CalendarAttribute>(  );

        //If it is a document attribute
        if ( mappingAttribute.getIdDocumentAttribute(  ) != -1 )
        {
            DocumentAttribute documentAttribute = DocumentAttributeHome.findByPrimaryKey( mappingAttribute.getIdDocumentAttribute(  ) );

            if ( documentAttribute.getCodeAttributeType(  ).equals( PARAMETER_TYPE_URL ) ||
                    documentAttribute.getCodeAttributeType(  ).equals( PARAMETER_TYPE_TEXT ) ||
                    documentAttribute.getCodeAttributeType(  ).equals( PARAMETER_TYPE_NUMERICTEXT ) ||
                    documentAttribute.getCodeAttributeType(  ).equals( PARAMETER_TYPE_INTERNALLINK ) )
            {
                calendarAttributeList = CalendarAttributeHome.findByType( PARAMETER_TYPE_TEXT, plugin );
            }
            else if ( documentAttribute.getCodeAttributeType(  ).equals( PARAMETER_TYPE_DATE ) )
            {
                calendarAttributeList = CalendarAttributeHome.findByType( PARAMETER_TYPE_DATE, plugin );
            }
            else if ( documentAttribute.getCodeAttributeType(  ).equals( PARAMETER_TYPE_IMAGE ) ||
                    documentAttribute.getCodeAttributeType(  ).equals( PARAMETER_TYPE_FILE ) )
            {
                calendarAttributeList = CalendarAttributeHome.findByType( PARAMETER_TYPE_FILE, plugin );
            }
            else if ( documentAttribute.getCodeAttributeType(  ).equals( PARAMETER_TYPE_NUMERICTEXT ) )
            {
                calendarAttributeList = CalendarAttributeHome.findByType( PARAMETER_TYPE_NUMERICTEXT, plugin );
            }
        }

        //else, it is a document feature
        else
        {
            //All of the document features are String (title & summary)
            calendarAttributeList = CalendarAttributeHome.findByType( PARAMETER_TYPE_TEXT, plugin );
        }

        //Remove the calendar Attributes already mapped
        List<MappingAttribute> mappingAttributeList = getDocumentMappingAttributeList( mappingAttribute.getCodeDocumentType(  ) );

        for ( MappingAttribute mappingAttributeMapped : mappingAttributeList )
        {
            if ( ( mappingAttributeMapped.getId(  ) != nIdMappingAttribute ) &&
                    ( mappingAttributeMapped.getIdCalendarAttribute(  ) != -1 ) )
            {
                for ( CalendarAttribute calendarAttribute : calendarAttributeList )
                {
                    if ( calendarAttribute.getId(  ) == mappingAttributeMapped.getIdCalendarAttribute(  ) )
                    {
                        calendarAttributeList.remove( calendarAttribute );

                        break;
                    }
                }
            }
        }

        return calendarAttributeList;
    }
}
