package domain;

import json.*;

import java.util.ArrayList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    Tuple<String, Integer>[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = exams;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject result = super.toJsonObject();
        JsonObject[] list = new JsonObject[exams.length];
        for (int i = 0; i < exams.length; i++) {
            list[i] = new JsonObject(new JsonPair("course", new JsonString(exams[i].key)),
                    new JsonPair("mark", new JsonNumber(exams[i].value)),
                    new JsonPair("passed", new JsonBoolean(exams[i].value > 2))
            );
            }
        result.add(new JsonPair("exams", new JsonArray(list)));
        return result;
    }
}