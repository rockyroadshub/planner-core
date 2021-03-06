/*
 * Copyright 2016 Arnell Christoper D. Dalid.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.rockyroadshub.planner.core.database;

import com.jcabi.aspects.LogExceptions;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Arnell Christoper D. Dalid
 * @since 0.2.0
 */
public final class DatabaseControl {    
    private DatabaseControl() {}
    
    private static final class Holder {
        private static final DatabaseControl INSTANCE = new DatabaseControl();
    }

    public static DatabaseControl getInstance() {  
        return Holder.INSTANCE;
    }  
    
    /**
     * Creates a new memory in the database
     * @param memory memory to be created
     * @return true if a new memory is created and false if a memory already exists
     * @throws SQLException
     */
    @LogExceptions
    public boolean create(Memory memory) throws SQLException {
        String schemaPattern = memory.getSchemaPattern();
        String tableNamePattern = memory.getTableNamePattern();
        Connection connection = DatabaseConnection.getConnection();
        
        if(!exists(connection, schemaPattern, tableNamePattern)) {
            try(Statement command = connection.createStatement()) {
                command.executeUpdate(memory.getMembers().getCreateFormat());
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if a memory already exists
     * @param connection connection of the memory (database) where all of the data are saved
     * @param schemaPattern schema pattern parameter
     * @param tableNamePattern table name parameter
     * @return returns true if a memory already exists; returns false otherwise
     * @throws SQLException 
     */
    @LogExceptions
    private boolean exists(Connection connection, 
            String schemaPattern,
            String tableNamePattern) 
            
                throws SQLException 
    {
        DatabaseMetaData dbmd = connection.getMetaData();
        try(ResultSet rs = dbmd.getTables(
                null, schemaPattern, tableNamePattern, null)) 
        {
            return rs.next();
        }
    }
//    
//    /**
//     * Executes a given SQL command
//     * @param SQLCommand command parameter
//     * @throws SQLException
//     */
//    @LogExceptions
//    public void execute(String SQLCommand) throws SQLException {
//        DatabaseConnection connection = DatabaseConnection.getInstance();
//        Connection connection0 = connection.getConnection();
//        try(Statement stmt = connection0.createStatement()) {
//            stmt.executeUpdate(SQLCommand);
//        }
//    }
//    
//    /**
//     * Gets all of the data in a row by saving it in an array
//     * <p>
//     * <strong>Note:</strong> This command returns the first row it gets from the command expression
//     * @param SQLCommand command parameter
//     * @param totalColumns number of columns in the memory
//     * @return returns all of the information of a row an in array
//     * @throws SQLException 
//     */
//    @LogExceptions
//    public String[] find(String SQLCommand, int totalColumns) 
//            throws SQLException 
//    {
//        DatabaseConnection connection = DatabaseConnection.getInstance();
//        Connection connection0 = connection.getConnection();
//        String[] data = new String[totalColumns + 1];
//        
//        try(Statement stmt = connection0.createStatement()) {
//            try(ResultSet rs = stmt.executeQuery(SQLCommand)) {
//                if(rs.next()) {
//                    for(int i = 0; i < totalColumns + 1; i++) {
//                        data[i] = rs.getString(i+1);
//                    }
//                }           
//            }
//        }
//        return data;
//    }
//    
//    /**
//     * 
//     * @param SQLCommand command parameter
//     * @return returns the number of results got from a command
//     * @throws SQLException 
//     */
//    @LogExceptions
//    public int getRowCount(String SQLCommand) throws SQLException {
//        int i = 0;
//        DatabaseConnection connection = DatabaseConnection.getInstance();
//        Connection connection0 = connection.getConnection();
//        try(Statement stmt = connection0.createStatement()) {
//            try(ResultSet rs = stmt.executeQuery(SQLCommand)) {
//                while(rs.next()) {
//                    i++;
//                }
//            }
//        }
//        return i;
//    }
}