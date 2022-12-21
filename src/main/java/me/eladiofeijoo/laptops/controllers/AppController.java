package me.eladiofeijoo.laptops.controllers;

import me.eladiofeijoo.laptops.services.LaptopByIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@RestController
public class AppController {

    private final Logger log = LoggerFactory.getLogger(LaptopByIdService.class);

    @Value("${app.endpoints}")
    String path;

    @GetMapping("/")
    public String welcome(HttpServletRequest req)  {
        return "Help: " + getCurrentUrl(req) + path;
    }

    private String getCurrentUrl(HttpServletRequest request){
        try{
            URL url = new URL(request.getRequestURL().toString());
            String host  = url.getHost();
            String userInfo = url.getUserInfo();
            String scheme = url.getProtocol();
            int port = url.getPort();
            String path = (String) request.getAttribute("javax.servlet.forward.request_uri");
            String query = (String) request.getAttribute("javax.servlet.forward.query_string");

            URI uri = new URI(scheme,userInfo,host,port,path,query,null);
            return uri.toString();
        }catch (MalformedURLException | URISyntaxException e){
            log.warn("error to create url help, " + e.getMessage());
        }
        return null;
    }
}
