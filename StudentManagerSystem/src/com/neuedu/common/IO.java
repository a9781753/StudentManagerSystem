package com.neuedu.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.neuedu.pojo.Student;
import com.neuedu.service.impl.StudentServiceimpl;

public class IO {
	 Map<Integer,Student> students = StudentServiceimpl.getstudentServiceimpl().getstudents();
	public void xulie() {
		
		OutputStream os = null;
		ObjectOutputStream oos = null;
		
		try {
			os = new FileOutputStream("D:\\dongruanxuexi\\students.txt");
			oos = new ObjectOutputStream(os);
			oos.writeObject(students);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(oos!=null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(os!=null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	

	}
	//∑¥–Ú¡–ªØ
		public void fanxulie() {
			InputStream is = null;
			ObjectInputStream ois = null;
			
			try {
				is = new FileInputStream("D:\\dongruanxuexi\\students.txt");
				ois = new ObjectInputStream(is);
				Object o = ois.readObject();
				if(o instanceof Map ) {
					 Map<Integer,Student> s = (Map<Integer,Student>)o;
					students = s;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(ois!=null) {
					
						try {
							ois.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
				}
				if(is!=null) {
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		

		}
}
