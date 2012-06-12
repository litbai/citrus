/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
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

package com.alibaba.test2.module.screen.form.fields;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.citrus.service.form.Field;
import com.alibaba.citrus.turbine.dataresolver.FormFields;
import org.springframework.beans.factory.annotation.Autowired;

public class MyScreen {
    @Autowired
    private HttpServletRequest request;

    public void execute(@FormFields(name = "field1", group = "myGroup1") Field[] fields) {
        setAttribute(fields);
    }

    private void setAttribute(Object data) {
        request.setAttribute("screenLog", data);
    }
}
