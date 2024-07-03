[English Version](README_en.md)
## 简介
这个模块提供了一套调用 [sherpa-ncnn-jni](../java-api/README.md) 的示例代码。

## 工具链和前置条件
要运行这些示例代码, 您需要 JDK8 或以上版本，您还需要 [Apache Maven](https://maven.apache.org/)。

### 安装工具链
#### Debian
```bash
sudo apt-get -y update
sudo apt-get -y install git default-jdk maven
```

#### Windows
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
### 构建和安装依赖的包
这个模块的例子依赖了其他包，来提供一些基础功能。
```bash
# linux
mkdir -p ~/projects
cd ~/projects

# windows
mkdir %USERPROFILE%\projects
cd /d %USERPROFILE%\projects

# both
git clone -b 3.1.4 https://github.com/seth-yang/dreamwork-base.git
cd dreamwork-base
mvn install

cd ..
git clone -b 1.1.1 https://github.com/seth-yang/application-bootloader.git
cd application-bootloader
mvn install
```

### 动态链接库
#### 本地编译动态链接库
```bash
# linux
cd ~/projects

# windows
cd /d %USERPROFILE%\projects

# both
git clone https://github.com/seth-yang/sherpa-ncnn.git
cd sherpa-ncnn
```
访问 https://k2-fsa.github.io/sherpa/ncnn/index.html 并根据其内容进行相应平台的编译。

#### 下载已经编译好的库
```bash
# linux
cd ~/projects

# windows
cd /d %USERPROFILE%\projects

# both
git clone https://github.com/seth-yang/sherpa-ncnn-java.git
cd sherpa-ncnn-java
mvn package
```
在项目的 `modules/sherpa-ncnn-{platform}-{arch}`目录下将生成包裹了本地动态链接库的.jar文件，您可以在后面的例子中直接使用

### 下载预训练模型

## 构建并运行示例代码