package de.linushuck.utopia.skyblock.libs.api.models.pair;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pair<A, B>
{

    private A first;
    private B second;

    public boolean equals(Pair<A, B> other)
    {
        return other.getFirst().equals(first) && other.getSecond().equals(second);
    }

    public String toString()
    {
        return "Pair{first=" + first + ", second=" + second + '}';
    }
}