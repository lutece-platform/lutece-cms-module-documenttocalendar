/*
 * Copyright (c) 2002-2011, Mairie de Paris
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
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.List;


/**
*
*class mappingAttributeHome
*
*/
public final class MappingAttributeHome
{
    // Static variable pointed at the DAO instance
    private static IMappingAttributeDAO _dao = (IMappingAttributeDAO) SpringContextService.getPluginBean( "documenttocalendar",
            "documenttocalendar.mappingAttributeDAO" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private MappingAttributeHome(  )
    {
    }

    /**
     * Update of the mappingAttribute which is specified in parameter
     *
     * @param mappingAttribute The instance of the MappingAttribute which contains the informations to update
     * @param plugin the Plugin
     */
    public static void update( MappingAttribute mappingAttribute, Plugin plugin )
    {
        _dao.store( mappingAttribute, plugin );
    }

    /**
     * Remove the Mapping attributes whose identifier is specified in parameter
     *
     * @param strCodeDocumentType The code of the document type
     * @param plugin the Plugin
     */
    public static void remove( String strCodeDocumentType, Plugin plugin )
    {
        _dao.delete( strCodeDocumentType, plugin );
    }

    /**
     * Creation of an instance of mappingAttribute
     *
     * @param mappingAttribute The instance of the MappingAttribute which contains the informations to store
     * @param plugin the Plugin
      */
    public static void create( MappingAttribute mappingAttribute, Plugin plugin )
    {
        _dao.insert( mappingAttribute, plugin );
    }

    /**
     * Returns an instance of a mappingAttribute whose id mapping attribute is specified in parameter
     *
     * @param idKey The mapping attribute id
     * @param plugin the Plugin
     * @return an instance of mappingAttribute
     */
    public static MappingAttribute findByPrimaryKey( Integer idKey, Plugin plugin )
    {
        return _dao.load( idKey, plugin );
    }

    /**
     * Return the list of all mapping attribute of a document type
     *
     * @param strCodeDocumentType The mapping attribute type
     * @param plugin the Plugin
     * @return the mapping attribute list of a document type
     */
    public static List<MappingAttribute> findDocumentMappingAttributeList( String strCodeDocumentType, Plugin plugin )
    {
        return _dao.selectDocumentMappingAttributeList( strCodeDocumentType, plugin );
    }
}
