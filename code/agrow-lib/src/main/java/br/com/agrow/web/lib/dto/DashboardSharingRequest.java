package br.com.agrow.web.lib.dto;

import java.util.List;
import java.util.UUID;

public class DashboardSharingRequest {

	private UUID fromUserId;
	private String mesAno;
	private List<UUID> usersId;
	private String nome;

	public DashboardSharingRequest() {

	}

	public DashboardSharingRequest(List<UUID> usersId) {
		this.usersId = usersId;
	}

	public UUID getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(UUID fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public List<UUID> getUsersId() {
		return usersId;
	}

	public void setUsersId(List<UUID> usersId) {
		this.usersId = usersId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "DashboardSharingRequest [fromUserId=" + fromUserId + ", mesAno=" + mesAno + ", usersId=" + usersId + "]";
	}
}
