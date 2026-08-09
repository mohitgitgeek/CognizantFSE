# Exercises 34–36

## 34. Java modules

`com.utils/src/module-info.java`: `module com.utils { exports com.utils; }`

`com.utils/src/com/utils/TextUtil.java`: `package com.utils; public class TextUtil { public static String greet(String n) { return "Hello " + n; } }`

`com.greetings/src/module-info.java`: `module com.greetings { requires com.utils; }`
`com.greetings/src/com/greetings/Main.java`: `package com.greetings; import com.utils.TextUtil; public class Main { public static void main(String[] a) { System.out.println(TextUtil.greet("Ada")); } }`

Compile: `javac -d mods --module-source-path "*/src" -m com.utils,com.greetings`
Run: `java --module-path mods -m com.greetings/com.greetings.Main`

## 35. TCP client/server chat

```java
// ChatServer.java
import java.io.*; import java.net.*;
class ChatServer { public static void main(String[] x) throws Exception { try (ServerSocket ss=new ServerSocket(5000); Socket s=ss.accept(); var in=new BufferedReader(new InputStreamReader(s.getInputStream())); var out=new PrintWriter(s.getOutputStream(),true)) { String line; while((line=in.readLine())!=null) { System.out.println("Client: "+line); out.println("Server received: "+line); } } } }
// ChatClient.java
import java.io.*; import java.net.*;
class ChatClient { public static void main(String[] x) throws Exception { try(Socket s=new Socket("localhost",5000); var in=new BufferedReader(new InputStreamReader(s.getInputStream())); var out=new PrintWriter(s.getOutputStream(),true); var keyboard=new BufferedReader(new InputStreamReader(System.in))) { String line; while((line=keyboard.readLine())!=null) { out.println(line); System.out.println(in.readLine()); } } } }
```

Compile both, run `java ChatServer` in one terminal, then `java ChatClient` in another.

## 36. HTTP Client API (Java 11+)

```java
import java.net.URI; import java.net.http.*;
class HttpExample { public static void main(String[] a) throws Exception { var client=HttpClient.newHttpClient(); var request=HttpRequest.newBuilder(URI.create("https://api.github.com/users/octocat")).build(); var response=client.send(request,HttpResponse.BodyHandlers.ofString()); System.out.println(response.statusCode()); System.out.println(response.body()); } }
```
