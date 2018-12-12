package ${entityTypeInfo.packageName};
import java.io.Serializable;
<#list imports as import>
import ${import};
</#list>

public class ${entityTypeInfo.simpleName} implements Serializable{
	private static final long serialVersionUID = 1L;
	<#list attrInfos as attrTypeInfo>
	private ${attrTypeInfo.simpleName} ${attrTypeInfo.javaName};
	</#list>

	<#list attrInfos as attrTypeInfo>
	public ${attrTypeInfo.simpleName} ${attrTypeInfo.javaGetMethodName}() {
		return ${attrTypeInfo.javaName};
	}
	public void ${attrTypeInfo.javaSetMethodName}(${attrTypeInfo.simpleName} ${attrTypeInfo.javaName}) {
		this.${attrTypeInfo.javaName}=${attrTypeInfo.javaName};
	}	
	</#list>
	
}
