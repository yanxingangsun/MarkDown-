rem 在cmd下运行bat文件，要正常显示中文，则bat文件编码格式需保存为gbk(或gb2312,gbk包含gb2312)
del /s/q links 
rd /S /q links 
rem javac -p ../javafx11lib/win64/javafx11mods -d mods/游戏 src/游戏/module-info.java src/游戏/src/*.java src/游戏/src/界面/*.java src/游戏/src/解题/*.java src/游戏/src/模型/*.java
rem copy src\游戏\src\界面\界面.fxml mods\游戏\src\界面 /y  
jlink -p ..\..\openjfx12.0.1库\win64\javafx-jmods-12.0.1;build\modules --add-modules=笔记本 --output links 
rem links\bin\java -p build\modules -m 笔记本/Java包装.启动 --output links   
java -p ..\..\openjfx12.0.1库\win64\javafx-sdk-12.0.1\lib;build\modules -m 笔记本/Java包装.启动  