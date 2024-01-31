从 [SherpaNcnn.kt](../android/SherpaNcnn/app/src/main/java/com/k2fsa/sherpa/ncnn/SherpaNcnn.kt) 改编而来，并根据 java 的使用习惯进行了部分微调:
- 采用 Apache Maven 来管理项目
- 核心类 SherpaNcnn 按 Java 使用习惯，实现 `java.io.Closable 接口`，可使用 `try (resource) {}` 方式使用 