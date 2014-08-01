/*******************************************************************************
 * Copyright 2012 University of Southern California
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 	http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * This code was developed by the Information Integration Group as part 
 * of the Karma project at the Information Sciences Institute of the 
 * University of Southern California.  For more information, publications, 
 * and related projects, please see: http://www.isi.edu/integration
 ******************************************************************************/
package edu.isi.karma.controller.command;

import edu.isi.karma.controller.command.selection.SuperSelection;
import edu.isi.karma.controller.update.UpdateContainer;
import edu.isi.karma.controller.update.WorksheetUpdateFactory;
import edu.isi.karma.modeling.alignment.Alignment;
import edu.isi.karma.modeling.alignment.AlignmentManager;
import edu.isi.karma.rep.HNodePath;
import edu.isi.karma.rep.Workspace;


/**
 * Commands that operate on a worksheet.
 * 
 * @author szekely
 * 
 */
public abstract class WorksheetCommand extends Command {

	protected final String worksheetId;
	protected SuperSelection selection;
	protected WorksheetCommand(String id, String worksheetId) {
		super(id);
		this.worksheetId = worksheetId;
	}

	public String getWorksheetId() {
		return worksheetId;
	}
	
	public void setSelection(SuperSelection sel) {
		this.selection = sel;
	}
	
	private Alignment getAlignmentOrCreateIt(Workspace workspace)
	{
		return AlignmentManager.Instance().getAlignmentOrCreateIt(workspace.getId(), worksheetId, workspace.getOntologyManager());
	}
	
	// TODO break this method up.  
	public UpdateContainer computeAlignmentAndSemanticTypesAndCreateUpdates(Workspace workspace, HNodePath path)
	{
		Alignment alignment = getAlignmentOrCreateIt(workspace);
		return WorksheetUpdateFactory.createSemanticTypesAndSVGAlignmentUpdates(worksheetId, workspace, alignment);
	}
		
	// TODO break this method up.  
	public UpdateContainer computeAlignmentAndSemanticTypesAndCreateUpdates(Workspace workspace)
	{
		Alignment alignment = getAlignmentOrCreateIt(workspace);
		return WorksheetUpdateFactory.createSemanticTypesAndSVGAlignmentUpdates(worksheetId, workspace, alignment);
	}
}
