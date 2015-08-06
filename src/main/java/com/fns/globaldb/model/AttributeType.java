/*
 * Copyright 2015 - Chris Phillipson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fns.globaldb.model;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

/**
 * An {@code AttributeType} specifies the type of an {@link Attribute} defined in a {@link Class}.
 * It may be declared independent from an {@code Attribute} participating 
 * in multiple {@code Class} instances. Mediates between Java and SQL types. 
 * 
 * @author Chris Phillipson
 */
public enum AttributeType {

    VARCHAR(0, "varchar", Types.VARCHAR),
    TEXT(1, "text", Types.LONGVARCHAR),
    DATETIME(2, "datetime", Types.TIMESTAMP),
    INTEGER(3, "int", Types.INTEGER),
    BIG_INTEGER(4, "bigint", Types.BIGINT),
    DECIMAL(5, "decimal", Types.DECIMAL),
    JSON_BLOB(6, "json", Types.LONGVARCHAR);
    
    /** The id. Internally, uniquely and statically identifies this enum. */
    private final Integer id;
    
    /** The intermediate type.  Maps between a Java type and SQL type. */
    private final String type;
    
    /** A SQL type. @see Types */
    private final int sqlType;

    /**
     * Instantiates a new attribute type.
     *
     * @param id
     *            the id
     * @param type
     *            the intermediate type
     * @param sqlType
     *            the SQL type
     */
    AttributeType(Integer id, String type, int sqlType) {
        this.id = id;
        this.type = type;
        this.sqlType = sqlType;
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the intermediate type.
     *
     * @return the intermediate type
     */
    public String getType() {
        return type;
    }
    
    /**
     * Gets the SQL type.
     *
     * @return the SQL type; one of {@link Types}
     */
    public int getSqlType() {
        return sqlType;
    }

    /**
     * From id.
     *
     * @param id
     *            an identifier
     * @return the attribute type
     * @throws IllegalArgumentException when id does not match any internal {@link AttributeType#id}
     */
    public static AttributeType fromId(Integer id) {
        List<AttributeType> attributeTypes = Arrays.stream(AttributeType.values()).filter(o -> o.getId() == id).collect(Collectors.toList());
        Assert.notEmpty(attributeTypes, String.format("Invalid AttributeType [%s]", id));
        return attributeTypes.get(0);
    }
    
    /**
     * To SQL type.
     *
     * @param type
     *            the intermediate type
     * @return a SQL Type; one of {@link Types}
     * @throws IllegalArgumentException when type does not match any internal {@link AttributeType#type}
     */
    public static int toSqlType(String type) {
        List<AttributeType> attributeTypes = Arrays.stream(AttributeType.values()).filter(o -> o.getType().equalsIgnoreCase(type)).collect(Collectors.toList());
        Assert.notEmpty(attributeTypes, String.format("Invalid type [%s]", type));
        return attributeTypes.get(0).getSqlType();
    }
}

