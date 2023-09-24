package com.cakefactory;

import com.cakefactory.services.Catalog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class IndexController {

    private Catalog catalog;

    public IndexController(Catalog catalog) {
        this.catalog = catalog;
    }

    @GetMapping("/index")
    public ModelAndView findAllItems(Map<String, Object> model) {
        var items = catalog.findAllItems();
        model.put("items", items);
        return new ModelAndView("index", model);
    }

}