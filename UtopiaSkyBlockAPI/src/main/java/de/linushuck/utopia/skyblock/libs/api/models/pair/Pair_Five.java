package de.linushuck.utopia.skyblock.libs.api.models.pair;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pair_Five<A, B, C, D, E>
{

    private A first;
    private B second;
    private C third;
    private D fourth;
    private E fifth;


    public boolean equals(Pair_Five<A, B, C, D, E> other)
    {
        return other.getFirst().equals(first) && other.getSecond().equals(second) && other.getThird()
                .equals(third) && other.getFourth().equals(fourth) && other.getFifth().equals(fifth);
    }
}