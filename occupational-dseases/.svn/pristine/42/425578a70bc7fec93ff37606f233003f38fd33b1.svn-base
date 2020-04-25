package com.as.occupationaldseases.utils;

import java.io.*;

public class FileFactory {

	private String filePath;
	private String fileName;
	private File file;
    private OutputStreamWriter outputStream;
	public FileFactory(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;
	}

	/**
	 * ����Ҫд����ļ�
	 */
	public void createFile() {
		File tempFileDir = new File(this.filePath);
		File tempFile = new File(this.filePath + File.separator + this.fileName);
		if (!tempFileDir.exists()) {
			//System.out.println("��ǰ��Ŀ¼�����ڣ�");
			tempFileDir.mkdirs();
			System.out.println("��ǰ�ļ��д����ɹ���");
			if (!tempFile.exists()) {
				//System.out.println("��ǰ��Ŀ���ļ������ڣ�");
			    try {
					tempFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    System.out.println("Ŀ���ļ������ɹ���");
			}
			this.file = tempFile;
		} else {
			//System.out.println("��ǰ�ĸ�Ŀ¼���ڣ�");
			if (!tempFile.exists()) {
				//System.out.println("��ǰָ�����ļ������ڣ�");
			  	try {
					tempFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			this.file = tempFile;
		}
		
	}
	
	public void  openOutStream(){
		try {
			this.outputStream  = new OutputStreamWriter(new FileOutputStream(this.file));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	/**
	 * �򴴽����ļ�����д���ļ�
	 * @param content
	 */
	public void writeContent(String content){
		try {
			
			this.outputStream.write(content+"\r\n");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeOutStream(){
		
		try {
			this.outputStream.flush();
			this.outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void main(String args[]){
		FileFactory fileFactory = new FileFactory("F:\\", "�����ļ�.txt");
		fileFactory.createFile();
		fileFactory.openOutStream();
		for(int i=0;i<100;i++){
			fileFactory.writeContent(i+"---------content------"+i+"\r\n");
		}
		fileFactory.closeOutStream();
	}

}
