package com.codecool.loginchecker.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.Map;
import java.io.IOException;

@RestController
public class CheckerControllerREST {

    @PostMapping("/api/{platform}")
    public String checkPlatform(@PathVariable("platform") String platform, HttpServletRequest request) throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> credentials = gson.fromJson(request.getReader(), type);

        System.out.println(platform);

        return "valami";
    }

}
