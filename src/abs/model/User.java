package abs.model;

public interface User {

	public String getEmail();

	public String getName();

	public String getPassword();

	@Override
	public String toString();
}