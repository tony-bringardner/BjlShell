/**
 *	Copyright 2024 Tony Bringardner
 *
 *	Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 *	on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for 
 *	the specific language governing permissions and limitations under the License.
 */
package us.bringardner.shell.ide;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import org.fife.ui.rsyntaxtextarea.SyntaxScheme;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Configuration {
	private static File configFile;
	private static Configuration global;
	private int maxRecent=10;
	private int minVariableNameLength=2;
	private int tabSize= 4;
	private boolean lineWrap=false;
	private String scheme;


	public String getScheme() {
		if( scheme == null ) {
			scheme = new SyntaxScheme(null, true).toCommaSeparatedString();
		}
		return scheme;
	}


	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	private Map<String,Double[]> defaultColors = new TreeMap<>();

	private List<Template> templates;

	private double[] drawingBackgroundColor = {0.999};



	/**
	 * templates2 is given to other objects so templates can not be modified
	 */
	private transient List<Template> templates2;

	public static Configuration getInstance()  {
		if( global == null ) {
			synchronized (Configuration.class) {
				if( global == null ) {
					Configuration tmp = null;
					File file = getConfigFile();
					if( file.exists()) {
						try {
							
							JAXBContext ctx = JAXBContext.newInstance(Configuration.class);
							Unmarshaller um = ctx.createUnmarshaller();
							tmp = (Configuration) um.unmarshal(file);
							
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage(), "Error managing configuration", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						tmp = createDefault();
					}
					global = tmp;
				}
			}
		}

		return global;
	}





	
	private static File getConfigFile() {
		if( configFile == null ) {
			synchronized (Configuration.class) {
				if( configFile == null ) {
					File home = new File(System.getProperty("user.home"));
					File dir = new File(home,".bjlsh");
					if(!dir.exists()) {
						dir.mkdirs();
					}
					configFile = new File(dir,"Config.xml");
				}
			}
		}

		return configFile;
	}

	public void save () throws IOException {
		JAXBContext ctx;
		try {
			ctx = JAXBContext.newInstance(Configuration.class);
			Marshaller um = ctx.createMarshaller();
			um.marshal(this, getConfigFile());
		} catch (JAXBException e) {
			throw new IOException(e);
		}
	
	}

	public int getMaxRecent() {
		return maxRecent;
	}


	public void setMaxRecent(int maxRecent) {
		this.maxRecent = maxRecent;
	}


	public int getMinVariableNameLength() {
		return minVariableNameLength;
	}


	public void setMinVariableNameLength(int minVariableNameLength) {
		this.minVariableNameLength = minVariableNameLength;
	}

	public List<Template> getTemplates() {
		if( templates2 == null ) {
			List<Template> tmp = new ArrayList<>();
			populateDefault(tmp);
			if( templates != null ) {
					if( templates2 == null && templates != null ) {
						for (Template t : templates) {
							if( !tmp.contains(t)) {
								tmp.add(t.copy());	
							}							
						}						
					}
			}
			templates2 = tmp;
		}

		return templates2;
	}


	public void setTemplates(List<Template> templates) {
		this.templates = new ArrayList<Template>();
		populateDefault(this.templates);
		templates2 = null;
	}

	
	private static Template defaultTemplates [] = {
			new Template( "true", "true"),
			new Template( "false", "false"),
			new Template( "until", "until-loop", 
					"until [ ${true} ]\n"
					+ "do\n"
					+ "  ${echo}\n"
					+ "done"),
			new Template( "while", "while-loop", 
					"while [ ${false} ]\n"
					+ "do\n"
					+ "  ${echo}\n"
					+ "done\n"),
			
			new Template( "for", "for-array", 
					"for ${i} in ${1 2};\n"
					+ "do\n"
					+ "\n\techo ${i};\n"
					+ "done\n"),
			new Template( "for", "for-list", 
					"for word in ${list of words}\n"
					+ "do \n"
					+ " echo ${\"$word\"}\n"
					+ "done"),
			
			new Template( "for", "for-range", 
					"for ${i} in {${start}..${stop}..${increment}}\n"
					+ "do\n"
					+ "\techo  ${$i}\n"
					+ "done"),
	};
	
	private static void populateDefault(List<Template> templates) {
		for(Template t : defaultTemplates) {
			if(!templates.contains(t)) {
				templates.add(t);
			}
		}
	}

	public static Configuration createDefault() {
		Configuration ret = new Configuration();
		ret.maxRecent = 10;
		ret.minVariableNameLength = 2;
		ret.templates = new ArrayList<>();
		ret.defaultColors = new TreeMap<>();
		ret.setLineWrap(false);
		ret.setTabSize(4);

		ret.setScheme(new SyntaxScheme(null, true).toCommaSeparatedString());
		populateDefault(ret.templates);

		return ret;
	}
	// 287 323 842 997     807140



	public static void main(String[] args) throws IOException {
		boolean create = true;
		if( create ) {
			Configuration config = createDefault();
			config.save();
		} else {
			Configuration config = getInstance();
			System.out.println("Number or templates="+config.templates.size());
			System.out.println("maxRecent="+config.maxRecent);
			System.out.println("minVariableNameLength="+config.minVariableNameLength);
			List<Template> temp = config.getTemplates();
			List<Template> temp2 = config.getTemplates();
			System.out.println("eq="+temp.equals(temp2));
		}
	}


	public Map<String,Double[]> getDefaultColors() {
		return defaultColors;
	}


	public void setDefaultColors(Map<String,Double[]> defaultColors) {
		this.defaultColors = defaultColors;
	}


	public double[] getDrawingBackgroundColor() {
		return drawingBackgroundColor;
	}


	public void setDrawingBackgroundColor(double[] drawingBackgroundColor) {
		this.drawingBackgroundColor = drawingBackgroundColor;

	}


	public int getTabSize() {
		return tabSize;
	}


	public void setTabSize(int tabSize) {
		this.tabSize = tabSize;
	}


	public boolean isLineWrap() {
		return lineWrap;
	}


	public void setLineWrap(boolean lineWrap) {
		this.lineWrap = lineWrap;
	}




}
