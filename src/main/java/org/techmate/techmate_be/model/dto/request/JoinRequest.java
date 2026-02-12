package org.techmate.techmate_be.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.techmate.techmate_be.model.enumeration.CommitmentLevel;

@Getter
@Setter
public class JoinRequest {
    private CommitmentLevel commitmentLevel;
}
