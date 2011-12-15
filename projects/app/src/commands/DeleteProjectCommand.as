package commands
{
	import events.ProjectEvent;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import services.KanbanService;
	
	import valueObjects.Project;
	
	public class DeleteProjectCommand extends BaseCommand
	{
		public function DeleteProjectCommand(event:ProjectEvent):void
		{
			super( event, this.result_handler, this.fault_handler);
		}
		
		public function execute(): void {
			var e:ProjectEvent = originEvent as ProjectEvent;
			
		}
		
		public function result_handler(event:ResultEvent):void {
			var e:ProjectEvent = originEvent as ProjectEvent;
			
			if(event.result) {
				
			} else {
				Alert.show("Something has gone terribly wrong. Fail");
			}
			
		}
		
		public function fault_handler(event:FaultEvent):void {
			Alert.show("Something has gone terribly wrong. Fail \n" + event.message );
			
		}
	}
}