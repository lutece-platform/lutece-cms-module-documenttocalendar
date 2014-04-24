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

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 *
 *class  MappingAttributeDAO
 *
 */
public class MappingAttributeDAO implements IMappingAttributeDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = " SELECT max( id_mapping_attr ) FROM documenttocalendar_mapping_attr ";
    private static final String SQL_QUERY_INSERT = "INSERT INTO documenttocalendar_mapping_attr (id_mapping_attr,code_document_type, id_document_attr,id_calendar_attr, document_features )VALUES(?,?,?,?,?)";
    private static final String SQL_QUERY_DELETE = "DELETE FROM documenttocalendar_mapping_attr WHERE code_document_type = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE documenttocalendar_mapping_attr SET id_mapping_attr=?, code_document_type= ? ,id_document_attr=?,id_calendar_attr=?, document_features=? WHERE id_mapping_attr=?";
    private static final String SQL_QUERY_SELECT = "SELECT id_mapping_attr,code_document_type,id_document_attr,id_calendar_attr,document_features FROM documenttocalendar_mapping_attr WHERE id_mapping_attr=?";
    private static final String SQL_QUERY_SELECT_ALL_BY_DOCUMENT_TYPE = "SELECT id_mapping_attr,code_document_type,id_document_attr,id_calendar_attr,document_features FROM documenttocalendar_mapping_attr WHERE code_document_type=?";

    /**
     * Generates a new primary key
     * @return The new primary key
     */
    private int newPrimaryKey(  )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK );
        daoUtil.executeQuery(  );

        int nKey;

        if ( !daoUtil.next(  ) )
        {
            // if the table is empty
            nKey = 1;
        }

        nKey = daoUtil.getInt( 1 ) + 1;

        daoUtil.free(  );

        return nKey;
    }

    /**
     * update record in the table.
     *
     * @param mappingAttribute instance of the MappingAttribute object to update
     * @param plugin the plugin
     */
    public void store( MappingAttribute mappingAttribute, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        daoUtil.setInt( 1, mappingAttribute.getId(  ) );
        daoUtil.setString( 2, mappingAttribute.getCodeDocumentType(  ) );
        daoUtil.setInt( 3, mappingAttribute.getIdDocumentAttribute(  ) );
        daoUtil.setInt( 4, mappingAttribute.getIdCalendarAttribute(  ) );
        daoUtil.setString( 5, mappingAttribute.getDocumentFeature(  ) );
        daoUtil.setInt( 6, mappingAttribute.getId(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * Remove the Mapping attributes whose identifier is specified in parameter
     *
     * @param strCodeDocumentType The code of the document type
     * @param plugin the Plugin
     */
    public void delete( String strCodeDocumentType, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setString( 1, strCodeDocumentType );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * Insert a new record in the table.
     *
     * @param mappingAttribute instance of the MappingAttribute object to insert
     * @param plugin the plugin
     */
    public void insert( MappingAttribute mappingAttribute, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );
        mappingAttribute.setId( newPrimaryKey(  ) );
        daoUtil.setInt( 1, mappingAttribute.getId(  ) );
        daoUtil.setString( 2, mappingAttribute.getCodeDocumentType(  ) );
        daoUtil.setInt( 3, mappingAttribute.getIdDocumentAttribute(  ) );
        daoUtil.setInt( 4, mappingAttribute.getIdCalendarAttribute(  ) );
        daoUtil.setString( 5, mappingAttribute.getDocumentFeature(  ) );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * Load the mapping attribute by mapping attribute identifier
     *
     * @param idKey The mapping attribute id
     * @param plugin the Plugin
     * @return an instance of mappingAttribute
     */
    public MappingAttribute load( Integer idKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, idKey );

        daoUtil.executeQuery(  );

        MappingAttribute mappingAttribute = null;

        if ( daoUtil.next(  ) )
        {
            mappingAttribute = new MappingAttribute(  );
            mappingAttribute.setId( daoUtil.getInt( 1 ) );
            mappingAttribute.setCodeDocumentType( daoUtil.getString( 2 ) );
            mappingAttribute.setIdDocumentAttribute( daoUtil.getInt( 3 ) );
            mappingAttribute.setIdCalendarAttribute( daoUtil.getInt( 4 ) );
            mappingAttribute.setDocumentFeature( daoUtil.getString( 5 ) );
        }

        daoUtil.free(  );

        return mappingAttribute;
    }

    /**
     * Load the list of MappingAttributes of a document
     *
     * @param strCodeDocumentType The document type code identifier
     * @param plugin The plugin
     * @return The Collection of the MappingAttribute
     */
    public List<MappingAttribute> selectDocumentMappingAttributeList( String strCodeDocumentType, Plugin plugin )
    {
        List<MappingAttribute> mappingAttributeList = new ArrayList<MappingAttribute>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_ALL_BY_DOCUMENT_TYPE, plugin );
        daoUtil.setString( 1, strCodeDocumentType );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            MappingAttribute mappingAttribute = new MappingAttribute(  );
            mappingAttribute.setId( daoUtil.getInt( 1 ) );
            mappingAttribute.setCodeDocumentType( daoUtil.getString( 2 ) );
            mappingAttribute.setIdDocumentAttribute( daoUtil.getInt( 3 ) );
            mappingAttribute.setIdCalendarAttribute( daoUtil.getInt( 4 ) );
            mappingAttribute.setDocumentFeature( daoUtil.getString( 5 ) );

            mappingAttributeList.add( mappingAttribute );
        }

        daoUtil.free(  );

        return mappingAttributeList;
    }
}
