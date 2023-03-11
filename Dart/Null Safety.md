## Dart
---
### Null Safety

Null Safety는 null이 설정된 변수의 의도하지 않은 접근으로 인한 런타임 오류를 방지한다.   
예를 들어, 메서드가 정수 타입으로 선언되었지만 null값을 받으면 앱이 런타임 오류를 발생시킨다.   
   
문서에서는 null이 아닌 변수가 null이 아닌 값으로 초기화되지 않았거나 null 할당될 때 플래그를 지정하여 잠재적이 런타임 오류를 편집 시간 분석 오류로 변경하고.  
이를 통해 앱을 배포하기 전에 이러한 오류를 수정할 수 있다고 나와있다.   
   
```Dart
void main(){
  String name = 'YeongJun';
  name = null;
}
```
위의 예제에서는 ``name`` 이라는 변수가 ``String`` 타입으로 선언되어 ``null`` 이 될 수가 없다.   
따라서 실행을 시키면 런타임 에러가 발생한다.   
     
      
```Dart
void main(){
  String? name = 'YeongJun';
  name = null;
  
  name?.isNotEmpty;
}
```
위의 예제처럼 타입뒤에 ?를 넣어 ``null``값이 참조될 수도 있다고 선언해 주면 런타임에러가 뜨지 않는다.   
``name?.isNotEmpty;``를 실행하면서 ``name``이 null이 아닌지를 확인하고 다음 속성을 달라고 요청하면 된다.
