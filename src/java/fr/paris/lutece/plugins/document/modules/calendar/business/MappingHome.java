/*
 * Copyright (c) 2002-2014, Mairie de Paris
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

import fr.paris.lutece.plugins.document.business.DocumentTypeHome;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.List;


/**
*
*class mappingHome
*
*/
public final class MappingHome
{
    // Static variable pointed at the DAO instance
    private static IMappingDAO _dao = (IMappingDAO) SpringContextService.getPluginBean( "documenttocalendar",
            "documenttocalendar.mappingDAO" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private MappingHome(  )
    {
    }

    /**
     * Returns a list of all mapping
     *
     * @param plugin the plugin
     * @return  the list of mapping
     */
    public static List<Mapping> getList( Plugin plugin )
    {
        List<Mapping> listMapping = _dao.select( plugin );

        for ( Mapping mapping : listMapping )
        {
            mapping.setDocumentTypeName( DocumentTypeHome.findByPrimaryKey( mapping.getCodeDocumentType(  ) ).getName(  ) );
        }

        return listMapping;
    }

    /**
     * Remove the Mapping whose identifier is specified in parameter
     *
     * @param strCodeDocumentType The code of the document type
     * @param plugin the Plugin
     */
    public static void remove( String strCodeDocumentType, Plugin plugin )
    {
        _dao.delete( strCodeDocumentType, plugin );
    }

    /**
     * Returns an instance of a mapping whose identifier is specified in parameter
     *
     * @param strCodeDocumentType The code of the document type
     * @param plugin the Plugin
     * @return an instance of Mapping
     */
    public static Mapping findByCodeDocumentType( String strCodeDocumentType, Plugin plugin )
    {
        Mapping mapping = _dao.load( strCodeDocumentType, plugin );

        if ( mapping != null )
        {
            mapping.setDocumentTypeName( DocumentTypeHome.findByPrimaryKey( mapping.getCodeDocumentType(  ) ).getName(  ) );
        }

        return mapping;
    }

    /**
     * Update of the mapping which is specified in parameter
     *
     * @param mapping The instance of the Mapping which contains the informations to update
     * @param plugin the Plugin
     *
     */
    public static void update( Mapping mapping, Plugin plugin )
    {
        _dao.store( mapping, plugin );
    }

    /**
     * Creation of an instance of mapping
     *
     * @param mapping The instance of the Mapping which contains the informations to store
     * @param plugin the Plugin
      */
    public static void create( Mapping mapping, Plugin plugin )
    {
        _dao.insert( mapping, plugin );
    }
}
