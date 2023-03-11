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


