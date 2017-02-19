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

import java.io.IOException;

/**
 * This is a generic exception that indicates a problem with a WaveFront 
 * resource.
 * <p>
 * Instances of this or sub-classes will generally be thrown during
 * loading or saving of WaveFront resources.
 *
 * @author Momchil Atanasov
 * 
 */
public class WFException extends IOException {

    private static final long serialVersionUID = 1L;

    public WFException() {
        super();
    }

    public WFException(String message) {
        super(message);
    }

    public WFException(Throwable ex) {
        super(ex);
    }

    public WFException(String message, Throwable ex) {
        super(message, ex);
    }
    
}
