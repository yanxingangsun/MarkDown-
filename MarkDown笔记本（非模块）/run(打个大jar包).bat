rem ����Ҫ������buildĿ¼�µı����ļ�
del /s/q lib dist\MarkDown�ʼǱ�.jar
rd /S /q lib 

rem dir /s /b src\*.java > sources.txt & javac -encoding UTF-8 --module-path ../../openjfx12.0.1��/win64/javafx-sdk-12.0.1/lib --add-modules=javafx.controls,javafx.fxml -d classes @sources.txt & del sources.txt

mkdir lib
mkdir dist
cd lib

jar xf ..\..\..\openjfx12.0.1��\win64\javafx-sdk-12.0.1\lib\javafx.base.jar
jar xf ..\..\..\openjfx12.0.1��\win64\javafx-sdk-12.0.1\lib\javafx-swt.jar
jar xf ..\..\..\openjfx12.0.1��\win64\javafx-sdk-12.0.1\lib\javafx.base.jar
jar xf ..\..\..\openjfx12.0.1��\win64\javafx-sdk-12.0.1\lib\javafx.controls.jar
jar xf ..\..\..\openjfx12.0.1��\win64\javafx-sdk-12.0.1\lib\javafx.fxml.jar
jar xf ..\..\..\openjfx12.0.1��\win64\javafx-sdk-12.0.1\lib\javafx.graphics.jar
jar xf ..\..\..\openjfx12.0.1��\win64\javafx-sdk-12.0.1\lib\javafx.media.jar 
jar xf ..\..\..\openjfx12.0.1��\win64\javafx-sdk-12.0.1\lib\javafx.swing.jar
jar xf ..\..\..\openjfx12.0.1��\win64\javafx-sdk-12.0.1\lib\javafx.web.jar
copy ..\..\..\openjfx12.0.1��\win64\javafx-sdk-12.0.1\bin\*.dll . /Y
rem xcopy /S ..\build\classes .
del META-INF\MANIFEST.MF
del module-info.class 
del .netbeans*

cd .. 
jar --create --file=dist/MarkDown�ʼǱ�.jar --main-class=Java��װ.���� -C lib . 
java -cp build\classes;dist\MarkDown�ʼǱ�.jar Java��װ.����








