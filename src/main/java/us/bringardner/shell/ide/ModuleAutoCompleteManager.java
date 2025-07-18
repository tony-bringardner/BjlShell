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

import java.util.ArrayList;
import java.util.List;

import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;
import org.fife.ui.autocomplete.TemplateCompletion;

public class ModuleAutoCompleteManager {


	public static final Object EDIT_MODULE_NAME = "Module~Name";

	private String name;
	private List<String> args;
	private int start;
	private String type;

	public ModuleAutoCompleteManager(String name, List<String> args, int scopeStart,String type) {
		this.name = name;
		this.start = scopeStart;
		this.type = type;
		if( args == null ) {
			this.args = new ArrayList<>();
		} else {
			this.args = args;
		}
	}



	public String getType() {
		return type;
	}



	public int getScopeStart() {
		return start;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public List<String> getArgs() {
		return args;
	}



	public void setArgs(List<String> args) {
		if( args == null ) {
			this.args = new ArrayList<>();
		} else {
			this.args = args;
		}
	}



	public String getCompletionString() {
		String ret = null;
		if( args != null && args.size()>0) {
			StringBuilder buf = new StringBuilder();
			for (String arg : args) {
				if( buf.length()>0) {
					buf.append(", ");
				}
				int idx= arg.indexOf('=');
				if( idx > 0 ) {
					//String name = arg.substring(0,idx);

					buf.append("${"+arg+"}");
				} else {
					buf.append("${"+arg+"}");
				}
			}
			ret = name+"("+buf+") ${cursor}";
		} else {
			ret = name+" ()";
		}
		return ret;
	}

	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if (obj instanceof ModuleAutoCompleteManager) {
			ModuleAutoCompleteManager m = (ModuleAutoCompleteManager) obj;
			ret = m.name.equals(name);
			if( ret ) {
				ret=args.equals(m.args);
				if( ret ) {
					ret = start == m.start;
				}
			}
		}

		return ret;
	}

	public Completion getCompletion(CompletionProvider provider) {
		Completion ret = null;
		if( args != null && args.size()>0) {
			ret = new TemplateCompletion(provider, name, name+"-"+type,getCompletionString());
		} else {
			ret = new ShorthandCompletion(provider,name+"-"+type,name+" ()");
		}
		return ret;
	}

	public String toString() {
		return getCompletionString();
	}



	

}
