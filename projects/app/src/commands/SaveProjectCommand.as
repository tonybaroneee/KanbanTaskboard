package commands
{
	import events.ProjectEvent;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import services.KanbanService;
	
	import valueObjects.Project;
	
	public class SaveProjectCommand extends BaseCommand
	{
		public function SaveProjectCommand(event:ProjectEvent):void
		{
			super( event, this.result_handler, this.fault_handler);
		}
		
		public function execute(): void {
			var e:ProjectEvent = originEvent as ProjectEvent
			callresponder.token = KanbanService.getInstance().saveProject(e.project.id);
		}
		
		public function result_handler(event:ResultEvent):void {
			var e:ProjectEvent = originEvent as ProjectEvent;
			
			if(event.result) {
				// Check if successful?
			} else {
				Alert.show("Something has gone terribly wrong. Fail");
			}
			
		}
		
		public function fault_handler(event:FaultEvent):void {
			Alert.show("Something has gone terribly wrong. Fail \n" + event.message );
			
		}
	}
}