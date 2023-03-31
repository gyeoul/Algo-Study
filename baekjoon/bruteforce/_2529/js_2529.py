N = int(input())
Min, Max = 1e10, 0
giho = []
for gi in input().split():
    giho.append(gi)
number = [0 for _ in range(N+1)]
visited = [False for _ in range(10)]
def dfs(level):
    global Min, Max
    if level == N+1:
        temp = int("".join(map(str,number)))
        Min = min(temp,Min)
        Max = max(temp,Max)
        return
    for i in range(10):
        if not visited[i] and (level == 0 or (giho[level-1]=='<' and number[level-1]<i) or (giho[level-1]=='>' and number[level-1]>i)):
            number[level]=i
            visited[i]=True
            dfs(level+1)
            visited[i]=False
dfs(0)
Max = '0'*(N+1-len(str(Max)))+str(Max)
Min = '0'*(N+1-len(str(Min)))+str(Min)
print(Max)
print(Min)