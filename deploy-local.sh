#!/bin/bash

# --- 설정 변수 ---
# EC2 인스턴스의 퍼블릭 IP 또는 DNS
EC2_HOST="43.201.68.168"
# EC2 인스턴스에 접속할 사용자 이름 (일반적으로 'ubuntu' 또는 'ec2-user')
EC2_USER="ubuntu"
# EC2 인스턴스 접속에 사용할 .pem 키 파일 경로
PEM_KEY_PATH="/Users/a82104/Dev/smartFarm/smartFarm.pem"
# Spring Boot 프로젝트의 루트 디렉토리 (이 스크립트가 실행되는 위치와 동일하다고 가정)
PROJECT_DIR="."
# 빌드된 JAR 파일 이름 (build.gradle 또는 pom.xml에서 확인)
JAR_NAME="backend-0.0.1-SNAPSHOT.jar"
# EC2 인스턴스에서 애플리케이션이 배포될 디렉토리
REMOTE_APP_DIR="/home/$EC2_USER/backend"

# --- 스크립트 시작 ---

echo "--- 1. Spring Boot 프로젝트 빌드 시작 ---"
# Gradle 프로젝트인 경우:
cd "$PROJECT_DIR"
./gradlew clean build -x test
if [ $? -ne 0 ]; then
    echo "Spring Boot 빌드 실패. 스크립트를 종료합니다."
    exit 1
fi
echo "Spring Boot 빌드 완료."

# Maven 프로젝트인 경우:
# cd "$PROJECT_DIR"
# mvn clean package -DskipTests
# if [ $? -ne 0 ]; then then
#     echo "Spring Boot 빌드 실패. 스크립트를 종료합니다."
#     exit 1
# fi
# echo "Spring Boot 빌드 완료."

# 빌드된 JAR 파일 경로 확인 (Gradle 기준: build/libs, Maven 기준: target)
JAR_PATH="$PROJECT_DIR/build/libs/$JAR_NAME"
# Maven인 경우: JAR_PATH="$PROJECT_DIR/target/$JAR_NAME"

if [ ! -f "$JAR_PATH" ]; then
    echo "JAR 파일이 '$JAR_PATH' 경로에 존재하지 않습니다. 빌드 경로를 확인해주세요."
    exit 1
fi
echo "JAR 파일 확인: $JAR_PATH"

echo "--- 2. JAR 파일 EC2 인스턴스로 전송 시작 ---"
# 원격 디렉토리 생성 (만약 존재하지 않으면)
ssh -i "$PEM_KEY_PATH" "$EC2_USER@$EC2_HOST" "mkdir -p $REMOTE_APP_DIR"
if [ $? -ne 0 ]; then
    echo "EC2 원격 디렉토리 생성 실패. 스크립트를 종료합니다."
    exit 1
fi

# SCP를 사용하여 JAR 파일 전송
scp -i "$PEM_KEY_PATH" "$JAR_PATH" "$EC2_USER@$EC2_HOST:$REMOTE_APP_DIR/$JAR_NAME"
if [ $? -ne 0 ]; then
    echo "JAR 파일 EC2 전송 실패. 스크립트를 종료합니다."
    exit 1
fi
echo "JAR 파일 EC2 인스턴스로 전송 완료: $REMOTE_APP_DIR/$JAR_NAME"

echo "--- 3. EC2 인스턴스에서 배포 스크립트 실행 ---"
# 원격 스크립트 실행 (다음 섹션의 deploy-remote.sh)
# 이 스크립트가 EC2 인스턴스에 미리 존재하거나, 지금 전송해서 실행하는 방식이 있습니다.
# 여기서는 원격에서 직접 명령어를 실행하는 방식을 사용합니다.
# 실제 운영 환경에서는 원격 스크립트를 먼저 전송 후 실행하는 것이 더 좋습니다.

ssh -i "$PEM_KEY_PATH" "$EC2_USER@$EC2_HOST" "bash -s" < ./deploy-remote.sh "$JAR_NAME" "$REMOTE_APP_DIR"
if [ $? -ne 0 ]; then
    echo "EC2 인스턴스에서 배포 스크립트 실행 실패. 스크립트를 종료합니다."
    exit 1
fi
echo "EC2 인스턴스 배포 완료."

echo "--- 배포 프로세스 완료 ---"