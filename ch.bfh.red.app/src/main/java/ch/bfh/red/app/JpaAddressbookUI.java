/**
 * Copyright 2009-2013 Oy Vaadin Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.bfh.red.app;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

public class JpaAddressbookUI extends UI {

    public static final String PERSISTENCE_UNIT = "redapp";

    static {
        DemoDataGenerator.create();
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(new AddressBookMainView());
    }
}