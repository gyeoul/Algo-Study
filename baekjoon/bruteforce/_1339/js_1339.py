from collections import defaultdict
N = int(input())
word = defaultdict(int)
for _ in range(N):
    for i,s in enumerate(reversed(input())):
        word[s]+=10**i
ans = sorted([count for  count in word.values()])
init,answer = 9,0
for c in reversed(ans):
    answer+=init*c
    init-=1
print(answer)