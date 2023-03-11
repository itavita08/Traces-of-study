## Dart
---
### Named Argument
   
```Dart
String sayHello(String name, int age, String country){
  return "Hello $name, you are $age, and you come from $country";  
}

void main() {
  print(sayHello('y',19,'korea'));
}
```
``sayHello``를 호출하여 사용하려면 파라미터르 순서대로 넣어줘야한다.   
하지만 사용자 입장에서는 ``'y'``가 name인지 age인지 모를 것이다.   
따라서 ``Named Argument``를 아래와 같이 사용해 준다.   
```Dart
String sayHello({String name, int age, String country}){
  return "Hello $name, you are $age, and you come from $country";  
}

void main() {
  print(sayHello(
    name: 'y',
    age: 19,
    country: 'korea'));
}
```
하지만 파라미터가 null인 경우가 있어 null safety로 인해 에러가 발생한다.   
이를 해결하기 위해선 두가지 방법이 있다.   
### 1. Default Value 사용
```Dart
String sayHello({String name = 'anon', int age = 19, String country = 'wakanda'}){
  return "Hello $name, you are $age, and you come from $country";  
}

void main() {
  print(sayHello(name: 'y'));
}
```
``Default Value``를 사용하면 파라미터가 null이면 default value가 출력된다.
### 2. Required Modifier 사용
```Dart
String sayHello({required String name, required int age, required String country}){
  return "Hello $name, you are $age, and you come from $country";  
}

void main() {
  print(sayHello(
    name: 'y',
    age: 19,
    country: 'korea'));
}
```
파라미터 타입 앞에 ``required``를 붙여주면 sayHello함수를 사용할 때 파라미터를 입력하라고 알려준다.
## 3. null safety
```Dart
String sayHello({required String name, int? age, required String country}){
  return "Hello $name, you are $age, and you come from $country";  
}

void main() {
  print(sayHello(
    name: 'y',
    country: 'korea'));
}
```
타입뒤에 ?를 붙여주면 값이 있을 수도 있고 null일 수도 있다는것을 명시해주는 것이라서 출력하게 되면    
``Hello y, you are null, and you come from korea``라고 출력된다.


