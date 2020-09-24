package entities;

public enum Group {
COLABORADOR("Colaborador"), COORDENADOR("Coordenador"), ADMIN("Admin");
	
	private String nome;
	
	private Group(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
