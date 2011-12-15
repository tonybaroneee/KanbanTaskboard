package commands
{
	import events.ProjectEvent;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import services.KanbanService;
	
	import valueObjects.Project;
	
	public class OpenProjectCommand extends BaseCommand
	{
		public function OpenProjectCommand(event:ProjectEvent):void
		{
			super( event, this.result_handler, this.fault_handler);
		}
		
		public function execute(): void {
			var e:ProjectEvent = originEvent as ProjectEvent
			callresponder.token = KanbanService.getInstance().openProject(e.project.id);
		}
		
		public function result_handler(event:ResultEvent):void {
			var e:ProjectEvent = originEvent as ProjectEvent;
			
			if(event.result) {
				e.taskboard.currentState = 'taskboardScreen';
				e.taskboard.project = event.result as Project;
				e.taskboard.taskboard.populate(event.result as Project);
			} else {
				Alert.show("Something has gone terribly wrong. Fail");
			}
			
		}
		
		public function fault_handler(event:FaultEvent):void {
			Alert.show("Something has gone terribly wrong. Fail \n" + event.message );
			
		}
	}
}