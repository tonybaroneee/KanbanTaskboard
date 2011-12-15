/**
 * TaskEvent.as
 * 
 * Event handler for task-related commands
 * 
 * abarone - 10/10/10
 **/ 

package events
{
	import flash.events.Event;
	
	import valueObjects.Project;
	import valueObjects.TaskData;
	import valueObjects.Task;
	
	
	public class TaskEvent extends Event
	{
		public static const NEW_TASK:String = "newTask";
		public static const EDIT_TASK:String = "editTask";
		public static const DELETE_TASK:String = "deleteTask";
		public static const SAVE_TASK:String = "saveTask";
		
		public static const CANCEL_TASK:String = "cancelTask";
		public static const SUBMIT_TASK:String = "submitTask";
		public static const SUBMIT_TASK_EDIT:String = "submitTaskEdit";
		
		public static const LOAD_TASK:String = "loadTask";
		
		public var task:TaskData;
		public var fullTask:Task;
		public var project:Project;
		public var taskboard:KanbanTaskboard;
		public var colIndex:int;
		public var stageId:int;
		
		public function TaskEvent(type:String, task:valueObjects.TaskData=null, project:valueObjects.Project=null, taskboard:KanbanTaskboard=null, colIndex:int=-1, bubbles:Boolean=true, cancelable:Boolean=true)
		{
			super(type, bubbles, cancelable);
			this.task = task;
			this.colIndex = colIndex;
			this.project = project;
			this.taskboard = taskboard;
		}
		
		override public function clone():Event
		{
			return new TaskEvent(type, task, project, taskboard, colIndex);
		}
	}
}