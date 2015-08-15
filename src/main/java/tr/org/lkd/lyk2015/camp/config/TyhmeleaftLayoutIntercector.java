package tr.org.lkd.lyk2015.camp.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TyhmeleaftLayoutIntercector extends HandlerInterceptorAdapter {

	public static final String MAİN_LAYOUT = "layout/main";

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// gidilecek olan viev'in ismini veriyor.
		String originalViewName = modelAndView.getViewName();

		// redirect varsa dokunmucaz. Intersectioni geçircez.
		if (isRedirectOrForward(originalViewName)) {
			return;
		}

		// Controllerdan gelen view'ı değiştiriyoruz.
		modelAndView.setViewName(MAİN_LAYOUT);

		// modele attribute ekledik
		modelAndView.addObject("layout_main", originalViewName);

	}

	private boolean isRedirectOrForward(String originalViewName) {
		return originalViewName.startsWith("redirect:") || originalViewName.startsWith("forward:");
	}

}
