package pl.mlata.financecontrol.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public @ResponseBody String test() {
        return "TEST_FROM_BOOT";
    }
}
