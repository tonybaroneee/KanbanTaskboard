package commands
{
	import events.ProjectEvent;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import services.KanbanService;
	
	import valueObjects.Project;
	import valueObjects.ProjectData;

	public class NewProjectCommand extends BaseCommand
	{
		public function NewProjectCommand(event:ProjectEvent):void
		{
			super( event, this.result_handler, this.fault_handler);
		}
		
		public function execute(): void {
			var e:ProjectEvent = originEvent as ProjectEvent
			callresponder.token = KanbanService.getInstance().createNewProject(e.project);
		}
		
		public function result_handler(event:ResultEvent):void {
			var e:ProjectEvent = originEvent as ProjectEvent;
			
			//This is the new project returned from the backend
			var project:Project = event.result as Project;
			
						
			if(event.result) {
				e.project.id = (event.result as Project).id;
				new OpenProjectCommand(e).execute();
			} else {
				Alert.show("Something has gone terribly wrong. Fail");
			}
			
		}
		
		public function fault_handler(event:FaultEvent):void {
			Alert.show("Something has gone terribly wrong. Fail \n" + event.message );
			
		}
	}
}