package com.zubayear.pursuitofhappyness.Core.AgentAggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Agent is the parent of Complaint.
 * We'll not save complaint without Agent
 * We can only save this to db
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agent /*extends EntityBase<UUID> implements AggregateRootInterface*/ {
    @Id
    private Long id;
    private String name;
    private boolean active = true;
//    private Set<Complaint> complaint;
//    private final List<Complaint> complaints = new LinkedList<>();
//
//    public void addComplaint(Complaint complaint) throws Exception {
//        if (complaint == null) {
//            throw new Exception("Complaint can't be empty");
//        }
//        complaints.add(complaint);
//        // we can have Events i.e. as soon as we add a complaint we fire a domain event
//    }
}
