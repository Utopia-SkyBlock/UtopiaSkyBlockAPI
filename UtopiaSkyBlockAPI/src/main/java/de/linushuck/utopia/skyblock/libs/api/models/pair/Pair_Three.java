package de.linushuck.utopia.skyblock.libs.api.models.pair;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pair_Three<A, B, C>
{

    private A first;
    private B second;
    private C third;

    public boolean equals(Pair_Three<A, B, C> other)
    {
        return other.getFirst().equals(first) && other.getSecond().equals(second) && other.getThird().equals(third);
    }
}