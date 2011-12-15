package commands
{
	import events.ProjectEvent;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import services.KanbanService;
	
	import valueObjects.Project;
	import valueObjects.ProjectData;
	
	public class AddUserCommand extends BaseCommand
	{
		public function AddUserCommand(event:ProjectEvent):void
		{
			super( event, this.result_handler, this.fault_handler);
		}
		
		public function execute(): void {
			var e:ProjectEvent = originEvent as ProjectEvent
			callresponder.token = KanbanService.getInstance().addUserToProject(e.project.id, e.project.name, e.project.userToAdd);
		}
		
		public function result_handler(event:ResultEvent):void {
			var e:ProjectEvent = originEvent as ProjectEvent;
			
			//This is the new project returned from the backend
			var success:Boolean = event.result as Boolean;
			
			
			if(success) {
				Alert.show("Successfully added " + e.project.userToAdd + ".");
			} else {
				Alert.show("Error: Could not add " + e.project.userToAdd + "to the project.");
			}
			
		}
		
		public function fault_handler(event:FaultEvent):void {
			Alert.show("Something has gone terribly wrong. Fail \n" + event.message );
			
		}
	}
}