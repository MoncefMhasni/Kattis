tt = 1

t = int(raw_input())
for i in range(t):
    x, A, B = raw_input().split()
    n = len(A)
    m = len(B)
    
    #convert x to base 10
    b = 1
    y = 0
    for i in range(len(x)):
        y += b * A.index(x[len(x) - i - 1])
        b *= n
        
    # Convert y to base m
    ans = ""
    while y > 0:
        ans += B[y % m]
        y /= m
        
    print 'Case #{0}: {1}'.format(tt, ans[::-1])
    tt += 1