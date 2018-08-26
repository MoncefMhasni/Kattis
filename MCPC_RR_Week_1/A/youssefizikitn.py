""" 
	To reprsent n in base 10 we need floor(log10(n)) + 1 digits. So, to reprsent n! we'll need floor(log10(n!)) + 1 digits.
	    log10(n!) = log10(1*2*...*n) = log10(1) + log10(2) + ... + log10(n)
	For small numbers (<256), we can just compute the sum of log10(i) for i in [1 .. n] ==> O(n) complexity.
	
	But for large numbers this will result in a TLE verdict, so we need another way of computing it.
	Stirling’s approximation is what we need to compute it with O(1) complexity (I guess?), Using the Gamma function.
	The Gamma function for integers is: G(n + 1) = n!
	So Stirling's approximation says, with x = n+1: log G(x) ≈ (x – 1/2)*log(x) – x  + (1/2)*log(2*pi) + 1/(12*x) – 1/(360*x^3) + 1/(1260*x^5) – …
	It's an asymptotic series for log G(x). More terms mean more precision, but we only need a few terms since we're computing the whole part of the log G(x).
	This gives us the natural log of G(x), so to get the log base 10 we devide the result by log(10) since log10(x) = log(x)/log(10).
	My solution for the problem is coded below in Python 3:
"""


from math import log, floor, pi
from sys import stdin, stdout

lines = stdin.read().split('\n')[:-1]

for l in lines:
    n = int(l)
    if n < 256:
        stdout.write(str(floor(sum([log(i, 10) for i in range(1, n+1)])) + 1) + "\n")
    else:
        x = n + 1
        stdout.write(str(floor(((x-1/2)*log(x) - x + 1/2*log(2*pi) + 1/(12*x))/log(10)) + 1) + "\n")
stdout.flush()
