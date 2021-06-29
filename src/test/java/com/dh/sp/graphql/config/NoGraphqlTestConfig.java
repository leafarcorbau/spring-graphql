package com.dh.sp.graphql.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@TestConfiguration
@ComponentScan("com.dh.sp.graphql.utils")
public class NoGraphqlTestConfig {

    @MockBean
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ServerEndpointExporter serverEndpointExporter;

}
