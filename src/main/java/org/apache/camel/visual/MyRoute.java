//usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.apache.camel:camel-route-parser:3.6.0-SNAPSHOT
//DEPS org.jboss.forge.roaster:roaster-jdt:2.22.0.Final

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.visual;

import org.apache.camel.builder.RouteBuilder;

public class MyRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:foo?period=500").routeId("myRoute")
            .recipientList(header("foo"))
            .to("mock:zzz")
            .toD("twitter:hi");
    }
}
