/**
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 **/
        
/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.example;

import org.mule.api.annotations.*;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.ConnectionException;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.MetaDataKeyParam;
import org.mule.api.annotations.param.Optional;
import org.mule.common.metadata.*;
import org.mule.common.metadata.builder.DefaultMetaDataBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Example Connector for MetaData
 *
 * @author MuleSoft, Inc.
 */
@Connector(name="example", schemaVersion="1.0-SNAPSHOT")
public class ExampleConnector
{
    @MetaDataKeyRetriever
    public List<MetaDataKey> getEntities() throws Exception {

        List<MetaDataKey> entities = new ArrayList<MetaDataKey>();

        entities.add(new DefaultMetaDataKey("Book_id","Book"));
        entities.add(new DefaultMetaDataKey("Author_id","Author"));
        entities.add(new DefaultMetaDataKey("BookList_id","BookList"));

        return entities;
    }

    @MetaDataRetriever
    public MetaData describeEntity(MetaDataKey entityKey) throws Exception {

        //Here we describe the entity depending on the entity key

        if ("Author_id".equals(entityKey.getId())) {
            MetaDataModel authorModel =  new DefaultMetaDataBuilder().createPojo(Author.class).build();
            return new DefaultMetaData(authorModel);
        }

        if ("Book_id".equals(entityKey.getId())) {
            MetaDataModel bookModel =  new DefaultMetaDataBuilder().createPojo(Book.class).build();
            return new DefaultMetaData(bookModel);
        }

        if ("BookList_id".equals(entityKey.getId())) {
            MetaDataModel bookListModel =  new DefaultMetaDataBuilder().createList().ofPojo(Book.class).build();
            return new DefaultMetaData(bookListModel);
        }

        throw new RuntimeException(String.format("This entity %s is not supported",entityKey.getId()));

    }

    /**
     * Creates an entity on the book library service
     *
     * {@sample.xml ../../../doc/example-connector.xml.sample example:create}
     *
     * @param entityType type of the entity
     * @param entityData data of the entity
     * @return the entity created
     */
    @Processor
    public Object create(@MetaDataKeyParam String entityType, @Default("#[payload]") Object entityData) {

        if (entityData instanceof Book) {
            return createBook((Book) entityData);
        }

        if (entityData instanceof Author) {
            return createAuthor((Author) entityData);
        }

        throw new RuntimeException("Entity not recognized");

    }

    private Object createAuthor(Author entityData) {
        //CODE FOR CREATING NEW AUTHOR GOES HERE
        return null;
    }

    private Object createBook(Book entityData) {
        //CODE FOR CREATING A NEW BOOK GOES HERE
        return null;
    }

    /**
     * Connect
     *
     * @param username A username
     * @param password A password
     * @throws ConnectionException
     */
    @Connect
    public void connect(@ConnectionKey String username, @Password String password)
        throws ConnectionException {
        /*
         * CODE FOR ESTABLISHING A CONNECTION GOES HERE
         */
    }

    /**
     * Disconnect
     */
    @Disconnect
    public void disconnect() {
        /*
         * CODE FOR CLOSING A CONNECTION GOES HERE
         */
    }

    /**
     * Are we connected
     */
    @ValidateConnection
    public boolean isConnected() {
        return true;
    }

    /**
     * Connection identifier
     */
    @ConnectionIdentifier
    public String connectionId() {
        return "001";
    }



}
