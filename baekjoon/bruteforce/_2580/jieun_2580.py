"""
작성자: 이지은
문제: 스도쿠. 게임 시작 전 스도쿠 판에 쓰여 있는 숫자들의 정보가 주어질 때 모든 빈 칸이 채워진 최종 모습을 출력하는 프로그램을 작성하시오.
해결: 백트래킹 - 사간초과로 인해 check하는 함수를 모두 분리
이슈: python3로 제출했을 때는 시간초과 발생, pypy3로 제출했을 때는 통과
작성일: 2023-04-21

시간 초과 테스트용
[ 입력 ]
0 0 0 0 4 3 0 0 0
0 0 0 0 0 0 1 0 0
0 0 0 0 5 0 0 0 0
0 8 0 7 0 0 0 2 0
0 6 0 0 0 0 0 0 3
0 0 0 0 0 0 0 4 0
0 0 5 8 0 0 6 0 0
4 0 0 1 0 0 0 0 0
3 0 0 0 0 0 5 0 0

[ 결과 ]
6 7 1 9 4 3 2 5 8
5 4 2 6 8 7 1 3 9
8 3 9 2 5 1 4 6 7
1 8 3 7 6 4 9 2 5
9 6 4 5 2 8 7 1 3
2 5 7 3 1 9 8 4 6
7 1 5 8 3 2 6 9 4
4 9 6 1 7 5 3 8 2
3 2 8 4 9 6 5 7 1
"""
#가로 check 함수
def checkRow(y, v):
    if v in sudoko[y]:
        return False
    return True

#세로 check 함수
def checkCol(x, v):
    for i in range(9):
        if v == sudoko[i][x]:
            return False
    return True

#3x3 비교
def check(y, x, value):
    ny = (y//3)*3
    nx = (x//3)*3
    for a in range(ny, ny+3):
        for b in range(nx, nx+3):
            if sudoko[a][b] == value:
                return False
    return True

#dfs 함수
def dfs(depth):
    if depth == len(zeroList):
        for i in range(9):
            print(*sudoko[i])
        exit(0) #종료

    for v in range(1, 10): #value: 비교 대상, 스도쿠의 값이 1~9이기 때문에 범위(1, 10)
        x = zeroList[depth][1]
        y = zeroList[depth][0]

        if checkRow(y, v) and checkCol(x, v) and check(y, x, v): #결과가 true일 때만
            sudoko[y][x] = v
            dfs(depth+1)
            sudoko[y][x] = 0 #0으로 초기화

sudoko = [] #9x9 배열
zeroList = [] #0값을 가진 좌표를 담을 리스트
for i in range(9):
    sudoko.append(list(map(int, input().split())))  #2차원 배열에 스도쿠 값 입력
    for j in range(9):
        if sudoko[i][j] == 0: #값이 0이라면
            zeroList.append((i, j))

dfs(0) #실행