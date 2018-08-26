chars = list(input())
dp = [0 for i in chars]
n = len(chars)
for i in range(n):
    for j in range(i):
        if ord(chars[i]) - ord(chars[j]) > 0 and dp[i] < dp[j] + 1 :
            dp[i] = dp[j] + 1
l = 0
for i in dp:
    l = max(i, l)
print(26-l-1)
