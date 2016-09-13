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

import java.util.Optional;

/**
 *
 * @author Arnell Christoper D. Dalid
 * @since 0.2.1
 */
public interface Mapper {
    /**
     * Finds a data from a given id
     * @param id primary/main key of the data
     * @return returns a data as in "Optional" container
     * @throws java.lang.Exception
     * @see java.util.Optional
     */
    Optional<Data> find(int id) throws Exception;
    
    /**
     * Inserts new data in the memory
     * @param data data to be inserted
     * @throws Exception 
     */
    void insert(Data data) throws Exception;

    /**
     * Updates a data in the memory
     * @param data data to be updated
     * @throws Exception 
     */
    void update(Data data) throws Exception;

    /**
     * Deletes a data in the memory
     * @param id primary/main key of the data
     * @throws Exception 
     */
    void delete(int id) throws Exception;
}
