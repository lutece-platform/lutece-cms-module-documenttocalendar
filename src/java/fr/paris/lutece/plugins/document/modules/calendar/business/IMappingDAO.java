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

import fr.paris.lutece.portal.service.plugin.Plugin;

import java.util.List;


/**
*
*Interface IMappingDAO
*
*/
public interface IMappingDAO
{
    /**
     * Load the data of all mapping returns them in a  list
     * @param plugin the plugin
     * @return  the list of mapping
     */
    List<Mapping> select( Plugin plugin );

    /**
     * Remove the Mapping whose identifier is specified in parameter
     *
     * @param strCodeDocumentType The code of the document type
     * @param plugin the Plugin
     */
    void delete( String strCodeDocumentType, Plugin plugin );

    /**
     * Load the data of the mapping from the table
     *
     * @param strCodeDocumentType The code of the document type
     * @param plugin the plugin
     * @return the instance of the Mapping
     */
    Mapping load( String strCodeDocumentType, Plugin plugin );

    /**
     * update record in the table.
     *
     * @param mapping instance of the Mapping object to update
     * @param plugin the plugin
     */
    void store( Mapping mapping, Plugin plugin );

    /**
     * Insert a new record in the table.
     *
     * @param mapping instance of the Mapping object to insert
     * @param plugin the plugin
     */
    void insert( Mapping mapping, Plugin plugin );
}
