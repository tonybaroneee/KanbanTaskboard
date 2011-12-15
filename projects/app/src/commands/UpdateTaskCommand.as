package commands
{
	import events.TaskEvent;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.managers.PopUpManager;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import services.KanbanService;
	
	import valueObjects.Task;
	import valueObjects.TaskData;
	
	import views.Column;
	
	public class UpdateTaskCommand extends BaseCommand
	{
		
		public function UpdateTaskCommand(event:TaskEvent)
		{
			super( event, this.result_handler, this.fault_handler);
		}
		
		public function execute(): void {
			var e:TaskEvent = originEvent as TaskEvent;
			callresponder.token = KanbanService.getInstance().updateTask(e.fullTask);
		}
		
		public function result_handler(event:ResultEvent):void {
			var e:TaskEvent = originEvent as TaskEvent;
			
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