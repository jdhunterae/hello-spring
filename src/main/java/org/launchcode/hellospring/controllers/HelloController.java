package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@ResponseBody
@RequestMapping("hello")
public class HelloController {
    private static final String[] LANGUAGES = {"English", "French", "Spanish", "German", "Italian"};
    private static final HashMap<String, String> GREETINGS;

    static {
        GREETINGS = new HashMap<String, String>();
        GREETINGS.put("English", "Hello");
        GREETINGS.put("French", "Bonjour");
        GREETINGS.put("German", "Guten tag");
        GREETINGS.put("Spanish", "Hola");
        GREETINGS.put("Italian", "Ciao");
    }

    @GetMapping("goodbye")
    public String goodbye() {
        return "Goodbye, Spring!";
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String helloWithQueryParam(@RequestParam String name, @RequestParam String language) {
        return greet(name, language);
    }

    @GetMapping("{name}")
    public String helloWithPathParam(@PathVariable String name) {
        return greet(name, LANGUAGES[0]);
    }

    @GetMapping("form")
    public String helloForm() {
        return "<html>" +
                "<body>" +
                "<form action='/hello' method='post'>" +
                "<input type='text' name='name'/>" +
                getSelector() +
                "<input type='submit' value='Greet me!'/>" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    private static String getSelector() {
        StringBuilder res = new StringBuilder();

        res.append("<select name='language'>");
        for (String language : LANGUAGES) {
            res.append(String.format("<option value='%s'>%s</option>", language, language));
        }
        res.append("</select>");

        return res.toString();
    }

    private static String greet(String name, String language) {
        return String.format("%s, %s", GREETINGS.get(language), name);
    }
}
