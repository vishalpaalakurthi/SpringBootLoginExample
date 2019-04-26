package com.java.login.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class defaultErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest httpServletRequest) {
        Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><h1 style='color:red'>");

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                sb.append("error-404");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                sb.append("error-500");
            }
        }
        sb.append(" </h1></body></html>");
        return sb.toString();
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
