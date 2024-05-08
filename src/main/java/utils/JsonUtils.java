package utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonUtils {
    public static Map<String, List<Integer>> jsonToMap_String_List(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, new TypeReference<Map<String, List<Integer>>>() {
        });
    }

    public static <T> List<T> jsonToList(String jsonString, Class<T> c) throws IOException {
          ObjectMapper mapper = new ObjectMapper();
          CollectionType typeReference =
          TypeFactory.defaultInstance().constructCollectionType(List.class, c);
         return mapper.readValue(jsonString, typeReference);
    }
}