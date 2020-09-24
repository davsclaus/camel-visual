package org.apache.camel.visual;

import org.apache.camel.builder.RouteBuilder;

public class MyRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:foo").routeId("myRoute")
            .recipientList(header("foo"))
            .to("mock:zzz")
            .toD("log:hi");
    }
}
