javac --module-path "C:\javafx-sdk-22\lib --add-modules javafx.controls App.java
java--module-path C:\vscode\DemoFx\src --add-modules javafx.controls App
{
// Use IntelliSense to learn about possible attributes.
// Hover to view descriptions of existing attributes.
// For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
"version": "0.2.0",
"configurations": [
{
"type": "java",
"name": "App",
"request": "launch",
"mainClass": "App.java",
"projectName": "DemoFx_d8b99602"
},
{
"type": "java",
"name": "Current File",
"request": "launch",
"mainClass": "${file}"
},
{
"type": "java",
"name": "App",
"request": "launch",
"mainClass": "App",
"projectName": "DemoFx_d8b99602",
"vmArgs": "--module-path \"C:/javafx-sdk-22/lib\" --add-modules javafx.controls,javafx.fxml"
}
]
}
