/*
 * Copyright (C) mokiat.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mokiat.data.front.error;

/**
 * This exception is used to indicate that a WaveFront
 * resource is too large to load.
 *
 * @author Momchil Atanasov
 * 
 */
public class WFSizeException extends WFException {

    private static final long serialVersionUID = 1L;

    public WFSizeException() {
        super();
    }

    public WFSizeException(String message) {
        super(message);
    }

    public WFSizeException(Throwable ex) {
        super(ex);
    }

    public WFSizeException(String message, Throwable ex) {
        super(message, ex);
    }
}
