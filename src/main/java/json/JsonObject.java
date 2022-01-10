package json;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    HashMap<String, Json> jsonPairs = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair tuple: jsonPairs) {
            this.jsonPairs.put(tuple.key, tuple.value);
        }
    }

    @Override
    public String toJson() {
        StringBuilder result = new StringBuilder();
        result.append("{");
        for (String tupleKey: jsonPairs.keySet()) {
            result.append("'");
            result.append(tupleKey);
            result.append("'");
            result.append(": ");
            result.append(jsonPairs.get(tupleKey).toJson());
            result.append(",");
        }
        if (result.length() != 1) {
            result.deleteCharAt(result.length() - 1);
        }
        result.append("}");
        return result.toString();
    }

    public void add(JsonPair jsonPair) {
        jsonPairs.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return jsonPairs.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject result = new JsonObject();
        for (String name : names) {
            if (find(name) != null) {
                result.add(new JsonPair(name, find(name)));
            }
        }
        return result;
    }
}
