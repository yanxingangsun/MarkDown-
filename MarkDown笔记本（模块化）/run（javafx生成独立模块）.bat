rem ��cmd������bat�ļ���Ҫ������ʾ���ģ���bat�ļ������ʽ�豣��Ϊgbk(��gb2312,gbk����gb2312)
del /s/q links 
rd /S /q links 
rem javac -p ../javafx11lib/win64/javafx11mods -d mods/��Ϸ src/��Ϸ/module-info.java src/��Ϸ/src/*.java src/��Ϸ/src/����/*.java src/��Ϸ/src/����/*.java src/��Ϸ/src/ģ��/*.java
rem copy src\��Ϸ\src\����\����.fxml mods\��Ϸ\src\���� /y  
jlink -p ..\..\openjfx12.0.1��\win64\javafx-jmods-12.0.1;build\modules --add-modules=�ʼǱ� --output links 
rem links\bin\java -p build\modules -m �ʼǱ�/Java��װ.���� --output links   
java -p ..\..\openjfx12.0.1��\win64\javafx-sdk-12.0.1\lib;build\modules -m �ʼǱ�/Java��װ.����  