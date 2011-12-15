/**
 * ProjectEvent.as
 * 
 * Event handler for project-related commands
 * 
 * abarone - 10/10/10
 **/ 

package events
{
	import flash.events.Event;
	
	import valueObjects.Project;
	import valueObjects.ProjectData;
	
	public class ProjectEvent extends Event
	{
		public static const CREATEPROJECT:String = "createProject";
		public static const CANCELCREATION:String = "cancelCreation";
		public static const SUBMITCREATION:String = "submitCreation";
		public static const OPENPROJECT:String = "openProject";
		public static const SAVEPROJECT:String = "saveProject";
		public static const OPENTEMPLATES:String = "openTemplates";
		
		public static const ADDUSER:String = "addUser";
		public static const SUBMITUSER:String = "submitUser";
		public static const NOTIFSETTINGS:String = "notifSettings";
		public static const REVERTVERSION:String = "revertVersion";
		public static const HISTORYREPORT:String = "historyReport";
		public static const EXPORTBACKLOG:String = "exportBacklog";
		
		public static const DEBUG_GOTOCREATE:String = "DEBUG_gotocreate";
		public static const DEBUG_GOTOTASKBOARD:String = "DEBUG_gototaskboard";
		public static const DEBUG_GOTOSUBSCRIBE:String = "DEBUG_gotosubscribe";
		
		public var project:ProjectData;
		public var taskboard:KanbanTaskboard;
		public var fullProject:Project;
		
		public function ProjectEvent(type:String, project:valueObjects.ProjectData=null, taskboard:KanbanTaskboard=null, bubbles:Boolean=true, cancelable:Boolean=true)
		{
			super(type, bubbles, cancelable);
			this.project = project;
			this.taskboard = taskboard;
		}
		
		override public function clone():Event
		{
			return new ProjectEvent(type, project, taskboard);
		}
	}
}