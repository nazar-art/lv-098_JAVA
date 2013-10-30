package tc.lv.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tc.lv.domain.User;

@Controller
public class SecureController {
	
	@ModelAttribute
	public User populateCurrentUser(){
		return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@RequestMapping(value = "/secure/", method = RequestMethod.GET)
	public String secure() {
		return "secure";
	}
	
}
