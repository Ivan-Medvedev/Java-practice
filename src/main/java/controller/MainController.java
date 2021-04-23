package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ModelAndView clients() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clients");
        return modelAndView;
    }

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public ModelAndView services() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("services");
        return modelAndView;
    }
}
