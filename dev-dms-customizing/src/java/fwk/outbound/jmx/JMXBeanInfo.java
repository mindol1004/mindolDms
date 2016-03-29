package fwk.outbound.jmx;

import java.util.List;

public class JMXBeanInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1738828430362847389L;

	public String name;
	public String className;
	public String description;
	public List<JMXBeanAtrribute> attributes;
	public List<JMXBeanOperation> operations;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name=").append(name);
		sb.append(", className=").append(className);
		sb.append(", description=").append(description);
		sb.append(", attributes=").append(attributes);
		sb.append(", operations=").append(operations);
		return sb.toString();
	}

}
