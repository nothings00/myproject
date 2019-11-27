package com.znothings.echarts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author zenghh, 625111833@qq.com
 * @date 2019-10-30 17:15
 * @version 1.0.0
 */
@Controller
@RequestMapping("/echarts")
public class EchartsController {
    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("title","end-front-home");
        return "/echarts/home";
    }
}
