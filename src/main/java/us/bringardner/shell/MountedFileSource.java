package us.bringardner.shell;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import us.bringardner.io.filesource.FileSource;

public class MountedFileSource implements InvocationHandler {

	private String name="myname";
	private FileSource target;
	private List<FileSource> mountPoints = new ArrayList<>();
	
	public MountedFileSource(FileSource target) {
		this.target = target;
	}
	
	public void mount(FileSource dir) {
		if( !mountPoints.contains(dir)) {
			mountPoints.add(dir);
		}
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		    Object result = method.invoke(target, args);
		    if (result instanceof List) {
		    	if( mountPoints.size() > 0 ) {
		    		@SuppressWarnings("unchecked")
					List<FileSource> kids = (List<FileSource>) result;
		    		kids.addAll(mountPoints);
		    	}
			}
   	        return result;
		
	}

}
