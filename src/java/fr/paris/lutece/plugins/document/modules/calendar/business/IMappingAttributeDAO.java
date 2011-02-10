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
*Interface IMappingAttributeDAO
*
*/
public interface IMappingAttributeDAO
{
    /**
     * update mapping attribute record in the table.
     *
     * @param mappingAttribute instance of the MappingAttribute object to update
     * @param plugin the plugin
     */
    void store( MappingAttribute mappingAttribute, Plugin plugin );

    /**
     * Remove the Mapping attributes of a document whose identifier is specified in parameter
     *
     * @param strCodeDocumentType The code of the document type
     * @param plugin the plugin
     */
    void delete( String strCodeDocumentType, Plugin plugin );

    /**
     * Insert a new record in the table.
     *
     * @param mappingAttribute instance of the MappingAttribute object to insert
     * @param plugin the plugin
     */
    void insert( MappingAttribute mappingAttribute, Plugin plugin );

    /**
     * Returns an instance of a mappingAttribute whose id mapping attribute is specified in parameter
     *
     * @param idKey The mapping attribute id
     * @param plugin the Plugin
     * @return an instance of mappingAttribute
     */
    MappingAttribute load( Integer idKey, Plugin plugin );

    /**
     * Return the list of all mapping attributes of a document
     *
     * @param strCodeDocumentType The document type code
     * @param plugin the Plugin
     * @return A list of mapping attributes
     */
    List<MappingAttribute> selectDocumentMappingAttributeList( String strCodeDocumentType, Plugin plugin );
}
