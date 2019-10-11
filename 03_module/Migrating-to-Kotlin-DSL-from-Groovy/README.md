# Gradle migrating Groovy → Kotlin

Gradle 공식 홈페이지에 나와 있는 마이그레이션 방법
(세부 내용이 길기 때문에 나중에 좀 더 깊게 파볼때 보는걸 추천합니다.)

[링크](https://slack-redir.net/link?url=https%3A%2F%2Fguides.gradle.org%2Fmigrating-build-logic-from-groovy-to-kotlin%2F)

## STEP1: 마이그레이션 사전준비

- 5.0 버전부터 지원 [링크](https://docs.gradle.org/5.0/release-notes.html)

  Android Gradle Plugin 버전 확인하는 곳 [링크](https://developer.android.com/studio/releases/gradle-plugin)
  
  - Android Studio에서 Gradle, Gralde Plugin 버전 설정 방법
  
    방법1: 'AndroidStuido에서 File → Project Structure → Project'
    
    방법2: gradle-wrapper.properties 파일에서 `distributionUrl`변수로 Gradle 버전 설정, Project build.gradle 파일에서 `dependencies {}` 블럭에서 Android Gradle Plugin 버전 설정

- 자바 8 이상 사용해야 합니다.

## STEP2: Groovy Script를 Kotlin 문법으로 바꾸기 쉽게 변경

Kotlin DSL은 Groovy에 비해서 엄격한 문법을 갖기 때문에 '.kts' 파일로 변경전에 Kotlin식으로 변경하는 것을 권장합니다.

  1. Groovy는 `''`, `""` 둘다 가능하지만, Kotlin에서는 String 표현시 `""`만 가능하므로 `''` → `""`로 변경해야 합니다.
  2. Groovy는 함수의 파라미터를 꼭 `()`로 묶을 필요가 없지만 Kotlin 함수는 파라미터를 모두 `()`로 묶어야 합니다.
  3. Groovy의 값 할당은 `applicationId "ado.sabgil.studyproject"`나 `applicationId = "ado.sabgil.studyproject"` 둘다 가능하지만, Kotlin에서는 `=`를 꼭 써야 하므로 변경해야 합니다.

## STEP3: Script 파일 확장자를 kts로 변경하기

- .gradle → .gradle.kts 로 변경합니다.
- settings.gradle 파일도 잊지 않고 해줍니다.

## STEP4: Kotling DSL 문법에 맞게 변경하기
  
  STEP2에서 기본적인 것만 변경했기 때문에 세부적인 것들을 변경해줘야 합니다.
  
- module build.gradle 파일에서 apply plugin: 부분
  ```kotlin 
  // AS IS
  apply plugin: 'com.android.application'

  apply plugin: 'kotlin-android'

  apply plugin: 'kotlin-android-extensions'

  apply plugin: 'kotlin-kapt'

  // TO BE
  plugins {
      id("com.android.application")
      id("kotlin-android")
      id("kotlin-android-extensions")
      id("kotlin-kapt")
  } 
  ```
  
- module build.gradle 파일에서 `implementation fileTree...`부분
  ```kotlin 
  // AS IS
  implementation fileTree(include: ['*.jar'], dir: 'libs')
  
  // TO BE
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
  
  ```
  
- module build.gradle 파일에서 `buildTypes { release {} }`블럭
  ```kotlin
  // AS IS
  buildTypes {
      release {
          minifyEnabled false
          proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
      }
  }
  
  // TO BE
  buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
      }
  }
  ```
- project build.gradle 파일에서 task 부분
  ```kotlin
  // AS IS
  task clean(type: Delete) {
    delete rootProject.buildDir
  }
  
  // TO BE
  tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
  }
  ```
참고: [Migrating Android build scripts from Groovy to Kotlin DSL](https://proandroiddev.com/migrating-android-build-scripts-from-groovy-to-kotlin-dsl-f8db79dd6737)


## STEP5: buildSrc 만들고 디펜던시 관리하기
- 프로젝트 최상단 위치에 buildSrc 폴더 생성
- build.gradle.kts, Dependencies.kt 파일 생성
- build.gradle.kts 파일에 아래 코드 추가
  ```kotlin
  repositories {
      jcenter()
  }

  plugins {
      `kotlin-dsl`
  }
  ```
- Dependencies.kt에 버전 정보 넣어서 다른 kts에서 사용
  - 반드시 src/main/java 폴더에 생성

참고: [Write your Gradle scripts using Kotlin Dsl](https://ayusch.com/writing-your-gradle-scripts-in-kotlin-with-kotlin-dsl/)
