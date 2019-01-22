import java.io.*;
import java.nio.ByteBuffer;
import java.nio.*;

public class Save{
	String nameOfProfile;
	String nameOfSave;
	public boolean createSave(String Name){
		try{
			f = new File( System.getProperty("user.dir")+fileSeparator+"saves"+fileSeparator+nameOfProfile+fileSeparator+Name+".sav");
			if(f.exists()){
				return false;
			}
			f.createNewFile();
			nameOfSave = Name;
//=================================defualt values==========================================
			
//=========================================================================================
			save();
			return true;
		}catch(Exception e){}
		return false;
	}
	public void load(Profile p, String Name){
		nameOfProfile=p.nameOfProfile;
		nameOfSave = Name;
		File f = new File( System.getProperty("user.dir")+fileSeparator+"saves"+fileSeparator+nameOfProfile+fileSeparator+Name+".sav");
		InputStream inStream = null;
		BufferedInputStream bis = null;
		try {
			// open input stream test.txt for reading purpose.
			inStream = new FileInputStream(f);
			// input stream is converted to buffered input stream
			bis = new BufferedInputStream(inStream);			
			// read until a single byte is available
//===========================load values=======================================
			
//==================================================================			
		}
		catch(Exception e) {
			// if any I/O error occurs
			System.out.printf("\nload exception\n");
			e.printStackTrace();
		}
		finally {try{
			// releases any system resources associated with the stream
			if(inStream!=null) inStream.close();
			if(bis!=null) bis.close();
			}catch(Exception e){}
		}
	}
	public void save(){
		File f = new File( System.getProperty("user.dir")+fileSeparator+"saves"+fileSeparator+nameOfProfile+fileSeparator+nameOfSave+".sav");
		FileOutputStream outStream = null;
		BufferedOutputStream bos = null;
		try{
			outStream = new FileOutputStream(f);
			bos = new BufferedOutputStream(outStream);
//=================================save values====================================
			
//=====================================================================
			bos.close();
		}
		catch(Exception e) {
			// if any I/O error occurs
			System.out.printf("\nsave exception\n");
			e.printStackTrace();
		}
		finally{try{
			if(outStream!=null) outStream.close();
			if(bos!=null) bos.close();
			}catch(Exception e){}
		}
	}
}