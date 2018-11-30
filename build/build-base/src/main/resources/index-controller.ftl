package  ${indexControllerTypeInfo.packageName};
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class ${indexControllerTypeInfo.simpleName} {
	@RequestMapping
	public Object index() {
		return "index";
	}
}