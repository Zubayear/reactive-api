package com.zubayear.pursuitofhappyness.SharedKarnel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityBase<TId> {
    private TId id;
}
