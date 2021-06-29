package com.dh.sp.graphql.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.graphql.spring.boot.test.GraphQLResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TestUtils {

    private static final String DATA = "data";
    private final ObjectMapper objectMapper;

    public JsonNode objectToJsonNode(Object obj){
        return objectMapper.valueToTree(obj);
    }
    public  <T> Optional<T> graphQLResponseToObject(final GraphQLResponse graphQLResponse, final String nodeName, final  Class<T> valueType){
        try {
            final JsonNode jsonNode = graphQLResponse.readTree();
            final String createEmployee = jsonNode.get("data").get(nodeName).toString();
            return  Optional.ofNullable(objectMapper.readValue(createEmployee, valueType));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public <T> List<T> graphQLResponseToObjectList(final GraphQLResponse graphQLResponse, final String nodeName, final Class<T> valueType){
        try {
            final JsonNode jsonNode = graphQLResponse.readTree();
            final String objectList = jsonNode.get(DATA).get(nodeName).toString();
            final CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, valueType);
            return objectMapper.readValue(objectList, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
