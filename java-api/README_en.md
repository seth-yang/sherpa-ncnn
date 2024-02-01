## Introduction
`sherpa-ncnn-jin` is adapted from [SherpaNcnn.kt](../android/SherpaNcnn/app/src/main/java/com/k2fsa/sherpa/ncnn/SherpaNcnn.kt) and based on Java Some fine-tuning has been made to usage habits:
- Use Apache Maven to manage projects
- The core class SherpaNcnn implements the `java.io.Closable` interface according to Java usage habits and can be used in the `try(resource){}` way.

## Toolchain
To build this library, you need JDK 8 or above, Apache Maven.
### Install tool chain
Debian
```bash
sudo apt-get -y update
sudo apt-get -y install git default-jdk maven
```
Windows

- Visit https://learn.microsoft.com/zh-cn/windows/package-manager/winget/ and follow the content to install the `winget` command line tool
- Or install `winget` directly in [Microsoft App Store](https://www.microsoft.com/p/app-installer/9nblggh4nns1#activetab=pivot:overviewtab)
- Then open Windows Terminal or Command Prompt and execute the following commands
```bash
winget install "Java SE Development Kit 17"
winget install "Git.Git"
winget install "Git LFS"

mkdir %USERPROFILE%\tool-chains
cd /d %USERPROFILE%\tool-chains
curl -o .\apache-maven-3.9.6-bin.zip https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip
tar zxf apache-maven-3.9.6-bin.zip

set PATH="%ProgramFiles%\Java\jdk-17\bin;%ProgramFiles%\Git\bin;%ProgramFiles%\Git LFS;%USERPROFILE%\tool-chains\apache-maven-3.9.6\bin;% PATH%"
```

## Build and install
```bash
#linux
cd ~
# windows
cd /d %USERPROFILE%

git clone https://github.com/seth-yang/sherpa-ncnn.git
cd sherpa-ncnn/java-api
mvn install
```