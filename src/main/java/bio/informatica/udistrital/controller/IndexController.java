package bio.informatica.udistrital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by hacortesn on 23/09/16.
 */
@Controller("/jsp")
public class IndexController {

    @GetMapping("/index.hacn")
    public String welcome(Map<String, Object> model) {
        /*model.put("time", new Date());
        model.put("message", "mensajito");*/
        return "index";
    }

    @RequestMapping("/foo")
    public String foo(Map<String, Object> model) {
        throw new RuntimeException("Foo");
    }
}
