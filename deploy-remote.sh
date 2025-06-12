#!/bin/bash

# --- 설정 변수 ---
# 로컬 스크립트에서 인자로 전달받음
JAR_NAME="$1"
REMOTE_APP_DIR="$2"

# --- 스크립트 시작 ---

# MongoDB 서비스 시작 및 활성화
sudo systemctl start mongod
sudo systemctl enable mongod
sudo systemctl status mongod --no-pager
if [ $? -ne 0 ]; then
    echo "MongoDB 서비스 시작 실패."
    exit 1
fi
echo "MongoDB 시작 완료"

echo "--- 기존 Spring Boot 애플리케이션 종료 (만약 실행 중이라면) ---"
# 기존에 실행 중인 JAR 프로세스 찾아서 종료
# ps aux 결과에서 특정 JAR_NAME을 포함하는 프로세스를 찾고, grep 프로세스 자체는 제외
# awk '{print $2}'로 PID만 추출
# xargs -r kill -9는 추출된 각 PID에 대해 kill -9 명령을 실행하며,
# -r 옵션은 입력이 없으면 kill을 실행하지 않도록 합니다.
# JAR 파일이 백그라운드에서 실행될 때의 일반적인 패턴인 'java -jar'를 사용하여 더 정확하게 찾습니다.
# 단, JAR_NAME이 정확히 일치하는지 다시 확인합니다.
PIDS=$(ps aux | grep "java -jar $JAR_NAME" | grep -v "grep" | awk '{print $2}')
if [ -n "$PIDS" ]; then
    echo "기존 Spring Boot 애플리케이션 PID: $PIDS"
    # 각 PID에 대해 kill -9 명령을 실행하되, 오류가 발생해도 계속 진행하도록 수정
    echo "$PIDS" | xargs -r -n 1 sudo kill -9 || true
    # '|| true'를 추가하여 kill 명령이 실패하더라도 스크립트가 종료되지 않도록 함
    echo "기존 Spring Boot 애플리케이션 종료 시도 완료."
else
    echo "실행 중인 Spring Boot 애플리케이션이 없습니다."
fi

# Spring Boot 애플리케이션 실행 전, 잠시 대기하여 프로세스가 완전히 종료될 시간을 줌
sleep 5 # 5초 대기

echo "--- Spring Boot 애플리케이션 실행 ---"
cd "$REMOTE_APP_DIR"
pwd
# nohup을 사용하여 백그라운드에서 실행하고, 로그를 nohup.out에 기록
nohup java -jar "$JAR_NAME" > nohup.out 2>&1 &

if [ $? -ne 0 ]; then
    echo "Spring Boot 애플리케이션 실행 실패."
    exit 1
fi
echo "Spring Boot 애플리케이션이 백그라운드에서 실행되었습니다."
echo "로그 확인: tail -f nohup.out"
echo "애플리케이션 PID 확인: ps aux | grep '$JAR_NAME'"

echo "--- 배포 완료 ---"