## 简介
`sherpa-ncnn-jin` 是从 [SherpaNcnn.kt](../android/SherpaNcnn/app/src/main/java/com/k2fsa/sherpa/ncnn/SherpaNcnn.kt) 改编而来，并根据 Java 的使用习惯进行了部分微调:
- 采用 Apache Maven 来管理项目
- 核心类 SherpaNcnn 按 Java 使用习惯，实现 `java.io.Closable 接口`，可使用 `try (resource) {}` 方式使用

## 工具链
构建这个库，您需要用到 JDK8 或以上版本, Apache Maven.
### 安装工具链
Debian
```bash
sudo apt-get -y update
sudo apt-get -y install git default-jdk maven
```
Windows

- 访问 https://learn.microsoft.com/zh-cn/windows/package-manager/winget/ 并按照页面内容安装`winget`命令行工具
- 或直接在 [微软应用商店](https://www.microsoft.com/p/app-installer/9nblggh4nns1#activetab=pivot:overviewtab) 进行安装 `winget`
- 然后打开 Windows Terminal 或 Command Prompt 执行以下命令
```bash
winget install "Java SE Development Kit 17"
winget install "Git.Git"
winget install "Git LFS"

mkdir %USERPROFILE%\tool-chains
cd /d %USERPROFILE%\tool-chains
curl -o .\apache-maven-3.9.6-bin.zip https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip
tar zxf apache-maven-3.9.6-bin.zip

set PATH="%ProgramFiles%\Java\jdk-17\bin;%ProgramFiles%\Git\bin;%ProgramFiles%\Git LFS;%USERPROFILE%\tool-chains\apache-maven-3.9.6\bin;%PATH%"
```

## 构建和安装
```bash
# linux
cd ~
# windows
cd /d %USERPROFILE%

git clone https://github.com/seth-yang/sherpa-ncnn.git
cd sherpa-ncnn/java-api
mvn install
```