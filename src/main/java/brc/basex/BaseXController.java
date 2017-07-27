package brc.basex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class BaseXController {

    @GetMapping("/")
    public String index(@RequestParam Map<String,String> params, Model model) throws Exception {
        model.addAllAttributes(params);
        return "redirect:/home";
    }

    @GetMapping("/{view}")
    public String view(@PathVariable("view") String view, @RequestParam Map<String,String> params, Model model) throws Exception {
        model.addAllAttributes(params);
        return view;
    }
}
