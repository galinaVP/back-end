package com.brainacad;

import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class RestTest {

    private static final String URL = "https://reqres.in/";

    @Test//GET метод
    public void checkGetResponseStatusCode() throws IOException {
        String endpoint = "/api/users";
        //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.get(URL + endpoint, "?page=2");
        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 200", 200, statusCode);
    }

    @Test//GET метод
    public void checkGetResponseBodyNotNull() throws IOException {
        String endpoint = "/api/users";
        //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.get(URL + endpoint, "?page=2");

        //Конвертируем входящий поток тела ответа в строку
        String body = HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertNotEquals("Body shouldn't be null", null, body);
    }

    @Test//GET метод Single User
    public void checkGetResponseFirstNameById() throws IOException {
        String endpoint = "/api/users";
        String jsonPath = "$..data.first_name";
        //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.get(URL + endpoint, "/2");

        //Конвертируем входящий поток тела ответа в строку
        String body = HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        String firstName = JsonUtils.stringFromJSONByPath(body,jsonPath);
        String expected = "[\"Janet\"]";
        Assert.assertEquals("First name for User with Id 2 is Janet", expected, firstName);
    }
    @Test//GET метод Single User Not Found
    public void checkGetResponseBodyEmpty() throws IOException {
        String endpoint = "/api/users";
        //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.get(URL + endpoint, "/23");

        //Конвертируем входящий поток тела ответа в строку
        String body = HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertEquals("Empty body for user 23", "{}", body);
    }
    @Test//GET метод List Resource
    public void checkGetResponseBody() throws IOException {
        String endpoint = "/api/users";
        //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.get(URL + endpoint, "/23");

        //Конвертируем входящий поток тела ответа в строку
        String body = HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertEquals("Empty body for user 23", "{}", body);
    }
    @Test//POST метод
    public void checkPostResponseStatusCode() throws IOException {
        String endpoint = "/api/users";
        //создаём тело запроса
        String requestBody = "{\"name\": \"morpheus\",\"job\": \"leader\"}";

        //Выполняем REST POST запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.post(URL + endpoint, requestBody);

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 201", 201, statusCode);
    }

    @Test//POST метод
    public void checkPostResponseBodyNotNull() throws IOException {
        String endpoint = "/api/users";
        //создаём тело запроса
        String requestBody = "{\"name\": \"morpheus\",\"job\": \"leader\"}";

        //Выполняем REST POST запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.post(URL + endpoint, requestBody);

        //Конвертируем входящий поток тела ответа в строку
        String body = HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertNotEquals("Body shouldn't be null", null, body);
    }

    //TODO: напишите по тесткейсу на каждый вариант запроса на сайте https://reqres.in
    //TODO: в тескейсах проверьте Result Code и несколько параметров из JSON ответа (если он есть)

    @Test//PUT метод
    public void checkPutResponseStatusCode() throws IOException {
        String endpoint = "/api/users";
        //создаём тело запроса
        String requestBody = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";

        //Выполняем REST PUT запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.put(URL + endpoint, requestBody);

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 200", 200, statusCode);
    }

    @Test//PUT метод
    public void checkPutResponseBodyNotNull() throws IOException {
        String endpoint = "/api/users";
        //создаём тело запроса
        String requestBody = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";

        //Выполняем REST PUT запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.put(URL + endpoint, requestBody);

        //Конвертируем входящий поток тела ответа в строку
        String body = HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertNotEquals("Body shouldn't be null", null, body);
    }

    @Test// PATCH метод
    public void checkPatchResponseStatusCode() throws IOException {
        String endpoint = "/api/users";
        //создаём тело запроса
        String requestBody = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";

        //Выполняем REST PATCH запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.patch(URL + endpoint, requestBody);

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 200", 200, statusCode);
    }

    @Test//PATCH метод
    public void checkPatchResponseBodyNotNull() throws IOException {
        String endpoint = "/api/users";
        //создаём тело запроса
        String requestBody = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";

        //Выполняем REST PATCH запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.patch(URL + endpoint, requestBody);

        //Конвертируем входящий поток тела ответа в строку
        String body = HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertNotEquals("Body shouldn't be null", null, body);
    }

    @Test//DELETE метод
    public void checkDeleteResponseStatusCode() throws IOException {
        String endpoint = "/api/users";
        //Выполняем REST DELETE запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.delete(URL + endpoint, "2");
        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 204", 204, statusCode);
    }

    @Test//DELETE метод
    public void checkDeleteResponseBodyWithNoContent() throws IOException {
        String endpoint = "/api/users";
        //Выполняем REST DELETE запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.delete(URL + endpoint, "2");

        //Конвертируем входящий поток тела ответа в строку
        String body = HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        //Assert.assertEquals("Body should be null", null, body);
    }

}
