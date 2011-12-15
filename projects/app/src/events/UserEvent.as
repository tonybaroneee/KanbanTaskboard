/**
 * UserEvent.as
 * 
 * Event handler for user-related commands
 * 
 * abarone - 10/10/10
 **/ 

package events
{
	import flash.events.Event;
	
	import services.KanbanService;
	
	import valueObjects.User;
	
	public class UserEvent extends Event
	{
		public static const REGISTER:String = "register";
		public static const SIGNIN:String = "signIn";
		public static const LOGOUT:String = "logout";
		public static const BROWSEPROJECTS:String = "browseProjects";
		
		public var user:User;
		public var taskboard:KanbanTaskboard;
		
		public function UserEvent(type:String, user:valueObjects.User=null, taskboard:KanbanTaskboard=null, bubbles:Boolean=true, cancelable:Boolean=true)
		{
			super(type, bubbles, cancelable);
			this.user = user;
			this.taskboard = taskboard;
		}
		
		override public function clone():Event
		{
			return new UserEvent(type, user, taskboard);
		}
	}
}