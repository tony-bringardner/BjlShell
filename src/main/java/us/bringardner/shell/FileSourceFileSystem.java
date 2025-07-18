/**
 * <PRE>
 * 
 * Copyright Tony Bringarder 1998, 2025 <A href="http://bringardner.com/tony">Tony Bringardner</A>
 * 
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       <A href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</A>
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *  </PRE>
 *   
 *   
 *	@author Tony Bringardner   
 *
 *
 * ~version~V000.01.31-V000.01.05-V000.01.15-V000.01.00-V000.00.01-V000.00.00-
 */

package us.bringardner.shell;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ProgressMonitor;

import us.bringardner.io.filesource.FileSource;
import us.bringardner.io.filesource.FileSourceFactory;
import us.bringardner.io.filesource.FileSourceFilter;
import us.bringardner.io.filesource.ISeekableInputStream;

public class FileSourceFileSystem implements FileSource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	private FileSource[] kids=new FileSource[0];
	
	public FileSourceFileSystem (String name) throws IOException {
		this.name = name;
	}
	
	public boolean mount(FileSource dir, String name ) throws IOException {
		for (int idx = 0; idx < kids.length; idx++) {
			if(kids[idx].getName().equals(name)) {
				return false;
			}
		}
		FileSource[] tmp = new FileSource[kids.length+1];
		for (int idx = 0; idx < kids.length; idx++) {
			tmp[idx] = kids[idx];				
		}
		tmp[tmp.length-1] = new RootFile(name, dir);
		kids=tmp;
		return  true;
	}
	
	public boolean unmount(String name) throws IOException {
		boolean ret = false;
		int pos = -1;
		for (int idx = 0; idx < kids.length; idx++) {
			if(kids[idx].getName().equals(name)) {
				pos = idx;
			}
		}
		if( pos>=0) {
			FileSource [] tmp = new FileSource[kids.length-1];			
			for (int idx = 0,pos2 = 0; idx < kids.length; idx++) {
				if( idx != pos) {
					tmp[pos2++] = kids[idx];
				}
			}
			kids = tmp;
			ret = true;
		}
		return ret;
	}
	
	@Override
	public String toString() {
		return getAbsolutePath();
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public long length() {
		return 0;
	}

	@Override
	public boolean isDirectory() {
		return true;
	}

	@Override
	public long lastModified() {
		return 0;
	}

	@Override
	public FileSource[] listFiles() throws IOException {
		return kids;
	}


	
	@Override
	public String getAbsolutePath() {
		
		return "/"+name;
	}

	

	@Override
	public InputStream getInputStream() throws IOException {
		throw new IllegalStateException("Can't get a stream from a root");	
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		throw new IllegalStateException("Can't get a stream from a root");
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public boolean mkdirs() throws IOException {
		return true;
	}

	@Override
	public FileSource getParentFile() throws IOException {
		return null;
	}

	@Override
	public boolean delete() throws IOException {
		return false;
	}

	@Override
	public void refresh() throws IOException {
		
	}

	
	public String getTitle() {
		return getName();
	}

	
	public FileSource getLinkedTo() {
		return null;
	}

	@Override
	public int compareTo(Object o) {
		return toString().compareTo(o.toString());
	}

	@Override
	public long getCreateDate() throws IOException {
		return 0;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public boolean canRead()  {
		return true;
	}

	@Override
	public boolean canWrite()  {
		return false;
	}

	@Override
	public boolean createNewFile() throws IOException {
		return false;
	}

	@Override
	public FileSource getChild(String name) throws IOException {
		FileSource ret = null;
		for(FileSource kid : kids) {
			if( kid.getName().equals(name)) {
				ret = kid;
				break;
			}
		}
		
		return ret;
	}

	@Override
	public FileSourceFactory getFileSourceFactory() {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public String getCanonicalPath() throws IOException {
		return getAbsolutePath();
	}

	@Override
	public String getParent() {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public boolean isChildOfMine(FileSource child) throws IOException {
		
		throw new RuntimeException("Not implemented");
	}

	@Override
	public boolean isFile() throws IOException {
		return false;
	}

	@Override
	public String[] list() throws IOException {
		FileSource[] list = listFiles();
		String ret [] = new String[list.length];
		for (int idx=0; idx < list.length; idx++) {
			ret[idx] = list[idx].getName();
		}
		return ret;
	}

	@Override
	public String[] list(FileSourceFilter filter) throws IOException {
		FileSource[] list = listFiles(filter);
		String ret [] = new String[list.length];
		for (int idx=0; idx < list.length; idx++) {
			ret[idx] = list[idx].getName();
		}
		return ret;
	}

	@Override
	public FileSource[] listFiles(FileSourceFilter filter) throws IOException {
		List<FileSource> list = new ArrayList<FileSource>();
		for (FileSource f : listFiles()) {
			if( filter.accept(f)) {
				list.add(f);
			}
		}
		
		return list.toArray(new FileSource[list.size()]);
	}

	@Override
	public boolean mkdir() throws IOException {
		return false;
	}

	@Override
	public boolean renameTo(FileSource dest) throws IOException {
		return false;
	}

	
	@Override
	public boolean setReadOnly() throws IOException {
		return true;
	}

	@Override
	public void dereferenceChilderen() {
		
	}

	@Override
	public OutputStream getOutputStream(boolean append) throws IOException {
		throw new IOException();
	}

	@Override
	public URL toURL() throws MalformedURLException {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public boolean isVersionSupported() throws IOException {
		return false;
	}

	@Override
	public long getVersion() throws IOException {
		return 0;
	}

	@Override
	public long getVersionDate() throws IOException {
		return 0;
	}

	@Override
	public long getMaxVersion() {
		return 0;
	}

	@Override
	public InputStream getInputStream(long bytesRec) throws IOException {
		throw new IOException();
	}

	
	@Override
	public FileSource[] listFiles(ProgressMonitor progress) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public boolean isHidden() {
		return false;
	}

	@Override
	public ISeekableInputStream getSeekableInputStream() throws IOException {
		throw new IOException("ISeekableInputStream not imploemented");
	}

	
	@Override
	public GroupPrincipal getGroup() throws IOException {		
		throw new RuntimeException("Not implemented");
	}

	@Override
	public UserPrincipal getOwner() throws IOException {
		throw new RuntimeException("Not implemented");
	}

	
	@Override
	public long lastAccessTime() throws IOException {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public long creationTime() throws IOException {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public boolean canOwnerRead() throws IOException {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public boolean canOwnerWrite() throws IOException {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public boolean canOwnerExecute() throws IOException {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public boolean canGroupRead() throws IOException {
		return true;
	}

	@Override
	public boolean canGroupWrite() throws IOException {
		return false;
	}

	@Override
	public boolean canGroupExecute() throws IOException {
		return false;
	}

	@Override
	public boolean canOtherRead() throws IOException {
		return true;
	}

	@Override
	public boolean canOtherWrite() throws IOException {
		return false;
	}

	@Override
	public boolean canOtherExecute() throws IOException {
		return false;
	}

	@Override
	public boolean setLastModifiedTime(long time) throws IOException {
		return false;
	}

	@Override
	public boolean setLastAccessTime(long time) throws IOException {
		return false;
	}

	@Override
	public boolean setCreateTime(long time) throws IOException {
		return false;
	}

	@Override
	public boolean setExecutable(boolean b) throws IOException {
		return false;
	}

	@Override
	public boolean setReadable(boolean b) throws IOException {
		return false;
	}

	@Override
	public boolean setWritable(boolean b) throws IOException {
		return false;
	}

	@Override
	public boolean setExecutable(boolean b, boolean ownerOnly) throws IOException {
		return false;
	}

	@Override
	public boolean setReadable(boolean b, boolean ownerOnly) throws IOException {
		return false;
	}

	@Override
	public boolean setWritable(boolean b, boolean ownerOnly) throws IOException {
		return false;
	}

	@Override
	public boolean setOwnerExecutable(boolean b) throws IOException {
		return false;
	}

	@Override
	public boolean setOwnerReadable(boolean b) throws IOException {
		return false;
	}

	@Override
	public boolean setOwnerWritable(boolean b) throws IOException {
		return false;
	}

	@Override
	public boolean setGroupExecutable(boolean b) throws IOException {
		return false;
	}

	@Override
	public boolean setGroupReadable(boolean b) throws IOException {
		return false;
	}

	@Override
	public boolean setGroupWritable(boolean b) throws IOException {
		return false;
	}

	@Override
	public boolean setOtherExecutable(boolean b) throws IOException {
		return false;
	}

	@Override
	public boolean setOtherReadable(boolean b) throws IOException {
		return false;
	}

	@Override
	public boolean setOtherWritable(boolean b) throws IOException {
		return false;
	}

	@Override
	public boolean setVersionDate(long time) throws IOException {
		return false;
	}

	@Override
	public boolean setVersion(long version, boolean saveChange) throws IOException {
		return false;
	}

	@Override
	public boolean setGroup(GroupPrincipal group) throws IOException {
		return false;
	}

	@Override
	public boolean setOwner(UserPrincipal owner) throws IOException {
		return false;
	}
	
}
