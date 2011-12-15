package commands
{
	
	import events.UserEvent;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import services.KanbanService;
	
	import valueObjects.Project;
	
	public class BrowseProjectsCommand extends BaseCommand
	{
		public function BrowseProjectsCommand(event:UserEvent):void
		{
			super( event, this.result_handler, this.fault_handler);
		}
		
		public function execute(): void {
			var e:UserEvent = originEvent as UserEvent;
			callresponder.token = KanbanService.getInstance().getProjectsForUser(e.user);
		}
		
		public function result_handler(event:ResultEvent):void {
			var e:UserEvent = originEvent as UserEvent;
			
			
			if(event.result) {
				e.taskboard.selectproject.projectsMap = event.result;
				e.taskboard.selectproject.populateProjects();
			} else {
				Alert.show("Something has gone terribly wrong. Fail");
			}
			
		}
		
		public function fault_handler(event:FaultEvent):void {
			Alert.show("Something has gone terribly wrong. Fail \n" + event.message );
			
		}
	}
}