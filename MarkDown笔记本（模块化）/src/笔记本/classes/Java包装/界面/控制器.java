package Java包装.界面;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
 

import Java包装.编码.编码检测;
import Java包装.编码.编码枚举;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;

/**
 * 请求 
 * @author xuchg
 */
public class 控制器 {
	
	private static Boolean editable = false;
	
	private static Boolean openLangeFile = false;

	/**
	 * 打开选择文件窗口
	 */
	public void toOpenFile(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("打开文件");
		if(主窗口.oldFile != null){
			fileChooser.setInitialDirectory(主窗口.oldFile.getParentFile());
		}
		File file = fileChooser.showOpenDialog(主窗口.stage);
		if(file != null){
			openFile(file);
		}
	}
	
	/**
	 * 保存前的检验
	 * @throws IOException 
	 */
	public void toSaveFile() throws IOException{
		String value = getValue();
		if(主窗口.newFile && !value.isBlank()){//新文件且编辑过
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("保存文件");
			FileChooser.ExtensionFilter mdFilter = new FileChooser.ExtensionFilter("README files (*.md)", "*.md");
			FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("所有文件", "*.*");
			fileChooser.getExtensionFilters().add(mdFilter);
	        fileChooser.getExtensionFilters().add(txtFilter);
	        fileChooser.getExtensionFilters().add(allFilter);
			File file = fileChooser.showSaveDialog(主窗口.stage);
			if(file != null){
				if(file.exists()){
					saveFile(value,file);
				}else{
					file.createNewFile();
					saveFile(value,file);
				}
			}
		}else if(!主窗口.newFile && !value.equals(主窗口.oldValue)){
			saveFile(value,主窗口.oldFile);
		}else if(!主窗口.newFile && !主窗口.save){
			saveFile(value,主窗口.oldFile);
		}
	}
	
	/**
	 * 保存文件
	 * @param create true表示新创建的文件，false表示旧文件保存
	 */
	public void saveFile(String value,File file){
		try {
			writeFile(file, value);
			主窗口.oldFile = file;
			主窗口.newFile = false;
			主窗口.fileName = file.getName();
			主窗口.oldValue = value;
			主窗口.save = true;
			主窗口.stage.setTitle(file.getName() + " - MDNotePad");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 打开文件
	 * @param file
	 */
	public void openFile(File file){
		try {
			//预测文件编码
			主窗口.charset = 编码检测.检测文件编码(file.getAbsolutePath()); 
			主窗口.jsObject.call("changeCodeDis", 主窗口.charset.java名称);
			
			long fileLength = file.length();
			//文件过大提醒
			if(fileLength > 1024000){
				openLangeFile = false;
				Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"是否打开该大文本？");
				confirmation.setHeaderText("");
				confirmation.showAndWait().ifPresent(response ->{
					if(response == ButtonType.OK){
						openLangeFile = true;
					}
				});
				if(!openLangeFile){
					return ;
				}
			}
			
			String info = readFile(file);
			主窗口.oldValue = info;
			主窗口.fileName = file.getName();
			主窗口.newFile = false;
			主窗口.oldFile = file;
			主窗口.save = true;
			主窗口.stage.setTitle(file.getName() + " - MDNotePad");
			主窗口.jsObject.call("initText", info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 切换编码，打开文件，无需判断文件大小
	 * @param file
	 */
	public void openFile(File file,String encode){
		try {
			//预测文件编码
			主窗口.charset = 编码枚举.valueOf(encode);
			
			String info = readFile(file);
			主窗口.oldValue = info;
			主窗口.fileName = file.getName();
			主窗口.newFile = false;
			主窗口.oldFile = file;
			主窗口.save = true;
			主窗口.stage.setTitle(file.getName() + " - MDNotePad");
			主窗口.jsObject.call("initText", info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建新文件
	 */
	public void createNewFile(){
		Boolean result = checkEdit("文件未保存，是否继续？");
		if(result){
			initFile();
		}
	}
	
	/**
	 * 检查是否编辑
	 */
	public String checkIfEdit(){
		String result = "n";
		String value = getValue();
		if(主窗口.newFile){
			if(!value.isBlank()){
				主窗口.save = false;
				主窗口.stage.setTitle("* 未保存 - MDNotePad");
				result = "y";
			}
		}else{
			if(!value.equals(主窗口.oldValue)){
				主窗口.save = false;
				主窗口.stage.setTitle("* " + 主窗口.stage.getTitle());
				result = "y";
			}
		}
		return result;
	}
	
	
	/**
	 * 获取当前编辑器的值
	 * @return
	 */
	private String getValue(){
		String value = (String) 主窗口.jsObject.call("getValue");
		return value;
	}
	
	/**
	 * 初始化创建文件
	 */
	private void initFile(){
		主窗口.fileName = "";
		主窗口.newFile = true;
		主窗口.charset = 编码枚举.GB2312;
		主窗口.oldValue = "";
		主窗口.save = true;
		主窗口.stage.setTitle("未保存 - MDNotePad");
		主窗口.jsObject.call("initText", "");
	}
	
	/**
	 * 修改编码
	 * @param encode
	 */
	public void changeEncode(String encode){
		主窗口.charset = 编码枚举.valueOf(encode);
		if(!主窗口.newFile){//已经打开的文件，修改编码则需要重新打开文件
			openFile(主窗口.oldFile,encode);
			主窗口.save = false;
		}
	}
	
	/**
	 * 导出成html
	 * @throws IOException 
	 */
	public void downHtml() throws IOException{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("保存成HTML");
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
		FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("所有文件", "*.*");
		fileChooser.getExtensionFilters().add(filter);
		fileChooser.getExtensionFilters().add(allFilter);
		File file = fileChooser.showSaveDialog(主窗口.stage);
		if(file != null){
			String html = 主窗口.jsObject.call("getHtml", "").toString();
			String prefix = "<!DOCTYPE html><html><body><head> <meta charset='utf-8'/><title>MDNotePad</title></head>";
			String end = "</body></html>";
			html = prefix + html + end;
			Charset charset =  Charset.forName(编码枚举.UTF8.java名称);
			if(file.exists()){
				writeFile(file, html,charset);
			}else{
				file.createNewFile();
				writeFile(file, html,charset);
			}
		}
	}
	
	/**
	 * 退出
	 */
	public void exit(){
		Boolean exec = checkEdit("未保存，是否退出？");
		if(exec){
			主窗口.stage.close();
		}
	}
	
	/**
     * 打开github
     * @throws URISyntaxException
     * @throws IOException
     */
    public void openGithub() throws URISyntaxException, IOException{
    	String url = "https://github.com/Xchunguang/MDNotePad";
    	System.setProperty("java.awt.headless", "false");
    	URI address = new URI(url);
    	Desktop d = Desktop.getDesktop();
    	d.browse(address);
    }
	
	/**
	 * 检查当前文件是否编辑过，并请用户判断是否继续
	 * 新建文件，文本内容不为空则属编辑态
	 * 打开文件，旧内容与新内容不同则属编辑态
	 * @return
	 */
	public Boolean checkEdit(String info){
		editable = false;
		String value = getValue();
		if((主窗口.newFile && !value.isBlank()) || (!主窗口.newFile)&&!value.equals(主窗口.oldValue)){
			Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,info);
			confirmation.setHeaderText("");
			confirmation.showAndWait().ifPresent(response ->{
				if(response == ButtonType.OK){
					editable = true;
				}
			});
		}else{
			editable = true;
		}
		return editable;
	}
	
    	/**
	 * 读文件
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public String readFile(File file) throws IOException{
		StringBuilder result = new StringBuilder("");
		Charset charset = Charset.forName(主窗口.charset.java名称);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),charset));
		String line = null;
		while ((line = reader.readLine()) != null) {
			if(!result.toString().isBlank()){
				result.append("\n");
			}
			result.append(line);
		}
		reader.close();
		return result.toString();
	}
	
	/**
	 * 写文件
	 * @param file
	 * @param lines
	 * @throws IOException
	 */
	public void writeFile(File file,String lines) throws IOException{
		Charset charset = Charset.forName(主窗口.charset.java名称);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),charset));
		String[] lineArr = lines.split("\n");
		for(int i=0;i<lineArr.length;i++){
			writer.write(lineArr[i]);
			if(i < lineArr.length - 1){
				writer.newLine();
			}
		}
		writer.flush();
		writer.close();
	}
	
	/**
	 * @param file
	 * @param lines
	 * @param charset
	 * @throws IOException
	 */
	public void writeFile(File file,String lines,Charset charset) throws IOException{
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),charset));
		String[] lineArr = lines.split("\n");
		for(int i=0;i<lineArr.length;i++){
			writer.write(lineArr[i]);
			if(i < lineArr.length - 1){
				writer.newLine();
			}
		}
		writer.flush();
		writer.close();
	}
}
