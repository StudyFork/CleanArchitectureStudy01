# Navigation Component
* 기존의 코드 단위에서만 관리됐었던 트랜잭션을 시각적으로 표시하고 더 쉽게 구현하도록 도와줍니다.
* 싱글 액티비티로 개발하기를 권장합니다.

### 왜 싱글액티비티인가?
>네비게이션 컴포넌트는 Activity의 역할을 기존과 다르게 바라볼 것을 요구합니다. 
원래 Activity는 화면의 Entry Point 이면서도 Content와 Navigation Method를 들고있는 Owner 였습니다.  
하지만 네비게이션 컴포넌트를 활용하기 위해서는 Entry Point로서의 역할만 보아야 합니다. 
Content와 Navigation Method는 모두 NavHost 라는 Fragment에게 위임합니다. 
그리고 이는 대부분의 화면을 Single Activity로 설계해야 함을 의미합니다.  
<br>
[Navigation Components in Android Jetpack (1) — Introduction](https://medium.com/@maryangmin/navigation-components-in-android-jetpack-1-introduction-e38442f70f)

### 이점
* 프래그먼트 트랜잭션 자동 제어
* 명확한 뒤로가기 & 상위이동
* 간단한 딥링크
* 기본 애니메이션 & 이동
* safeArgs 를 통하여 타입 안정성 제공
* 앱의 플로우를 시각화

### 기본 원칙
* 고정된 시작 지점이 필요
  * 첫 실행 지점에서는 백버튼으로 앱을 종료 합니다.
* stack 으로 관리
  * stack 으로 쌓이게 되고 LIFO 방식 으로 관리 합니다.
* 동일한 up 버튼과 back 버튼 동작
  * 앱바 좌측의 up 버튼과 기기 하단의 back 버튼은 동일하게 동작 합니다.
* 딥링크의 스택
  * 특정 목적지를 딥링크로 이동하거나, 네비게이션으로 이동 할 때 동일한 스택을 가져야 합니다.

