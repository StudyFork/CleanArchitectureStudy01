# Action

## 정의
프래그먼트 간의 이동 위치(목적지)를 정의합니다.

## 구현
### design tab
`in_game` 프래그먼트와 `results_winner` 프래그먼트가 있습니다.  
`in_game` 에서 `results_winner` 로 이동하려고 합니다.

![](img/action-from-in-game-to-results-winner.png)

프래그먼트 우측 테두리 중앙을 드래그하여 원하는 목적지에 끌어다 놓으면 destination 이 정의됩니다.

![](img/action-from-in-game-to-results-winner_2.gif)

생성된 라인을 클릭하면 우측에 해당 action 의 속성들이 나타납니다.

![](img/action-destination-attribute.png){: width="300" height="550"}

```kotlin
NavHostFragment.findNavController(this).navigate(R.id.action_in_game_to_results_winner)
```

### xml code

```xml
<action
    android:id="@+id/action_in_game_to_results_winner"
    app:destination="@id/results_winner" />
```

`android:id` : 해당 action 의 식별 키가 됩니다.  
`app:destination` : navigation graph 에 선언된 이동하려는 fragment 의 id 를 넣어주게 됩니다.

```xml
<action
    android:id="@+id/action_results_winner_to_matchFragment"
    app:popUpTo="@+id/in_game_navigation" />  
```

`app:popUpTo` : 예를 들어 `A -> B -> C` 와 같이 스택이 쌓일 때 `C -> A` 로 바로 가고 싶다면   
                `popUpTo` 속성을 사용하여 중간 스택(`B`) 을 제거하고 바로 이동 할 수 있습니다.




