package br.com.agrow.web.compartilhamentoapi.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("sharings")
public class SharingItem {

	@Id
	private String id;
	private UUID fromUserId;
	private List<UUID> toUsersId;
	private String dashboardName;
	private byte[] dashboardData;
	private LocalDateTime createdAt;

	public SharingItem() {

	}

	public SharingItem(UUID fromUserId, List<UUID> toUsersId, String dashboardName, byte[] dashboardData, LocalDateTime createdAt) {
		this.fromUserId = fromUserId;
		this.toUsersId = toUsersId;
		this.dashboardName = dashboardName;
		this.dashboardData = dashboardData;
		this.createdAt = createdAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UUID getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(UUID fromUserId) {
		this.fromUserId = fromUserId;
	}

	public List<UUID> getToUsersId() {
		return toUsersId;
	}

	public void setToUsersId(List<UUID> toUsersId) {
		this.toUsersId = toUsersId;
	}

	public String getDashboardName() {
		return dashboardName;
	}

	public void setDashboardName(String dashboardName) {
		this.dashboardName = dashboardName;
	}

	public byte[] getDashboardData() {
		return dashboardData;
	}

	public void setDashboardData(byte[] dashboardData) {
		this.dashboardData = dashboardData;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
