package com.example.languageexchangebackend.extras;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TestPerson {
    private String name;
    private int age;

    public TestPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        // JSON example
        String json = "[{\"name\":\"John\",\"age\":30},{\"name\":\"Jane\",\"age\":25},{\"name\":\"Bob\",\"age\":35}]";

        // Create a Gson instance
        Gson gson = new Gson();

        // Define the type of the collection you want to parse into
        Type personListType = new TypeToken<List<TestPerson>>(){}.getType();

        // Parse the JSON into a List of Person objects
        List<TestPerson> people = gson.fromJson(json, personListType);

        // Now, 'people' contains a list of Person objects
        for (TestPerson person : people) {
            System.out.println("Name: " + person.getName());
            System.out.println("Age: " + person.getAge());
        }
    }
}