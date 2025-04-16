package com.asthana.spring_boot_mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.servlet.ModelAndView;

import model.data;

// import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;




@Controller
public class HomeController {


  @ModelAttribute
  public void ModelData ( Model m)
  {
    m.addAttribute("name", "Aditya");
  }


@RequestMapping("/")
 public String home(){
    return "Index";
 }


@RequestMapping("add")
public String add(@RequestParam("num1") int i,@RequestParam("num2") int j , Model m ){
  
  // ModelAndView mv= new ModelAndView();
  // mv.setViewName("result");

  int num3 = i+j;

  // mv.addObject("num3",num3);

  m.addAttribute("num3", num3);

  return "result" ;
}



@RequestMapping("addData")
public String DataAddittion(@ModelAttribute("Data") data d)
{
  
  return "result" ;
}


}
