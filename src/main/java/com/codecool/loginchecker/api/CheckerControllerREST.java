package com.codecool.loginchecker.api;

import com.codecool.loginchecker.service.CredentialsChecker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.io.IOException;

@RestController
public class CheckerControllerREST {

    private CredentialsChecker credentialsChecker;

    public CheckerControllerREST(CredentialsChecker credentialsChecker) {
        this.credentialsChecker = credentialsChecker;
    }

    @PostMapping("/api/{platform}")
    public boolean checkPlatform(@PathVariable("platform") String platform, HttpServletRequest request) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> credentials = gson.fromJson(request.getReader(), type);

        Method method = credentialsChecker.getClass().getMethod(platform, String.class, String.class);
        return (boolean) method.invoke(credentialsChecker, credentials.get("email"), credentials.get("password"));
    }
}
