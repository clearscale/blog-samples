# Legacy Java 8 Inventory Management System

This is a sample Java 8 application designed to demonstrate Amazon Q Developer's transformation capabilities.

## Project Structure

Create the following directory structure:

```
inventory-management-system/
├── pom.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── clearscale/
│   │               └── legacy/
│   │                   └── inventory/
│   │                       ├── InventoryApplication.java
│   │                       ├── model/
│   │                       │   └── Product.java
│   │                       ├── service/
│   │                       │   └── InventoryService.java
│   │                       └── util/
│   │                           └── ReportGenerator.java
│   └── test/
│       └── java/
│           └── com/
│               └── clearscale/
│                   └── legacy/
│                       └── inventory/
│                           └── service/
│                               └── InventoryServiceTest.java
```

## Setup Instructions

1. **Create the project directory:**
   ```bash
   mkdir inventory-management-system
   cd inventory-management-system
   ```

2. **Copy the pom.xml file** to the root directory

3. **Create the directory structure:**
   ```bash
   mkdir -p src/main/java/com/clearscale/legacy/inventory/model
   mkdir -p src/main/java/com/clearscale/legacy/inventory/service  
   mkdir -p src/main/java/com/clearscale/legacy/inventory/util
   mkdir -p src/test/java/com/clearscale/legacy/inventory/service
   ```

4. **Copy the Java files** to their respective directories:
   - `Product.java` → `src/main/java/com/clearscale/legacy/inventory/model/`
   - `InventoryService.java` → `src/main/java/com/clearscale/legacy/inventory/service/`
   - `ReportGenerator.java` → `src/main/java/com/clearscale/legacy/inventory/util/`
   - `InventoryApplication.java` → `src/main/java/com/clearscale/legacy/inventory/`
   - `InventoryServiceTest.java` → `src/test/java/com/clearscale/legacy/inventory/service/`

## Building and Running

1. **Compile the project:**
   ```bash
   mvn clean compile
   ```

2. **Run tests:**
   ```bash
   mvn test
   ```

3. **Run the application:**
   ```bash
   mvn exec:java -Dexec.mainClass="com.clearscale.legacy.inventory.InventoryApplication"
   ```

## Legacy Patterns for Q Developer Transformation

This application includes multiple Java 8 patterns that are perfect for demonstrating Q Developer's transformation capabilities:

### 1. String.format() Usage
- Multiple instances in `ReportGenerator.java`
- Multiple instances in `InventoryService.java`
- Can be modernized to `.formatted()` method

### 2. Legacy Dependencies
- Spring Framework 4.3.30 (can be upgraded to Spring Boot)
- Hibernate 4.3.11 (can be upgraded to newer version)
- JUnit 4 (can be upgraded to JUnit 5)
- Jackson 2.8.11 (has security vulnerabilities)

### 3. Old-Style Code Patterns
- Anonymous Comparator class (can become lambda)
- Verbose null checking (can use Optional patterns)
- Legacy Date usage (can become LocalDateTime)
- Traditional for-loops (can use modern streams)

### 4. Maven Configuration
- Java 8 source/target
- Old Maven compiler plugin version
- Legacy dependency versions

## Expected Q Developer Transformations

When you run this through Amazon Q Developer transformation to Java 21, expect to see:

1. **POM Updates:**
   - Java version updated to 21
   - Dependencies updated to Java 21-compatible versions
   - Maven compiler plugin updated

2. **Code Modernization:**
   - `String.format()` → `.formatted()`
   - Anonymous classes → Lambda expressions
   - Traditional loops → Stream operations
   - Legacy Date → Modern time API

3. **Performance Improvements:**
   - Modern Java idioms
   - Optimized stream operations
   - Better null handling patterns

This provides a realistic enterprise codebase example for your Amazon Q Developer blog demonstration.