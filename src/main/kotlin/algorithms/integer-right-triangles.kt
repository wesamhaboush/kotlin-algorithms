package algorithms

/*
Integer right triangles
Problem 39
If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.

{20,48,52}, {24,45,51}, {30,40,50}

For which value of p ≤ 1000, is the number of solutions maximised?

solution ideas:

a + b + c = p
a^2 + b^2 = c^2

for each p, scan for the right solution? sounds crude

There is a theorem by Wade called Height-excess Theorem. It states that for all integers
(h, k), the triples are:

(h + dk, dk + (dk)^2 / 2h, h + dk + (dk)^2 / 2h )

where to find d, you put h as pq^2, where p is square free, and q positive.
Set d = 2pq if p is odd, or d = pq if p is even

There is also Dickson's methods, which says:

To find integer solutions to x^2+y^2=z^2, find positive integers r, s, and t such that
r^2=2st is a square.

Then:

x=r+s,y=r+t,z=r+s+t

From this we see that r is any even integer and that s and t are factors of (r^2)/2.
All Pythagorean triples may be found by this method

*/


fun r(): Sequence<Long> = generateSequence(2L) { it + 2 }

fun rst_triples(r: Long): Sequence<Triple<Long, Long, Long>> =
        findFactors(r * r / 2L).map { Triple(r, it.first, it.second) }.asSequence()

fun max_p_solution_count(maxPerimeter: Long): Map<Long, Set<Set<Long>>> =
        r()
                .takeWhile { it < maxPerimeter }
                .flatMap { rst_triples(it) } //r,s,t
                .map {
                    setOf(it.first + it.second, it.first + it.third, it.first + it.second + it.third)
                } //x,y,z
                .filter { perimeter(it) <= maxPerimeter } // we are scanning up to max
//                .onEach { println(it) }
                .groupBy { perimeter(it) } // we want a map of perimeter => SetOf(Solutions)
                .mapValues { it.value.toSet() } // groupBy produces perimeter => ListOf(Solutions), so change list to set

fun perimeter(triangleSides: Set<Long>): Long = triangleSides.sum()
