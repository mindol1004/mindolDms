package fwk.outbound.jmx;

public class JMXBeanOperationParameter implements java.io.Serializable {

	private static final long serialVersionUID = -1674102762258253131L;

	public String name;
	public String type;
	public String description;
	public Object value;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name=").append(name);
		sb.append(", type=").append(type);
		sb.append(", description=").append(description);
		sb.append(", value=").append(value);
		return sb.toString();
	}

}
