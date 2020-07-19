package com.brainacad;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpClientHelper {

    public static HttpResponse get(String endpointUrl, String parameters) throws IOException {
        //Создаём переменую headers типа Map
        Map<String, String> headers = new HashMap<>();
        //Добавляем в headers наш заголовок
        headers.put("Content-Type", "application/json");
        return get(endpointUrl, parameters, headers);
    }

    //REST GET запрос
    public static HttpResponse get(String endpointUrl, String parameters, Map<String, String> headers) throws IOException {
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP GET запрос из URL и параметров
        HttpGet request = new HttpGet(endpointUrl + parameters);
        //HttpGet requestUser = new HttpGet(endpointUrl + "/" + parameters);

        //добавляем в запрос необходимые хедеры
        for (String headerKey : headers.keySet()) {
            request.addHeader(headerKey, headers.get(headerKey));
        }

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(request);

        //возвращаем response
        return response;
    }


    public static HttpResponse post(String endpointUrl, String parameters) throws IOException {
        //Создаём переменую headers типа Map
        Map<String, String> headers = new HashMap<>();
        //Добавляем в headers наш заголовок
        headers.put("Content-Type", "application/json");
        return post(endpointUrl, parameters, headers);
    }

    public static HttpResponse post(String endpointUrl, String body, Map<String, String> headers) throws IOException {
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP POST запрос из URL и параметров
        HttpPost post = new HttpPost(endpointUrl);

        //добавляем в запрос необходимые хедеры
        for (String headerKey : headers.keySet()) {
            post.addHeader(headerKey, headers.get(headerKey));
        }

        //добавляем к запросу тело запроса
        post.setEntity(new StringEntity(body));

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(post);

        //возвращаем response
        return response;
    }

    public static String getBodyFromResponse(HttpResponse response) throws IOException {
        //создаём ридер буффера и передаём в него входящий поток респонса
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line;

        //получаем в цикле построчно строки из входящего потока и собираем в одну строку
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    //PUT request:
    public static HttpResponse put(String endpointUrl, String parameters) throws IOException {
        //Создаём переменую headers типа Map
        Map<String, String> headers = new HashMap<>();
        //Добавляем в headers наш заголовок
        headers.put("Content-Type", "application/json");
        return put(endpointUrl, parameters, headers);
    }

    public static HttpResponse put(String endpointUrl, String body, Map<String, String> headers) throws IOException {
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP PUT запрос из URL и параметров
        HttpPut put = new HttpPut(endpointUrl);

        //добавляем в запрос необходимые хедеры
        for (String headerKey : headers.keySet()) {
            put.addHeader(headerKey, headers.get(headerKey));
        }

        //добавляем к запросу тело запроса
        put.setEntity(new StringEntity(body));

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(put);

        //возвращаем response
        return response;
    }

    //PATCH request:
    public static HttpResponse patch(String endpointUrl, String parameters) throws IOException {
        //Создаём переменую headers типа Map
        Map<String, String> headers = new HashMap<>();
        //Добавляем в headers наш заголовок
        headers.put("Content-Type", "application/json");
        return patch(endpointUrl, parameters, headers);
    }

    public static HttpResponse patch(String endpointUrl, String body, Map<String, String> headers) throws IOException {
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP PATCH запрос из URL и параметров
        HttpPatch patch = new HttpPatch(endpointUrl);

        //добавляем в запрос необходимые хедеры
        for (String headerKey : headers.keySet()) {
            patch.addHeader(headerKey, headers.get(headerKey));
        }

        //добавляем к запросу тело запроса
        patch.setEntity(new StringEntity(body));

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(patch);

        //возвращаем response
        return response;
    }

    //DELETE request:
    public static HttpResponse delete(String endpointUrl, String parameters) throws IOException {
        //Создаём переменую headers типа Map
        Map<String, String> headers = new HashMap<>();
        //Добавляем в headers наш заголовок
        headers.put("Content-Type", "application/json");
        return delete(endpointUrl, parameters, headers);
    }

    public static HttpResponse delete(String endpointUrl, String parameters, Map<String, String> headers) throws IOException {
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP DELETE запрос из URL и параметров
        HttpDelete request = new HttpDelete(endpointUrl + "/" + parameters);

        //добавляем в запрос необходимые хедеры
        for (String headerKey : headers.keySet()) {
            request.addHeader(headerKey, headers.get(headerKey));
        }

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(request);

        //возвращаем response
        return response;
    }

}
