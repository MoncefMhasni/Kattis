t = int(input())
tab = []
for i in range(t) :
    n = int(input())
    pr = 0
    tab1 = [int(j) for j in input().split()]
    tab2 = [int(j) for j in input().split()]
    tab1.sort()
    tab2.sort()
    for j in range(n):
        pr += tab1[j]*tab2[-j-1]
    print('Case #' + str(i+1) + ":", pr)
