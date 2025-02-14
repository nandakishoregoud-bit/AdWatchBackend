FROM openjdk:17-jdk-slim 
WORKDIR /app 
COPY . . 
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64 
ENV PATH="C:\Program Files\Microsoft\jdk-17.0.13.11-hotspot/bin:C:\Python313\Scripts\;C:\Python313\;C:\Program Files\Microsoft\jdk-17.0.13.11-hotspot\bin;C:\Program Files\Common Files\Oracle\Java\javapath;C:\Program Files (x86)\Common Files\Oracle\Java\javapath;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\WINDOWS\System32\OpenSSH\;C:\Program Files (x86)\dotnet\;C:\Program Files\dotnet\;F:\apache-maven-3.8.6\bin;C:\Program Files\Git\cmd;D:\Angular\mongosh-1.6.1-win32-x64\bin;C:\Program Files\MongoDB\Server\6.0\bin;C:\ProgramData\chocolatey\bin;C:\Users\DELL\AppData\Local\Android\Sdk\platforms;C:\Users\DELL\AppData\Local\Android\Sdk\platform-tools;D:\Program Files\node.js;D:\Program Files\node.js\;D:\Users\DELL\gradle-8.10.2\bin;D:\Users\DELL\scrcpy\scrcpy-win64-v3.1;C:\Users\DELL\AppData\Local\Android\Sdk\emulator;C:\Users\DELL\AppData\Local\Android\Sdk\cmdline-tools\latest\bin;C:\Users\DELL\AppData\Local\Android\Sdk\build-tools\35.0.0;C:\Program Files\MySQL\MySQL Shell 8.0\bin\;C:\Users\DELL\AppData\Local\Microsoft\WindowsApps;C:\Users\DELL\AppData\Local\Programs\Microsoft VS Code\bin;C:\Users\DELL\AppData\Local\Programs\MiKTeX\miktex\bin\x64\;C:\Users\DELL\AppData\Roaming\npm" 
RUN chmod +x mvnw 
RUN ./mvnw clean install 
EXPOSE 8080 
CMD ["java", "-jar", "target/your-app.jar"] 
