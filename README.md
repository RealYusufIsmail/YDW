[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/RealYusufIsmail/YDW)

# YDW
YDW (Yusuf's Discord Wrapper) My own Discord Wrapper inspired by others such as JDA and Javacord

Note: I have used some code from JDA so all credit goes for them.

These include:
<br>
Inspiration for the websocket and CloseCode.
<br>
Inspiration for event adapter. Handle event system was built alone.
<br>
Inspiration for the gateway intent.
<br>
some other minor methods such as gateway ping.
<br>
The use of optionals was inspired by JavaCord.
<br>
If i have missed on any code that I have used from JDA, please let me know.

This project use java 17.

This is for learning purposes.

To use this project you need to add this to your build.gradle file:

```java
repositories {
    mavenCentral()
}

dependencies {
    implementation group: 'io.github.realyusufismail', name: 'YDW', version: '0.0.1'
}
```

