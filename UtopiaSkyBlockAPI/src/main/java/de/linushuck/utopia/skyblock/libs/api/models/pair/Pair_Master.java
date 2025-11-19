package de.linushuck.utopia.skyblock.libs.api.models.pair;

public class Pair_Master
{
    public static <A, B> Pair<A, B> of(A first, B second)
    {
        return new Pair<>(first, second);
    }

    public static <A, B, C> Pair_Three<A, B, C> of(A first, B second, C third)
    {
        return new Pair_Three<>(first, second, third);
    }

    public static <A, B, C, D> Pair_Four<A, B, C, D> of(A first, B second, C third, D fourth)
    {
        return new Pair_Four<>(first, second, third, fourth);
    }

    public static <A, B, C, D, E> Pair_Five<A, B, C, D, E> of(A first, B second, C third, D fourth, E fifth)
    {
        return new Pair_Five<>(first, second, third, fourth, fifth);
    }
}
