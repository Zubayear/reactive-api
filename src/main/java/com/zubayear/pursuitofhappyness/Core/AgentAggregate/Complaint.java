package com.zubayear.pursuitofhappyness.Core.AgentAggregate;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

/**
 * Complaint class is an entity
 * We're not going to get Complaint by itself.
 * We'll always get it with its parent i.e. Agent
 * We have to go through Agent to save Complaint to db
 */
@Data
public class Complaint /*extends EntityBase<UUID>*/ {
    @Id
    @Column("id")
    private Long id;
    @Column("complaint_status")
    private ComplaintStatus complaintStatus;
    @Column("sla")
    private int sla;
//    @Column("complaint_time")
//    private LocalDateTime complaintTime;
    @Column("description")
    private String description;
    @Column("agent_id")
    private Long agentId;
}
