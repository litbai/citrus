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

package com.alibaba.citrus.service.velocity;

import static com.alibaba.citrus.test.TestEnvStatic.*;
import static com.alibaba.citrus.test.TestUtil.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.alibaba.citrus.service.template.TemplateService;
import com.alibaba.citrus.service.velocity.impl.VelocityEngineImpl;
import com.alibaba.citrus.springext.support.context.XmlApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;

@RunWith(Parameterized.class)
public class VelocitySkipValidationTests {
    private final boolean            skipValidation;
    private       ApplicationContext factory;
    private       VelocityEngineImpl velocityEngine;

    public VelocitySkipValidationTests(boolean skipValidation) {
        this.skipValidation = skipValidation;
    }

    @Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] { { false }, { true } });
    }

    @Before
    public void init() {
        if (skipValidation) {
            System.setProperty("skipValidation", "true");
        }

        factory = new XmlApplicationContext(new FileSystemResource(new File(srcdir, "services-skip-validation.xml")));
        TemplateService templateService = (TemplateService) factory.getBean("templateService");
        velocityEngine = (VelocityEngineImpl) templateService.getTemplateEngine("vm");
    }

    @After
    public void dispose() {
        System.clearProperty("skipValidation");
    }

    @Test
    public void configuration() {
        assertEquals("/templates", getFieldValue(velocityEngine.getConfiguration(), "path", null));
        assertEquals("UTF-8", getFieldValue(velocityEngine.getConfiguration(), "charset", null));
        assertEquals(true, getFieldValue(velocityEngine.getConfiguration(), "cacheEnabled", null));
        assertEquals(2, getFieldValue(velocityEngine.getConfiguration(), "modificationCheckInterval", null));
        assertEquals(true, getFieldValue(velocityEngine.getConfiguration(), "strictReference", null));
    }
}
