package ${package};

<#list imports as import>
import ${import};
</#list>

public class ${class}{
	
	<#list fields as field>
	private ${field.type} ${field.name};
	</#list>

	<#list methods as method>
	public ${method.ret} ${method.name}(${method.paramStr}) {
		${method.core}
	}	
	</#list>
	
}
