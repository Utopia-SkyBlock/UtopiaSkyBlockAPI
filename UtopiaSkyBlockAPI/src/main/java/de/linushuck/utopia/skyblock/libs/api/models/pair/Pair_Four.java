package de.linushuck.utopia.skyblock.libs.api.models.pair;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pair_Four<A, B, C, D>
{

    private A first;
    private B second;
    private C third;
    private D fourth;

    public boolean equals(Pair_Four<A, B, C, D> other)
    {
        return other.getFirst().equals(first) && other.getSecond().equals(second) && other.getThird()
                .equals(third) && other.getFourth().equals(fourth);
    }
}