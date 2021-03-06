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

import java.sql.Connection;

/**
 *
 * @author Arnell Christoper D. Dalid
 * @since 0.2.0
 */
public final class DatabaseConnection {    
    private static Connection connection;

    private DatabaseConnection() {}
           
    public static void setConnection(Connection connection) {
        if(DatabaseConnection.connection != null) 
            throw new IllegalStateException("Database is already connected.");
        
        DatabaseConnection.connection = connection;
    }
    
    public static synchronized Connection getConnection() {
        return DatabaseConnection.connection;
    }
}
