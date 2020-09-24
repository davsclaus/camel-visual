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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.apache.camel.parser.RouteBuilderParser;
import org.apache.camel.parser.model.CamelNodeDetails;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaType;
import org.jboss.forge.roaster.model.source.JavaClassSource;

/**
 * Main to visualize Camel routes live from source code.
 */
public class VisualRouteMain {

    public static void main(String[] args) throws Exception {
        // prototype so this is hardcoded and hacky

        // scan for Java source code
        String dir = args.length > 0 ? args[1] : "";
        Path start = new File(dir).getAbsoluteFile().toPath();

        System.out.println("Camel Visual starting directory: " + start);
        System.out.println("\n");

        Stream<Path> files = Files.find(start, Integer.MAX_VALUE, (f, a) -> f.getFileName().toString().endsWith(".java"));
        files.forEach(f -> {
            try {
                visualizeFile(f.toFile());
            } catch (Exception e) {
                e.printStackTrace();
                // ignore
            }
        });
    }

    private static void visualizeFile(File file) throws Exception {
//        System.out.println("Parsing file: " + file);

//        if (true)
//        throw new IllegalArgumentException("Forced");
        String fqn = file.getPath();
        String baseDir = ".";
        JavaType out = Roaster.parse(file);
        // we should only parse java classes (not interfaces and enums etc)
        if (out instanceof JavaClassSource) {
            JavaClassSource clazz = (JavaClassSource) out;
            List<CamelNodeDetails> result = RouteBuilderParser.parseRouteBuilderTree(clazz, baseDir, fqn, true);
//            System.out.println("File: " + file);
            result.forEach(r -> {
                System.out.println("Route: " + r.getRouteId() + "\t\t\t" + r.getClassName() + "(" + getShortName(r.getClassName()) + ".java:" + r.getLineNumber() + ")");
                System.out.println(r.dump(0));
                System.out.println("");
            });
        }
    }

    private static String getShortName(String fqn) {
        int pos = fqn.lastIndexOf('.');
        if (pos != -1) {
            return fqn.substring(pos + 1);
        } else {
            return fqn;
        }
    }
}
