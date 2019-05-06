/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

module 笔记本 { 
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web; 
    
    requires jdk.jsobject;

    opens Java包装;
    opens Java包装.界面;
    opens pages;
    opens pages.js;
    opens pages.css;
    opens pages.fonts;
    opens pages.images; 
    
    exports Java包装;
    exports Java包装.界面;
}
