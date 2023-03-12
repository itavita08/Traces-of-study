## Dart
---
### Operator
#### 1. QQ Operator
- `` left ?? right``에서 만약 left가 null이 아니면 left를 그대로 출력하고 null이면 right를 출력한다.   
<br />
   
```Dart
String capitalizeName(String? name) => name.toUpperCase();

void main(){
  capitalizeName('yeongjun');
}
```
위의 코드와 같이 이름을 대문자로 바꿔서 출력하는 함수가 있을때,   
만약 이름대신에 ``null``값을 주고 싶다면 null일지도 모르는 값에 ``toUpperCase()``을 호출할 수가 없어서 에러가 날 것이다.   
이를 해결하기 위해선 아래와 같이 if문을 추가 해주면 된다.
```Dart
String capitalizeName(String name) {
  if (name != null){
    return name.toUpperCase();
  }
  return 'ANON';
}

void main(){
  capitalizeName('yoengjun');
}
```
위의 코드를 더 줄이기 위해서는 QQ Operator를 아래와 같이 사용하면 된다.
```Dart
tring capitalizeName(String? name) => name?.toUpperCase() ?? 'ANON';

void main(){
  capitalizeName('yoengjun');
}
```
<br /> 
   
#### 2. QQ assignment Operator
```Dart
void main(){
  String? name;
  name ??= 'yeongjun';
}
```
``name``이 null이면 ``yeongjun``을 할당하라는 명령어이다.


