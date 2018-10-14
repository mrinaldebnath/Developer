package Controllers;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AjaxController {

	@RequestMapping("/ajaxjsp")
	public ModelAndView mymethod(){
		return new ModelAndView("ajaxjsp","msg","Welcome First Spring");
	}
}
