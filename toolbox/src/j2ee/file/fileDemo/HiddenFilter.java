package j2ee.file.fileDemo;

import java.io.File;
import java.io.FileFilter;

public class HiddenFilter implements FileFilter{

	@Override
	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		return !pathname.isHidden();
	}
	
}
