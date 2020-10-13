/**
 * Copyright (C) 2009-2012 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fusesource.restygwt.client.basic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;
import org.junit.Test;
import java.util.Collections;
import java.util.List;

public class PatchTestGwt extends GWTTestCase {

    public static final String NEW_CUSTOMER_NAME = "John Doe";

    @Override
    public String getModuleName() {
        return "org.fusesource.restygwt.PatchTestGwt";
    }

    @Test
    public void testPatch() {
        final Resource resource = new Resource(GWT.getModuleBaseURL() + "api");

        PatchService service = GWT.create(PatchService.class);
        ((RestServiceProxy) service).setResource(resource);

        List<JsonPatchOperationDTO> operations = Collections.singletonList(new JsonPatchOperationDTO().op("replace").path("/name").value(NEW_CUSTOMER_NAME));
        service.patchCustomer(operations, new MethodCallback<ExampleDto>() {
            @Override
            public void onSuccess(Method method, ExampleDto response) {
                if (NEW_CUSTOMER_NAME.equals(response.name)) {
                    finishTest();
                } else {
                    fail();
                }
            }

            @Override
            public void onFailure(Method method, Throwable exception) {
                fail();
            }
        });

        // wait... we are in async testing...
        delayTestFinish(10000);
    }

}