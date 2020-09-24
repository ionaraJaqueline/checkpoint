package entities;

public interface Identificavel extends Cloneable {

	public Integer getId();

	public void setId(Integer id);

	public Identificavel clone();
}
