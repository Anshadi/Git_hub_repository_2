package com.asthana.spring_boot_mvc_demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        System.out.println("We Finally Reached Home .");
        return "index.jsp";
    }

    // @RequestMapping("StringAddition")
    // public String sadd(@RequestParam("name") String name , @RequestParam("surname") String surname , HttpSession session){

    //     String FullName = name.concat(surname) ;

    //     session.setAttribute("Fullname",FullName);

    //     return "show.jsp" ;

    // }

        @RequestMapping("StringAddition")
        public ModelAndView sadd(@RequestParam("name") String name , @RequestParam("surname") String surname )
        {
            ModelAndView mv = new ModelAndView();
            String FullName = name.concat(surname);

            mv.setViewName("show.jsp");

            mv.addObject("Fullname", FullName);

            return mv;
        }
    
}
